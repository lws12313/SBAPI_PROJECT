package com.sbapi.project.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sbapi.project.response.ApiException;

@Service
public class UserServiceImpl implements UserService {

	/* 회원등록여부조회 */
	@Override
	public String selectUserRegYn(Map<String, Object> params) throws ApiException {
		return "Y";
	}
	
	/* 회원정보조회 */
	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> params) throws ApiException {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("userId", "whskagns");
		returnMap.put("userNm", "조남훈");
		returnMap.put("phoneNum", "010-9150-0882");
		returnMap.put("emailId", "epsvkfo@gmail.com");
		
		return returnMap;
	}
	
}
