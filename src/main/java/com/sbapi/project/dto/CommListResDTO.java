package com.sbapi.project.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 공통 목록리스트 포함 응답 DTO
 * @author 조남훈
 *
 */
@Data
public class CommListResDTO {
	
	private String resultCode;				//결과코드
	private String resultMessage;			//결과메시지내용
	private String nextPageYn;				//다움페이지여부
	private List<Map<String, Object>> resultList;			//결과목록
	
}