package com.sbapi.project.sess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	
    /**
     * Http세션에 Session 세팅
     * @param obj
     */
    public static void setSession(HttpServletRequest request, SbapiSession sbapiSess) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SysConst.SESSION_ID, sbapiSess);
    }
    /**
     * 세션객체에 값 세팅
     * @param obj
     */
    public static void setSession(HttpServletRequest request, String strKey, Object objVal) {
    	HttpSession session = request.getSession(true);
    	session.setAttribute(strKey, objVal);
    }
    /**
     * 세션객체에서 값을 가져옴
     * @param request
     * @param strKey
     * @return 
     */
    public static Object getSession(HttpServletRequest request, String strKey) {
    	HttpSession session = request.getSession(true);
        return session.getAttribute(strKey);
    }
    /**
     * 세션객체를 리턴합니다.
     * @param request
     * @param strKey
     * @return SbapiSession
     */
    public static SbapiSession getSession(HttpServletRequest request) {
    	return (SbapiSession)getSession(request, SysConst.SESSION_ID);
    }
    /**
     * 세션객체 제거
     * @param request
     * @param strKey
     */
    public static void removeSession(HttpServletRequest request, String strKey) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(strKey);
        session.removeAttribute(SysConst.SESSION_ID);
    }
    /**
     * 로그인여부
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
    	return isLogin(request.getSession(true));
    }
    /**
     * 로그인여부
     * @return
     */
    public static boolean isLogin(HttpSession session) {
        boolean isLogin = false;

        try {
            if(session != null) {
            	Object obj = session.getAttribute(SysConst.SESSION_ID);
                if(obj != null && obj instanceof SbapiSession) {
                    isLogin = true;
                }
            }
        } catch(IllegalStateException ie) {
            isLogin = false;
        } catch(Exception e) {
            isLogin = false;
        }

        return isLogin;
    }
    /**
     * 로그아웃 처리
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request){
    	HttpSession session = request.getSession(true);
    	
    	//세션정보 삭제
    	if(session != null) {
    		session.removeAttribute(SysConst.SESSION_ID);
    		session.invalidate();
    	}
    }
}
