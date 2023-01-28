package com.sbapi.project.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

//import org.apache.commons.codec.binary.Hex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommUtil {
	
	/**
	 * 서버IP를 리턴 하는 함수입니다.
	 * @return 현재 동작 서버IP
	 */
	public static String getLocalServerIp(){
		String ip = null;
		try {
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en;		
			en = NetworkInterface.getNetworkInterfaces();
		
				while(en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				if (ni.isLoopback())
					continue;
		
				Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
				while(inetAddresses.hasMoreElements()) { 
					InetAddress ia = inetAddresses.nextElement();
					if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
						ip = ia.getHostAddress();
						isLoopBack = false;
						break;
					}
				}
				if (!isLoopBack)
					break;
			}
		} catch (SocketException e) {
			log.error("SocketException Error");
		}
		return ip;
	}
	
	/**
	 * ROOT경로정보를 가져온다.
	 * @return 경로
	 */
	public static String getRootPath(){
		int index = CommUtil.class.getResource("").getPath().indexOf("classes/");
		String rootPath = CommUtil.class.getResource("").getPath().substring(0, index);
		if(log.isDebugEnabled()) {
			log.debug("rootPath :: "+ rootPath);
		}
		return rootPath;
	}
	
	/**
	 * Map 을 json object로 변경한다.
	 * @return JSONObject
	 */
	public static JsonObject convertMapToJson(Map<String, Object> map) {
		JsonObject jsonObj = new JsonObject();
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			jsonObj.addProperty(key, value.toString());
		}
		return jsonObj;
	}
	
	/**
	 * Map 을 json array로 변경한다.
	 * @return String
	 */
	public static String convertListMapToJson(List<Map<String, String>> map) {
		Gson gson = new Gson();
		return gson.toJson(map);
	}
    
}