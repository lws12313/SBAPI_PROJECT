package com.sbapi.project.sess;


import java.io.Serializable;

import lombok.Data;

@Data
public class SbapiSession implements Serializable{ 
	private static final long serialVersionUID = 1L; // 시리얼UID
	
	private String userId = "";	  // 회원ID
	private String userNm = "";	  // 회원성명
	
}
