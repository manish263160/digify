package com.digify.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.digify.Enums.OTP_ENUMS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginUtils {

    private static final Logger logger = Logger.getLogger(LoginUtils.class);

    @Value("${sms.authkey}")
    private String smsauthkey;

    @Value("${sms.otpMsgUrl}")
    private String otpMsgUrl;

    @Value("${sms.otpResend}")
    private String otpResend;

    @Value("${sms.otpVeryfy}")
    private String otpVeryfy;

    @Value("${sms.sendSms}")
    private String sendSms;

    public ResponseEntity<String> generateOtp(String mobileNo) {

        String authKey = smsauthkey;
        String message = OTP_ENUMS.OTP_MESSAGE.getKey();
        String sender = OTP_ENUMS.SEBDER.getKey();
        String mobile = mobileNo;
        String otp_length = OTP_ENUMS.OTP_LENGTH.getKey();
        String otp_expiry = OTP_ENUMS.OTP_EXPIRY.getKey();

        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("authkey", authKey);
            map.add("mobile", mobile);
            map.add("message", message);
            map.add("sender", sender);
            map.add("otp_length", otp_length);
            map.add("otp_expiry", otp_expiry);
            ResponseEntity<String> resp = callThirdPartyRest(otpMsgUrl, map);
            return resp;
        } catch (Exception e) {
            logger.info("Exception on otp sending" + e);
            return null;
        }

    }

    public ResponseEntity<String> verifyOtp(String mobileNo, String otp) {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("authkey", smsauthkey);
            map.add("mobile", mobileNo);
            map.add("otp", otp);
            ResponseEntity<String> resp = callThirdPartyRest(otpVeryfy, map);
            return resp;
        } catch (Exception e) {
            logger.info("Exception on otp sending" + e);
            return null;
        }
    }

    public ResponseEntity<String> resendOtp(String mobileNo) {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("authkey", smsauthkey);
            map.add("mobile", mobileNo);
            map.add("retrytype", OTP_ENUMS.RETRYTYPE_TEXT.getKey());
            ResponseEntity<String> resp = callThirdPartyRest(otpResend, map);
            return resp;
        } catch (Exception e) {
            logger.info("Exception on resend otp sending" + e);
            return null;
        }
    }


    public ResponseEntity<String> callThirdPartyRest(String url, MultiValueMap<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        logger.info("response objet from otp call==" + response.toString());
        return response;
    }

    public static JsonNode genericResponseConvert(ResponseEntity<String> responseEntity) {
        String getBody = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObj = null;
        try {
            jsonObj = mapper.readTree(getBody);
            logger.info("actualObj=====" + jsonObj.toString());
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException === " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException === " + e.getMessage());
        }

        return jsonObj;
    }

    public ResponseEntity<String> sendSms(String name, String phone) {
        String authKey = smsauthkey;

        String message = "Dear " + name + ", your request for call back has been received. We will get back to you within 24 hours. Thank you for your patience. Regards, Team Novowash";
        String sender = OTP_ENUMS.SEBDER.getKey();
        String mobile = phone;
        String otp_length = OTP_ENUMS.OTP_LENGTH.getKey();
        String otp_expiry = OTP_ENUMS.OTP_EXPIRY.getKey();

        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("authkey", authKey);
            map.add("mobile", mobile);
            map.add("message", message);
            map.add("sender", sender);
            map.add("country", "91");
            map.add("route", "1");
//			ResponseEntity<String> resp = callThirdPartyRest(sendSms , map);

//			String URI = sendSms+"?country=91&sender="+sender+"&route=1&authkey="+authKey+"&message="+message+"&mobiles="+mobile;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(sendSms, HttpMethod.GET, request, String.class);
            logger.info("response objet from otp call==" + response.toString());
            return response;
			/*
			
			
			return response;*/
//			return resp;
        } catch (Exception e) {
            logger.info("Exception on sms sending" + e);
            return null;
        }
    }


}
