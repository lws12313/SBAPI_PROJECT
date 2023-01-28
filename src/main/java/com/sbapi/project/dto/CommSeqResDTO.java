package com.sbapi.project.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 공통 일련번호 포함 응답 DTO
 * @author 조남훈
 *
 */
@Data
public class CommSeqResDTO {
	
	private String resultCode;				//결과코드
	private String resultMessage;			//결과메시지내용
	private BigDecimal seqNo;				//일련번호
	
}