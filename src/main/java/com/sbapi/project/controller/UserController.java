package com.sbapi.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbapi.project.dto.CommResDTO;
import com.sbapi.project.dto.UserGetInfoDTO;
import com.sbapi.project.dto.UserGetInfoResDTO;
import com.sbapi.project.response.ApiException;
import com.sbapi.project.service.UserService;
import com.sbapi.project.util.ExceptionEnum;
import com.sbapi.project.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 샘플 프로젝트 회원관리 API Controller
 * @Desc 샘플 프로젝트 회원관리 업무 인터페이스 API
 * @author 조남훈
 *
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Value("${sbapi.project.key}")
	private String _API_KEY_;
	
	/**
	 * 회원정보조회 API (테이블 연동 X) - /user/getInfo
	 * @Desc - 회원 정보를 조회합니다.
	 * @param header, param
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public UserGetInfoResDTO getInfo(@RequestHeader Map<String, Object> header, @RequestBody UserGetInfoDTO param) throws ApiException {
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ UserController getInfo header : " + header.toString());
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ UserController getInfo param : " + param.toString());
		
		/*** [Default 변수 선언] ***/
		UserGetInfoResDTO result = new UserGetInfoResDTO();
		Map<String, Object> reqParam = new HashMap<String, Object>();
		
		/*** [파라미터 유효성 체크] ***/
		//0. 필수 파라미터 체크
		if(!header.containsKey("apikey") || StrUtil.nvl(header.get("apikey").toString()).isEmpty() || !_API_KEY_.equals(StrUtil.nvl(header.get("apikey")))) {
			throw new ApiException(ExceptionEnum.API_KEY_EXCEPTION);
		}
		if(StrUtil.nvl(param.getUserId()).isEmpty()) {
			throw new ApiException(ExceptionEnum.REQUIRE_PARAM_EXCEPTION);
		}
		
		/*** [비즈니스 로직 처리] ***/
		//0. 비즈니스 변수 선언
		
		//1. 회원정보조회
		reqParam.clear();
		reqParam.put("userId", param.getUserId());
		String userRegYn = service.selectUserRegYn(reqParam);	//--회원등록여부조회
		
		if("Y".equals(userRegYn)) {
			//1.1. 등록된 회원인 경우
			reqParam.clear();
			reqParam.put("userId", param.getUserId());
			
			Map<String, Object> resultInfo = service.selectUserInfo(reqParam);	//--회원정보조회
			result.setUserId(resultInfo.get("userId").toString());
			result.setUserNm(resultInfo.get("userNm").toString());
			result.setPhoneNum(resultInfo.get("phoneNum").toString());
			result.setEmailId(resultInfo.get("emailId").toString());
		}
		
		/*** [결과응답 셋팅] ***/
		result.setResultCode("0000");
		result.setResultMessage("정상 처리");
		result.setUserExistYn(userRegYn);
		
		return result;
	}
	
}