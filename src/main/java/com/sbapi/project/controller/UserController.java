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
import com.sbapi.project.dto.UserGetLoginDTO;
import com.sbapi.project.dto.UserGetLoginResDTO;
import com.sbapi.project.dto.UserRegUserDTO;
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
	 * 회원정보조회 API - /user/getInfo
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
			result.setUserId(resultInfo.get("user_Id") !=null ? resultInfo.get("user_Id").toString() : "");
			result.setUserNm(resultInfo.get("user_Nm") !=null ? resultInfo.get("user_nm").toString() : "");
			result.setPhoneNum(resultInfo.get("user_phoneNum") !=null ? resultInfo.get("user_phoneNum").toString() : "");
			result.setEmailId(resultInfo.get("user_email") !=null ? resultInfo.get("user_email").toString() : "");
			
		}
		
		/*** [결과응답 셋팅] ***/
		result.setResultCode("0000");
		result.setResultMessage("정상 처리");
		result.setUserExistYn(userRegYn);
		
		return result;
	}
	/**
	 * 회원가입 API - /user/regUser
	 * @Desc - 회원 가입 처리합니다.
	 * @param header, param
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/regUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public CommResDTO regUser(@RequestHeader Map<String, Object> header, @RequestBody UserRegUserDTO param) throws ApiException {
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ UserController getInfo header : " + header.toString());
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ UserController getInfo param : " + param.toString());
		 
		/*** [Default 변수 선언] ***/
		CommResDTO result = new CommResDTO();
		Map<String, Object> reqParam = new HashMap<String, Object>();
		
		/*** [파라미터 유효성 체크] ***/
		//0. 필수 파라미터 체크
		if(!header.containsKey("apikey") || StrUtil.nvl(header.get("apikey").toString()).isEmpty() || !_API_KEY_.equals(StrUtil.nvl(header.get("apikey")))) {
			throw new ApiException(ExceptionEnum.API_KEY_EXCEPTION);
		}
		if(StrUtil.nvl(param.getUserId()).isEmpty() || StrUtil.nvl(param.getUserPassword()).isEmpty()) {
			throw new ApiException(ExceptionEnum.REQUIRE_PARAM_EXCEPTION);
		}
		
		/*** [비즈니스 로직 처리] ***/
		//0. 비즈니스 변수 선언
		
		//1. 회원정보조회
		reqParam.clear();
		reqParam.put("userId", param.getUserId());
		String userRegYn = service.selectUserRegYn(reqParam);	//--회원등록여부조회
		
		if("N".equals(userRegYn)) {
			//1.1. 등록된 회원인 경우
			reqParam.clear();
			reqParam.put("userId", param.getUserId());
			reqParam.put("userPassword", param.getUserPassword());
			
			service.insertUserInfo(reqParam);	//--회원가입 처리
		
			/*** [결과응답 셋팅] ***/
			result.setResultCode("0000");
			result.setResultMessage("정상 처리");
			
		}else {
			/*** [결과응답 셋팅] ***/
			result.setResultCode("9999");
			result.setResultMessage("이미 회원ID가 존재함.");
		}
		
		return result;
	}
	
	
	/**
	 * 회원로그인 API - /user/getLogin
	 * @Desc - 회원 로그인 처리합니다.
	 * @param header, param
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/getLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public UserGetLoginResDTO getLogin(@RequestHeader Map<String, Object> header, @RequestBody UserGetLoginDTO param) throws ApiException {
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ UserController getInfo header : " + header.toString());
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ UserController getInfo param : " + param.toString());
		 
		/*** [Default 변수 선언] ***/
		UserGetLoginResDTO result = new UserGetLoginResDTO();
		Map<String, Object> reqParam = new HashMap<String, Object>();
		
		/*** [파라미터 유효성 체크] ***/
		//0. 필수 파라미터 체크
		if(!header.containsKey("apikey") || StrUtil.nvl(header.get("apikey").toString()).isEmpty() || !_API_KEY_.equals(StrUtil.nvl(header.get("apikey")))) {
			throw new ApiException(ExceptionEnum.API_KEY_EXCEPTION);
		}
		if(StrUtil.nvl(param.getUserId()).isEmpty() || StrUtil.nvl(param.getUserPwd()).isEmpty()) {
			throw new ApiException(ExceptionEnum.REQUIRE_PARAM_EXCEPTION);
		}
		
		/*** [비즈니스 로직 처리] ***/
		//0. 비즈니스 변수 선언
		
		//1. 회원로그인
		reqParam.clear();
		reqParam.put("userId", param.getUserId());
		reqParam.put("userPwd", param.getUserPwd());
		String userRegYn = service.selectUserLogin(reqParam);	//--회원로그인
		
		/*** [결과응답 셋팅] ***/
		if("Y".equals(userRegYn)) {
			result.setResultCode("0000");
			result.setResultMessage("정상 처리");
		}else {
			result.setResultCode("9999");
			result.setResultMessage("로그인 실패");
		}
		
		return result;
	}
	
}