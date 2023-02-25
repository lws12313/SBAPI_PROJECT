package com.sbapi.project.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbapi.project.dao.UserDAO;
import com.sbapi.project.response.ApiException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	/* 회원등록여부조회 */
	@Override
	public String selectUserRegYn(Map<String, Object> params) throws ApiException {
		return dao.selectUserRegYn(params);
	}
	
	/* 회원정보조회 */
	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> params) throws ApiException {
		/*
		 * Map<String, Object> returnMap = new HashMap<String, Object>();
		 * returnMap.put("userId", "whskagns"); 
		 * returnMap.put("userNm", "조남훈");
		 * returnMap.put("phoneNum", "010-9150-0882"); 
		 * returnMap.put("emailId", "epsvkfo@gmail.com");
		 */
		
		return dao.selectUserInfo(params);
	}

	/* 회원로그인 */
	@Override
	public String selectUserLogin(Map<String, Object> params) throws ApiException {
		return "Y";
	}
	
	/* 회원가입 처리 */
	@Override
	public int insertUserInfo(Map<String, Object> params) throws ApiException {
		return dao.insertUserInfo(params);
	}
	
}
