package com.sbapi.project.service;

import java.util.Map;

import com.sbapi.project.response.ApiException;

public interface UserService {

	/* 회원등록여부조회 */
	public String selectUserRegYn(Map<String, Object> params) throws ApiException;
	
	/* 회원정보조회 */
	public Map<String, Object> selectUserInfo(Map<String, Object> params) throws ApiException;
	
}
