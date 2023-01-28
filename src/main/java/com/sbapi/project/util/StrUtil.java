package com.sbapi.project.util;


import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrUtil {
	
	private StrUtil() {
	}
	
	/**
     * null 값 대체
     * @param objValue
     * @return
     */
    public static String nvl(Object objValue) {
    	String strReturn = "";

    	if(objValue == null) { 
    		strReturn = "";
    	} else {
    		strReturn = objValue.toString();
    	}

    	return strReturn;
    }
    /**
     * null 값 대체
     * @param objValue
     * @return
     */
    public static String nvl(Object objValue, String strDefValue) {
    	String strReturn = "";

    	if(objValue == null) {
    		strReturn = strDefValue;
    	} else {
    		strReturn = objValue.toString();
    	}

    	return strReturn;
    }
	
    /**
     * null, "" 값 대체
     * @param objValue
     * @return
     */
	public static String nvb(Object paramObject, String paramString) {
		String str = "";
		if ("".equals(nvl(paramObject)))
			str = paramString;
		else {
			str = nvl(paramObject);
		}
		return str;
	}
	
	/**
     * "", null 값으로 대체
     * @param objValue
     * @return null : paramValue
     */
	public static String blank2null(Object paramObject) {
		String str = "";
		if ("".equals(nvl(paramObject)))
			str = null;
		else {
			str = nvl(paramObject);
		}
		return str;
	}
	
	/**
	 * 입력된 문자열에서 왼쪽의 0 을 입력받은 갯수만큼만 남기고 제거, 전화번호 지역번호, 국번 검사에 사용 함<br>
	 * 지역번호 0 하나만 남김, cnt=1, 국번 앞의 0을 모두 삭제 cnt=0
	 * @param str String
	 * @param cnt int : 0을 남길 갯수
	 * @return String객체
	 */
	public static String delPreZero(String paramString, int paramInt) {
		int i = 0;
		for (int j = 0; (j < paramString.length() - 1)
				&& (paramString.substring(j, j + 1).equals("0")); j++) {
			i++;
		}

		if (i > paramInt) {
			return paramString.substring(i - paramInt);
		}
		return paramString;
	}
	/**
	 * 입력된 문자열에서 첫번째 연속되는 숫자만 반환, 전화번호 검사에 사용 함<br>
	 * @param str String
	 * @return String객체
	 */
	public static String checkNumber(String paramString) {
		String str;
		int j;
		int k;
		try {
			return paramString;
		} catch (NumberFormatException localNumberFormatException1) {
			int i = 0;
			str = paramString;
			j = 0;
			k = 0;

			for (int m = 0; m < str.length() - 1; m++) {
				try {
					if (i == 0) {
						i = 1;
						j = m;
					}
				} catch (NumberFormatException localNumberFormatException2) {
					if (i != 0) {
						k = m;
						break;
					}
				}
			}
			if (j == k)
				return "";
		}
		return str.substring(j, k);
	}
	/**
	 * 주어진 길이만큼 '0'을 채운다.
	 * @param nVal
	 * @param length
	 * @return
	 */
	public static String fillZero(double paramDouble, int paramInt) {
		String str1 = "";
		String str2 = "";
		for (int i = 0; i < paramInt; i++) {
			str1 = str1 + "0";
		}

		if (str1 != null) {
			str2 = new DecimalFormat(str1).format(paramDouble);
		}

		return str2;
	}
	/**
	 * 주어진 길이만큼 space를 오른쪽에 채운다.
	 * @param strVal
	 * @param nLength
	 * @return
	 */
	public static String fillSpace(String paramString, int paramInt) {
		return getPadString(paramString, paramInt, " ", 1);
	}
	/**
	 * 문자열 내에서 원하는 문자열 replace 하기
	 * @param oldString
	 * @param from
	 * @param to
	 * @return
	 */
	public static String replace(String paramString1, String paramString2,
			String paramString3) {
		String str = paramString1;
		int i = 0;
		while ((i = str.indexOf(paramString2, i)) > -1) {
			StringBuffer localStringBuffer = new StringBuffer(str.substring(0,
					i));
			localStringBuffer.append(paramString3);
			localStringBuffer.append(str.substring(i + paramString2.length()));
			str = localStringBuffer.toString();
			i++;
		}

		return str;
	}
	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * default 로 왼쪽에 blank 로 채움
	 * @param str String 원본 데이터
	 * @param len int    최종 데이터 길이
	 * @return String객체
	 */
	public static String getPadString(String str, int len) {
	    return getPadString(str, len, " ", 0);
	}
	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * default 로 왼쪽에 채움
	 * @param str String 원본 데이터
	 * @param len int    최종 데이터 길이
	 * @param padstr String 채울 문자열
	 * @return String객체
	 */
	public static String getPadString(String str, int len, String padstr) {
	    return getPadString(str, len, padstr, 0);
	}

	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * 입력된 lr 에 따라 왼쪽 또는 오른쪽에 채워준다 default 왼쪽
	 * @param str String 원본 데이터
	 * @param len int    최종 데이터 길이
	 * @param padstr String 채울 문자열, 하나의 문자로 하는것을 권장
	 * @param lr int 왼쪽에 채울지 오른쪽에 채울지 지정, 0:left(default), 1:right
	 * @return String객체
	 */
	public static String getPadString2(String str, int len, String padstr, int lr) {
		int totlength = 0;
		for(int i = 0; i < str.length(); i++) {
			if( str.substring(i, i+1).getBytes().length > 1 )
				totlength += 2;
			else
				totlength += 1;
		}

		while( totlength < len ) {
			if(lr == 0) {
				str = padstr + str;
			} else {
				str = str + padstr;
			}

			totlength++;
		}


		return str;
	}

	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * 입력된 lr 에 따라 왼쪽 또는 오른쪽에 채워준다 default 왼쪽
	 * @param str String 원본 데이터
	 * @param len int    최종 데이터 길이
	 * @param padstr String 채울 문자열, 하나의 문자로 하는것을 권장
	 * @param lr int 왼쪽에 채울지 오른쪽에 채울지 지정, 0:left(default), 1:right
	 * @return String객체
	 */
	public static String getPadString(String str, int len, String padstr, int lr) {
	    String rtnstr = "";
	    str = nvl(str);
	    if(str.length() >= len) {
	        return str;
	    } else {
	        if(lr == 1) {
	            rtnstr = str + padstr;
	        } else {
	            rtnstr = padstr + str;
	        }

	        if(rtnstr.length() < len) {
	            return getPadString(rtnstr, len, padstr, lr);
	        } else {
	            return rtnstr;
	        }
	    }
	}
	/**
	 * 한글깔끔히 짜르기.<br>
	 * 한글인코딩 처리하시고 한글,영문 모두 1byte로 계산해서 처리하시면 됩니다.<br>
	 */
	public static String strClip(String inputStr, int limit) {
		return strCut(inputStr, null, limit, 0, false, false); 
	}
	/*
	 * 깔끔히 문자를 자릅니다.
	    "가나다라" 에서 2바이트까지 자르고 싶을경우 strCut("가나다라", null, 2, 0, true, true); 처럼 하시면 됩니다.
		 => 결과 : "가"
		"가나다라" 에서 "다"라는 키워드 기준에서 2바이트까지 자르고싶을경우 strCut("가나다라", "다", 2, 0, true, true); 처럼 하시면 됩니다.
		 => 결과 : "다"
		"가나다라" 에서 "라"라는 키워드 기준으로 그 이전의 4바이트까지 포함하여 6바이트까지 자르고 싶을 경우 strCut("가나다라", "라", 6, 4, true, true); 처럼 하시면 됩니다.
		 => 결과 : "나다라"
		"가나다라" 에서 3바이트를 자를 경우
		 => 결과 : "가"
		"가a나다라" 에서 3바이트를 자를 경우
		 => 결과 : "가a"
		"가나다라" 에서 "나" 키워드 기준으로 이전 1바이트 포함하여 4바이트까지 자를 경우
		 => 결과 : "나"
	 */
	public static String strCut(String szText, String szKey, int nLength, int nPrev, boolean isNotag, boolean isAdddot){  // 문자열 자르기

		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;
		Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE); // 태그제거 패턴

		if (isNotag) {
			r_val = p.matcher(r_val).replaceAll("");
		} // 태그 제거
		r_val = r_val.replaceAll("&amp;", "&");
		r_val = r_val.replaceAll("(!/|\r|\n|&nbsp;)", ""); // 공백제거

		try {
			byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관
			if (szKey != null && !szKey.equals("")) {
				nLengthPrev = (r_val.indexOf(szKey) == -1) ? 0 : r_val.indexOf(szKey); // 일단 위치찾고
				nLengthPrev = r_val.substring(0, nLengthPrev).getBytes("MS949").length; // 위치까지길이를 byte로 다시 구한다
				nLengthPrev = (nLengthPrev - nPrev >= 0) ? nLengthPrev - nPrev : 0; // 좀 앞부분부터 가져오도록한다.
			}

			// x부터 y길이만큼 잘라낸다. 한글안깨지게.
			int j = 0;

			if (nLengthPrev > 0)
				while (j < bytes.length) {
					if ((bytes[j] & 0x80) != 0) {
						oF += 2;
						rF += 3;
						if (oF + 2 > nLengthPrev) {
							break;
						}
						j += 3;
					} else {
						if (oF + 1 > nLengthPrev) {
							break;
						}
						++oF;
						++rF;
						++j;
					}
				}

			j = rF;

			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + 2 > nLength) {
						break;
					}
					oL += 2;
					rL += 3;
					j += 3;
				} else {
					if (oL + 1 > nLength) {
						break;
					}
					++oL;
					++rL;
					++j;
				}
			}

			r_val = new String(bytes, rF, rL, "UTF-8"); // charset 옵션

			if (isAdddot && rF + rL + 3 <= bytes.length) {
				r_val += "...";
			} // ...을 붙일지말지 옵션
		} catch (UnsupportedEncodingException e) {
			log.error("strCut error");
		}

		return r_val;
	}
	/**
	 * 하나의 긴 String을 주어진 integer array의 순서대로 tokenize
	 * @param delim
	 * @param str
	 * @return
	 */
	public static String[] parsing(int[] paramArrayOfInt, String paramString) {
		int j = 0;

		byte[] arrayOfByte = paramString.getBytes();
		String[] arrayOfString = new String[paramArrayOfInt.length];

		for (int i = 0; i < paramArrayOfInt.length; i++) {
			String str = new String(arrayOfByte, j, paramArrayOfInt[i]);
			arrayOfString[i] = str;
			j += paramArrayOfInt[i];
		}

		return arrayOfString;
	}
	
	public static String[] parsing2(int[] paramArrayOfInt, String paramString) {
		String[] arrayOfString = new String[paramArrayOfInt.length];
		byte[] arrayOfByte = paramString.getBytes();

		int i = 0;
		int j = 0;
		for (int k = 0; k < paramArrayOfInt.length; k++) {
			int m = 0;
			int n = 0;
			for (int i1 = i; i1 < paramString.length(); i1++) {
				if (paramString.substring(i1, i1 + 1).getBytes().length > 1) {
					n += 3;
					m += 2;
				} else {
					n++;
					m++;
				}
				i++;

				if (m == paramArrayOfInt[k]) {
					arrayOfString[k] = new String(arrayOfByte, j, n);
					j += n;
					break;
				}
			}
		}

		return arrayOfString;
	}
	/**
	 * UTF-8 인코딩의 한글 2byte로 계산된 Length를 리턴한다.
	 * @param str
	 * @return
	 */
	public static String UTFSubLen(String paramString, int paramInt1,
			int paramInt2) {
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 0;
		int n = 0;
		for (int i1 = 0; i1 < paramString.length(); i1++) {
			if (k == paramInt1) {
				i = m;
				k = 0;
				m = 0;
				n = 1;
			}

			if ((n != 0) && (k == paramInt2)) {
				j = m;
				break;
			}

			if (paramString.substring(i1, i1 + 1).getBytes().length > 1) {
				m += 3;
				k += 2;
			} else {
				m++;
				k++;
			}
		}

		return new String(paramString.getBytes(), i, j);
	}
	/**
	 * UTF-8 인코딩의 한글 2byte로 계산된 Length를 리턴한다.
	 * @param str
	 * @return
	 */
	public static int getUTF8Length(String paramString) {
		int i = 0;
		for (int j = 0; j < paramString.length(); j++) {
			if (paramString.substring(j, j + 1).getBytes().length > 1)
				i += 2;
			else {
				i++;
			}
		}
		return i;
	}
	/**
	 * KSC5601 형식의 문자인지 검증한다.
	 */
	public static boolean isKSC5601(String paramString) {
		boolean i = false;
		try {
			i = paramString.equals(new String(paramString.getBytes("KSC5601"),
					"KSC5601")) ? true : false;
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			i = false;
		}

		return i;
	}
	/**
	 * 아스키코드문자 비교
	 * @param srcData
	 * @return
	 */
	public static boolean asciCheck(String paramString) {
		int i = 0;
		i = paramString.getBytes().length;

		byte[] arrayOfByte = paramString.getBytes();

		for (int j = 0; j < i; j++) {
			if ((48 > arrayOfByte[j]) || (arrayOfByte[j] > 57)) {
				return false;
			}
		}

		return true;
	}
	/**
	 * 한글영문혼합형 byte길이
	 * @param s
	 * @return
	 */
	public static int byteLength(String s) {
	    int useByteLength = 0;

	    for(int endCharIndex = 0; endCharIndex < s.length(); endCharIndex++) {
	        if(s.charAt( endCharIndex ) > 0x007F) {
	            useByteLength += 2;
	        } else {
	            useByteLength++;
	        }
	    }
	   
	    return useByteLength;
	}
	/**
	 * 숫자여부 판단
	 * @param str
	 * @return
	 */
	public static boolean isStringDouble(String paramString) {
		try {
			Double.parseDouble(paramString);
			return true;
		} catch (NumberFormatException localNumberFormatException) {
			return false;
		} catch (Exception localException) {
			return false;
		}
	}
	/**
	 * 숫자여부 판단(As-Is)
	 * @param str
	 * @return
	 */
	public static boolean isCheckNum(String str){
		boolean isNum = true;
		
		for(int i = 0 ; i < str.length() ; i++){
			char c = str.charAt(i);
			if(c < 0x30 || c > 0x39){
				isNum = false;
				break;
			}			
		}
		
		return isNum;
	}
	
	public static String srpad(String paramString, int paramInt, char paramChar) {
		return getPadString2(paramString, paramInt, String.valueOf(paramChar),
				1);
	}

	public static String rpad(String paramString, int paramInt, char paramChar) {
		return getPadString2(paramString, paramInt, String.valueOf(paramChar),
				1);
	}

	public static String slpad(String paramString, int paramInt, char paramChar) {
		return getPadString2(paramString, paramInt, String.valueOf(paramChar),
				0);
	}

	public static String lpad(String paramString, int paramInt, char paramChar) {
		return getPadString2(paramString, paramInt, String.valueOf(paramChar),
				0);
	}
	/**
     * DB 한글데이터 Charset 변경 
     * @param str
     * @return
     */
    public static String convDBValue(String str) {
    	return convString(str, "EUC-KR", "8859_1");
    }
    /**
     * Charset 변경
     * @param str
     * @param formCharset
     * @param toCharset
     * @return
     */
    public static String convString(String str, String formCharset, String toCharset) {
		try {
			str	= new String(str.getBytes(formCharset), toCharset);
		} catch (UnsupportedEncodingException e) {
			log.error("convString Data process is Error");
			str	= "";
		}
		
		return str;
    }
    
	public static String MaskPhoneNo2(String str) {
			
		if(str == null) return str;
		
		String phoneNo = str.trim();
		
		if(phoneNo == "") return str;
		else {
			if(phoneNo.length() >= 13) {
				return phoneNo.substring(0,9)+"****";
			} else {
				return phoneNo.substring(0,8)+"****";
			}
		}
	}
	public static String MaskPhoneNo(String str) {
		
		if(str == null) return str;
		
		String phoneNo = str.trim();
		
		if(phoneNo == "") return str;
		else {
			if(phoneNo.length() >= 11) {
				return phoneNo.substring(0,7)+"****";
			} else {
				return phoneNo.substring(0,6)+"****";
			}
		}
	}
	
	public static String substring(String str, int startIndex, int endIndex, String def){
		
		str = str.substring(startIndex, endIndex);
		
		return str;
	}
	
	public static String substringB(String str, int startIndex, int lenByte){ 
		String rtnValue = "";
		for (int i = startIndex ; i < str.length() ;i++) {
			try {
				if ( (rtnValue + str.substring(i, i+1)).getBytes("EUC-KR").length > lenByte ) {
					break;
				}
			} catch (UnsupportedEncodingException e) {
				log.error("substringB error");
			}
		
			rtnValue = rtnValue + str.substring(i, i+1);
		}
		
		return rtnValue;
	}

	/*************************
	 * 전각처리, 공백처리
	 *************************/
	public static String validFullChar(String str)
   	{
   		str = to2ByteConv(str);
   		
   	    StringBuffer sb = new StringBuffer();
   	 	char tmp ;
   	    for(int i = 0; i < str.length(); i++) {
   	    	tmp = str.charAt(i);
   	    	if (tmp >= 0x21 && tmp <= 0x7e){
				tmp = 0x20;
			}
			sb.append(tmp);
   	    }
   	    String validChar = sb.toString();
   	    validChar = validChar.replace("　　", "　");
   	    return validChar;
   	}
	
	//전각처리, 공백처리   :: (String str, int size) _size 만큼 패딩준다
	public static String validFullChar(String str, int _size)
   	{
   		str = to2ByteConv(str);
   		
   	    StringBuffer sb = new StringBuffer();
   	 	char tmp ;
   	    for(int i = 0; i < str.length(); i++) {
   	    	tmp = str.charAt(i);
   	    	if (tmp >= 0x21 && tmp <= 0x7e){
				tmp = 0x20;
			}
			sb.append(tmp);
   	    }
   	    String validChar = sb.toString();
   	    validChar = validChar.replace("　　", "　");
   	    
   	    int forCnt = validChar.length()-_size;
   	    if(forCnt < 0) {
   	    	String paddingStr = " ";
   	    	for(int i = 0; i<forCnt; i++) {
   	    		validChar += paddingStr;
   	    	}
   	    }
   	    return validChar;
   	}
	
	/**
     * 전각문자로 변경한다.
     * @param src 변경할값
     * @return String 변경된값
     */
    public static String to2ByteConv(String src)
    {
        // 입력된 스트링이 null인경우
        if (src == null) return "";
        
        StringBuffer strBuf = new StringBuffer();
        
        char c = 0;
        int nSrcLength = src.length();
        
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            
            // 영문이거나 특수 문자 일경우.
            if (c >= 0x21 && c <= 0x7e) {
                c += 0xfee0;
            }
            
            // 공백일경우
            else if (c == 0x20) {
                c = 0x3000;
            }
            
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        
        return strBuf.toString();
    }
    
    public static String stringToHex(String str){
		StringBuffer sbuf = new StringBuffer();
		for(int i = 0; i < str.length() ; i++) {
			sbuf.append(Integer.toHexString(str.charAt(i)));
		}
		return sbuf.toString();
	}
	
	public static String hexToString(String hexString){
		if(hexString.length() % 2 != 0) {
			if (log.isDebugEnabled()) log.debug("requires Even Number of chars");
			return "";
		}
		
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < hexString.length() - 1 ; i+=2){
			String output = hexString.substring(i, i+2);
			int decimal = Integer.parseInt(output, 16);
			buf.append((char)decimal);
		}
		
		return buf.toString();
	}
	
	/***
	 * 함 수 명 : lPadding
	 * 설    명 : 데이터 자리수만큼 왼쪽에 0을 채운다.
     */	
	public static String lPadding(String str, int len) {
	    for (int i=0; i < len; i++) {
	    	if (str.length() < len) str = "0" + str;
	    }	    
	    return str;     
	}
	
	/***
	 * 함 수 명 : rPadding
	 * 설    명 : 데이터 자리수만큼 오른쪽에 0을 채운다.
	 * 매개변수 :
     */	
	public static String rPadding(String str, int len) {
	    for (int i=0; i < len; i++) {
	    	if (str.length() < len) str = str + "0";
	    }	    
	    return str;     
	}
	
	/**
	 * 오른쪽 패딩함수
	 * @param source
	 * @param totLen
	 * @param pad
	 * @return
	 */
	public static String rightPadWith(String source, int totLen, char pad) {
		String rtnString = new String();
		String padStr = new String();
		byte[] byteSrc = source.getBytes();
		int byteLen = totLen - byteSrc.length;

		if (byteLen < 0) {
			return "";
		}
		for (int i = 0; i < byteLen; ++i) {
			padStr = padStr + pad;
		}
		rtnString = source + padStr;
		return rtnString;
	}
	/**
	 * 왼쪽패딩
	 * @param source
	 * @param totLen
	 * @param pad
	 * @return
	 */
	public static String leftPadWith(String source, int totLen, char pad) {
		String rtnString = new String();
		String padStr = new String();
		byte[] byteSrc = source.getBytes();
		int byteLen = totLen - byteSrc.length;

		if (byteLen < 0) {
			return "";
		}
		for (int i = 0; i < byteLen; ++i) {
			padStr = padStr + pad;
		}
		rtnString = padStr + source;
		return rtnString;
	}
	
	/**
	 * 휴대폰번호 체크(010만 허용)
	 * @param num
	 * @return
	 */
	public static boolean isMobileNo(String num){
		boolean isValid = true;
		if( null != num) num = num.replaceAll("-", "");
		String regex = "^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
		isValid = Pattern.matches(regex, num);
		return isValid;
	}
	/**
	 * 휴대폰번호 체크(010,011,016,017,018,019 허용)
	 * @param num
	 * @return
	 */
	public static boolean isMobileNo2(String num){ 
		boolean isValid = true;
		if( null != num) num = num.replaceAll("-", "");
		String regex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
		isValid = Pattern.matches(regex, num);
		return isValid;
	}
	/**
	 * 앞, 뒤, 중간등 모든 공백을 제거하여 리턴합니다.
	 * @param str
	 * @return
	 */
	public static String removeBlank(String str) {
		if(str == null) return "";
		String tmp = str;
		tmp = tmp.replaceAll(" ", "");
		tmp = tmp.replaceAll("\\p{Z}", "");
		tmp = tmp.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");

		return tmp;
	}
	/**
	 * Html 태그를 String으로 변환
	 * @param str
	 * @return
	 */
	public static String htmlTostr(String str) {
		if(str == null) {
			return "";
		}
		
		return str.replaceAll("&lt", "<")
				.replaceAll("&gt", ">")
				.replaceAll("&amp", "&")
				.replaceAll("&#35", "#")
				.replaceAll("&lsquo", "'")
				.replaceAll("&rsquo", "'")
				.replaceAll("&ldquo", "\"")
				.replaceAll("&rdquo", "\"");
	}
	
}