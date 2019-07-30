package com.digify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.Enums.CommonEnums;
import com.digify.Enums.CommonEnums.STATUS;
import com.digify.model.CartModel;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.User;
import com.digify.model.UserOrderDetails;
import com.digify.model.UserOrders;
import com.digify.utils.DigifyJdbcTemplate;
import com.digify.utils.GenUtilities;

@Repository
public class EcommerceDaoImpl extends DigifyJdbcTemplate implements EcommerceDao {

	private Logger logger = Logger.getLogger(DigifyJdbcTemplate.class);

	@Override
	public Long addTocart(String cartFor, long userId, int serviceProductId) {
		logger.info("###addTocart action###");
		try {
			StringBuffer query = new StringBuffer();
			if (cartFor.equals("services")) {
				query.append("insert into user_cart ( user_id , cart_for, service_id , created_on) values (?,?,?,now())");

			} else if (cartFor.equals("products")) {
				query.append("insert into user_cart ( user_id , cart_for, product_id , created_on) values (?,?,?,now())");
//                int rowInsert = getJdbcTemplate().update(query, userId, cartFor, serviceProductId);
//                return rowInsert > 0 ? true : false;
			}
			 KeyHolder keyHolder = new GeneratedKeyHolder();
		        getJdbcTemplate().update(new PreparedStatementCreator() {
		            @Override
		            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		                PreparedStatement pstmt = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
		                int index = 1;
		                pstmt.setLong(index++, userId);
		                pstmt.setString(index++, cartFor);
		                pstmt.setInt(index++, serviceProductId);
		                return pstmt;
		            }
		        }, keyHolder);
//			int rowInsert = getJdbcTemplate().update(query, userId, cartFor, serviceProductId);
//			return rowInsert > 0 ? true : false;
		        return keyHolder.getKey().longValue();
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return null;
	}

	@Override
	public List<CartModel> listOfUserCart(long userId) {
		logger.info("###list of user cart####");
		List<CartModel> list = null;
		String query = "select * from user_cart where user_id = ? and status=?";
		try {
			list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<CartModel>(CartModel.class), userId,
					STATUS.ACTIVE.ID);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return (List<CartModel>) list;
	}

	@Override
	public Integer insertOrder(UserOrders userOrders) {
		try {
			String query = "INSERT INTO user_orders " + "(user_id, ORDERID, BANKNAME, "
					+ "BANKTXNID, CURRENCY, GATEWAYNAME, MID, " + "PAYMENTMODE, RESPCODE, RESPMSG, STATUS, "
					+ "TXNAMOUNT, TXNDATE, TXNID, CHECKSUMHASH, " + "BIN_NUMBER, CARD_LAST_NUMS) " + "VALUES "
					+ "(?, ?, ?, " + "?, ?, ?, ?, " + "?, ?, ?, ?, " + "?, ?, ?, ?, " + "?, ?);";
//	           
			KeyHolder holder = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(query.toString(),
							PreparedStatement.RETURN_GENERATED_KEYS);
					int index = 1;
					pstmt.setLong(index++, userOrders.getUserId());
					pstmt.setString(index++, userOrders.getOrderid());
					pstmt.setString(index++, userOrders.getBankname());
					pstmt.setString(index++, userOrders.getBanktxnid());
					pstmt.setString(index++, userOrders.getCurrency());
					pstmt.setString(index++, userOrders.getGatewayname());
					pstmt.setString(index++, userOrders.getMid());
					pstmt.setString(index++, userOrders.getPaymentmode());
					pstmt.setString(index++, userOrders.getRespcode());
					pstmt.setString(index++, userOrders.getRespmsg());
					pstmt.setString(index++, userOrders.getStatus());
					pstmt.setString(index++, userOrders.getTxnamount());
					pstmt.setString(index++, userOrders.getTxndate());
					pstmt.setString(index++, userOrders.getTxnid());
					pstmt.setString(index++, userOrders.getChecksumhash());
					pstmt.setString(index++, userOrders.getBinNumber());
					pstmt.setString(index++, userOrders.getCardLastNums());

					return pstmt;
				}
			}, holder);
			int id = holder.getKey().intValue();
			return id;
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return null;
	}

	@Override
	public <T> int[] insertOrderDetails(String insertFor, UserOrders userOrders, List<T> productList) {
		try {
			if (insertFor.equals(BASIC_STRINGS.PRODUCTS.getStringName())) {
				final String query = "INSERT INTO user_order_details (user_id, user_order_id, product_id)"
						+ " VALUES (?,?,?);";
				List<Products> products = (List<Products>) productList;
				return getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// emp id is auto generated so not provided
						int index = 1;
						ps.setLong(index++, userOrders.getUserId());
						ps.setString(index++, userOrders.getOrderid());
						ps.setLong(index++, products.get(i).getId());
					}

					@Override
					public int getBatchSize() {
						return products.size();
					}
				});
			} else {

				final String query = "INSERT INTO user_order_details (user_id, user_order_id, service_id)"
						+ " VALUES (?,?,?);";
				List<Services> service = (List<Services>) productList;
				return getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// emp id is auto generated so not provided
						int index = 1;
						ps.setLong(index++, userOrders.getUserId());
						ps.setString(index++, userOrders.getOrderid());
						ps.setLong(index++, service.get(i).getId());
					}

					@Override
					public int getBatchSize() {
						return service.size();
					}
				});
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return null;
	}

	@Override
	public int cartDelete(int userId) {
		try {

			String query = "update user_cart set status=?,modified_on=now() where user_id=?;";
			int iscartDelete = getJdbcTemplate().update(query, STATUS.INACTIVE.ID, userId);
			return iscartDelete;
		} catch (EmptyResultDataAccessException e) {
			logger.error("EmptyResultDataAccessException " + e);
		} catch (DataAccessException e) {
			logger.error("DataAccessException" + e);
		}
		return 0;
	}

	@Override
	public boolean removeFromCart(int cartId) {

		User user = GenUtilities.getLoggedInUser();
		try {
			String query  = null;
			if (user != null) {
				query = "update user_cart set status=?,modified_on=now() where id =? and  user_id=?;";
				
				int iscartDelete = getJdbcTemplate().update(query, STATUS.INACTIVE.ID, cartId, user.getId());
				return iscartDelete > 0 ? true : false;
			}

		} catch (EmptyResultDataAccessException e) {
			logger.error("removeFromCart " + e);
		} catch (DataAccessException e) {
			logger.error("DataAccessException" + e);
		}
		return false;
	}
	
	@Override
	public List<UserOrders> getUserOrders(boolean isAdmin) {
		logger.info("###getUserOrders####");
		List<UserOrders> list = null;
		User user = GenUtilities.getLoggedInUser();
		try {
			if(!isAdmin) {
			String query = "select * from user_orders where user_id = ? and STATUS='TXN_SUCCESS'";
			list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserOrders>(UserOrders.class), user.getId());
			}else {
				String query = "select * from user_orders where STATUS='TXN_SUCCESS'";
				list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserOrders>(UserOrders.class));
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("EmptyResultDataAccessException ::" + e);
		} catch (DataAccessException e) {
			logger.error("DataAccessException ::" + e);
		}
		return list;
	}

	@Override
	public List<UserOrderDetails> getUserOrdersDetails(String orderId) {
		logger.info("###getUserOrdersDetails####");
		List<UserOrderDetails> list = null;
		User user = GenUtilities.getLoggedInUser();
		String query = "";
		
		try {
			if(orderId != null && StringUtils.isNotEmpty(orderId)) {
				
				query = "select uod.*,uo.TXNAMOUNT,uo.TXNID from user_order_details uod inner join user_orders uo on uod.user_order_id = uo.ORDERID order by created_on";
				list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserOrderDetails>(UserOrderDetails.class));
			}else {
				query = "select uod.*,uo.TXNAMOUNT,uo.TXNID from user_order_details uod inner join user_orders uo on uod.user_order_id = uo.ORDERID where  uod.user_id = ?  order by created_on";
				list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserOrderDetails>(UserOrderDetails.class), user.getId() );
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("EmptyResultDataAccessException ::" + e);
		} catch (DataAccessException e) {
			logger.error("DataAccessException ::" + e);
		}
		return list;
	}

	@Override
	public UserOrders findByOrderId(String orderId) {
		logger.info("###findByOrderId####");
		UserOrders userOrder = null;
		User user = GenUtilities.getLoggedInUser();
		String query = "select * from user_orders where ORDERID = ? and user_id=?";
		try {
			userOrder = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<UserOrders>(UserOrders.class),orderId, user.getId());
		} catch (EmptyResultDataAccessException e) {
			logger.error("EmptyResultDataAccessException ::" + e);
		} catch (DataAccessException e) {
			logger.error("DataAccessException ::" + e);
		}
		return userOrder;
	}

}
