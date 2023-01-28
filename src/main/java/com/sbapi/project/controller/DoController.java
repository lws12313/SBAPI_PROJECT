package com.sbapi.project.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbapi.project.sess.SessionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Do Controller
 * @Desc 샘플 프로젝트 화면 매핑
 * @author 조남훈
 *
 */
@Slf4j
@Controller
public class DoController {
	
	/**
	 * 페이지 Controller (페이지 인터셉터 역할)
	 * @param session
	 * @param request
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/*.do", "/*/*.do"}, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pageDefault(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		String reqUri = request.getRequestURI();
		String reqPath = reqUri.substring(1, reqUri.indexOf(".do"));
		String redirectPath = null;
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ DoController pageDefault start reqUri : " + reqUri);
		log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ DoController pageDefault start reqPath : " + reqPath);
		
		//로그인페이지가 아닌 경우,
		/*
		if(!"/login/login.do".equals(reqUri)) {
			//로그인 여부 체크 후, 비로그인이면 로그인 페이지로 redirect
			if(!SessionManager.isLogin(request)) {
				reqPath = "login/login";
				redirectPath = "redirect:/login/login.do";
			}
		}
		*/
		
		//결과페이지 호출
		ModelAndView mv = new ModelAndView();
		mv.setViewName(redirectPath != null ? redirectPath : reqPath);
		mv.addObject("pageParam", param);
		mv.addObject("jspath", reqPath);
		
		return mv;
	}
	
	/**
	 * 봇 크롤링 막기
	 */
	@RequestMapping(value = "/robots.txt")
	@ResponseBody
	public void robotsBlock(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("User-agent: *\nDisallow: /\n");	//모든 경로에 대해 로봇 크롤링 접근 불허
		} catch (IOException e) {
			log.error("robots Exception", e.getMessage());
		}
	}
	
}