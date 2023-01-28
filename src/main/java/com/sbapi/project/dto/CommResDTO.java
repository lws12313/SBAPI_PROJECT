package com.sbapi.project.dto;

import lombok.Data;

/**
 * 공통 응답 DTO
 * @author 조남훈
 *
 */
@Data
public class CommResDTO {
	
	private String resultCode;				//결과코드
	private String resultMessage;			//결과메시지내용
	
}