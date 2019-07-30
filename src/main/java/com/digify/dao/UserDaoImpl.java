package com.digify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.Enums.CommonEnums.STATUS;
import com.digify.Enums.CommonEnums.USER_TYPE;
import com.digify.model.User;
import com.digify.model.UserBookingDetails;
import com.digify.utils.DigifyJdbcTemplate;

@Repository
public class UserDaoImpl extends DigifyJdbcTemplate implements UserDao {
    private Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final String GET_USER = "select * from user where email = ? and password = ?";
    private static final String GET_USER_BY_MOBILE = "select * from user where mobile_no = ? ";
    private static final String INSERT_SQL = "INSERT INTO user" +
            "( mobile_no," +
            "name," +
            "status," +
            "created_by )" +
            "VALUES" +
            "(?,?,?,?); ";


    @Override
    public User findUserByMobile(String mobileno) {

        User user = null;
        try {
            logger.info("sql ===" + GET_USER_BY_MOBILE);
            user = getJdbcTemplate().queryForObject(GET_USER_BY_MOBILE, new BeanPropertyRowMapper<User>(User.class), mobileno);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No user found" + e);
        } catch (DataAccessException e) {
            logger.error("Database exception found" + e);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {

        logger.debug("Start saveUser");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
                int index = 1;
                pstmt.setString(index++, user.getMobileNo());
                pstmt.setString(index++, user.getName());
                pstmt.setInt(index++, STATUS.ACTIVE.ID);
                pstmt.setString(index++, BASIC_STRINGS.SYSTEM.getStringName());

                return pstmt;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(long id) {

    }

    @Override
    public List<User> findAllUsers() {

        logger.debug("findAllUsers start:");
        List<User> userLIst = null;
        String query = "select * from user;";
        try {
            userLIst = getJdbcTemplate().query(query, new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException e) {
            logger.error("No role found" + e);
        } catch (DataAccessException e) {
            logger.error("No role found" + e);
        }
        return userLIst;
    }

    @Override
    public void deleteAllUsers() {

    }

    @Override
    public boolean isUserExist(User user) {

        return false;
    }

    @Override
    public User getUserByUserName(String userName, String password) {
        logger.info("Start login");
        User user = null;
        try {
            user = getJdbcTemplate().queryForObject(GET_USER, new BeanPropertyRowMapper<User>(User.class), userName, password);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No user found" + e);
        } catch (DataAccessException e) {
            logger.error("No user found" + e);
        }
        return user;
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        logger.debug("get user roles");
        List<String> userRoles = null;
        String query = "select r.type from user_roles  ur left join role_m r on r.id = ur.role_id where ur.user_id = ? ";
        try {
            userRoles = getJdbcTemplate().queryForList(query, String.class, userId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No role found" + e);
            } catch (DataAccessException e) {
            logger.error("No role found" + e);
        }
        return userRoles;
    }

    @Override
    public void insertUserRole(long id) {
        String sql = "insert into user_roles(user_id , role_id) values (?,?)";
        getJdbcTemplate().update(sql, new Object[]{id, USER_TYPE.USER.ID});

    }

    @Override
    public boolean insertPotentialCuastomer(String name, String phone) {

        String sql = "insert into potential_customers(name , phone) values (?,?)";
        int updt = getJdbcTemplate().update(sql, new Object[]{name, phone});
        if (updt > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User findById(long id) {
        String query = "select * from user where id = ?";
        User user = null;
        try {
            logger.info("query ===" + query);
            user = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<User>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No user found" + e);
        } catch (DataAccessException e) {
            logger.error("Database exception found" + e);
        }
        return user;
    }

    @Override
    public List<UserBookingDetails> getUserBookingDetails() {
        logger.debug("getUserBookingDetails===start:");
        List<UserBookingDetails> UserBookingDetails = null;
        String query = "select * from user_booking_details";
        try {
            UserBookingDetails = getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserBookingDetails>(UserBookingDetails.class));
        } catch (EmptyResultDataAccessException e) {
            logger.error("No role found" + e);
        } catch (DataAccessException e) {
            logger.error("No role found" + e);
        }
        return UserBookingDetails;
    }

    public void activateUser(final long userId) {
        String query = "update user set status=1 where id=?;";
        getJdbcTemplate().update(query, userId);

    }

    public User getRegistrationTokenAndStatus(final long userId) {
        User user = null;
        try {
//			String query = "select token,status,otp from user_reg_history urh inner join user u on urh.user_id=u.user_id where u.user_id=?;";
            String query = " select * from user where id =?";
            user = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<User>(User.class), userId);
        } catch (EmptyResultDataAccessException e) {
            logger.error(" getRegistrationToken() EmptyResultDataAccessException");
        } catch (DataAccessException e) {
            logger.error(" getRegistrationToken() DataAccessException");
        }
        return user;
    }

    @Override
    public long insertUser(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curdate = new Date();
        String currentTime = sdf.format(curdate);
        logger.debug("::insertUser()");
        final String query = "INSERT INTO user(email,mobile_no,password,name,status,created_on)VALUES(?,?,?,?,?,'" + currentTime + "');";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, user.getEmail());
                ps.setString(i++, user.getMobileNo());
                ps.setString(i++, user.getPassword());
                if (!StringUtils.isEmpty(user.getName())) {
                    ps.setString(i++, user.getName());
                } else {
                    ps.setString(i++, null);
                }

                ps.setInt(i++, STATUS.INACTIVE.ID);

                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());

        return keyHolder.getKey().longValue();
    }

    @Override
    public User checkUserByEmailorID(String emailorID) {
        logger.debug("::checkUserByEmail()");
        User user = null;
        final String query = "select * from user where email=? ";
        try {
            user = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<User>(User.class), emailorID);
        } catch (EmptyResultDataAccessException e) {
            logger.error(" checkUserByEmail() EmptyResultDataAccessException");
        } catch (DataAccessException e) {
            logger.error(" checkUserByEmail() DataAccessException");
        }
        return user;
    }

    @Override
    public boolean insertPassGenToken(Long userId, String token) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curdate = new Date();
        String currentTime = sdf.format(curdate);
        String query = "update user set pass_gen_token=?, modified_on=?  where id=?;";
        int get = getJdbcTemplate().update(query, token, currentTime, userId);
        if (get > 0)
            return true;

        return false;
    }

    @Override
    public String getpassGenToken(long userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curdate = new Date();
        String currentTime = sdf.format(curdate);
        String passGenToken = null;
        try {
            String query = "select pass_gen_token from user where id=? and modified_on > (DATE_SUB('" + currentTime + "', INTERVAL 1 DAY));";
            passGenToken = getJdbcTemplate().queryForObject(query, new Object[]{userId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error(" getRegistrationToken() EmptyResultDataAccessException");
        } catch (DataAccessException e) {
            logger.error(" getRegistrationToken() DataAccessException");
        }
        return passGenToken;
    }

    @Override
    public boolean resetPassword(User user, String newpassword) {
        try {
            String query = "update user set password=? where id=?;";
            getJdbcTemplate().update(query, newpassword, user.getId());
            return true;
        } catch (EmptyResultDataAccessException e) {
            logger.error(" resetPassword() EmptyResultDataAccessException");
        } catch (DataAccessException e) {
            logger.error(" resetPassword() DataAccessException");
        }
        return false;
    }

    @Override
    public boolean contactUsSubmit(String name, String email, String subject, String message, String dateOfContact,
                                   String mobileNo) {
        String Sqlquery = "INSERT INTO contact_us(name,email,subject,message ,mobile_no , date_of_contact) VALUES (?,?,?,?,?,?);";
        int rowInsert = getJdbcTemplate().update(Sqlquery, name, email, subject, message, mobileNo, dateOfContact);
        return rowInsert > 0 ? true : false;
    }


}
