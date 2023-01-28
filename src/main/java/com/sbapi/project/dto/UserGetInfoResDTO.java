package com.sbapi.project.dto;

import lombok.Data;

/**
 * 회원정보조회 응답 DTO
 * @author 조남훈
 *
 */
@Data
public class UserGetInfoResDTO {
	
	private String resultCode;				//결과코드
	private String resultMessage;			//결과메시지내용
	private String userExistYn;				//회원존재여부
	private String userId;					//회원ID
	private String userNm;					//회원명
	private String phoneNum;				//휴대폰번호
	private String emailId;					//이메일주소
	
}