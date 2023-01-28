package com.sbapi.project.util;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 포매팅 관련 유틸
 * @author 조남훈
 *
 */
public class FormatUtil {

	public static final int _ACCOUNT_MASK_FINAL 	= 1;
	public static final int _ACCOUNT_MASK_MID 		= 2;
	public static final int _ACCOUNT_NOMASK_FINAL 	= 5;
	
    private FormatUtil() {

    }
    
    /**
     * 숫자에 컴마표기
     * @param nVal
     * @return
     */
    public static String getMoney(int nVal) {
        String displayForm = new DecimalFormat("#,###").format(nVal);
        return displayForm;
    }
    /**
     * 숫자에 컴마표기
     * @param nVal
     * @return
     */
    public static String getMoney(long nVal) {
        String displayForm = new DecimalFormat("#,###").format(nVal);
        return displayForm;
    }
    /**
     * 숫자에 컴마표기
     * @param nVal
     * @return
     */
    public static String getMoney(double nVal) {
        String displayForm = new DecimalFormat("#,###").format(nVal);
        return displayForm;
    }
    /**
     * 숫자에 컴마표기
     * @param strVal
     * @return
     */
    public static String getMoney(String strVal) {
        return getMoney(strVal,"0");
    }
    /**
     * 숫자에 컴마표기
     * @param strVal
     * @param defValue
     * @return
     */
    public static String getMoney(String strVal, String defVal){
    	if(null == strVal || "".equals(strVal.trim()) || "".equals(strVal.trim().replaceAll(",","")) ){
       		return defVal;
        }
        strVal = strVal.trim().replaceAll(",","");
        double nVal = Double.parseDouble(strVal);
        String displayForm = new DecimalFormat("#,###").format(nVal);
        return displayForm;
    }
    /**
     * 오늘날짜 가져오기
     * @return
     */
    public static String getCurDate() {
    	return getCurDateTime("yyyy-MM-dd");
    }
    /**
     * 오늘시간 가져오기
     * @return
     */
    public static String getCurTime() {
    	return getCurDateTime("HH:mm:ss");
    }
    /**
     * 오늘날짜시간 가져오기
     * @param format ex) yyyy-mm-dd HH:mm:ss
     * @return
     */
    public static String getCurDateTime(String format) {
    	Date currDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(currDate);
    }
    /**
     * 날짜포맷
     * @param strValue
     * @return
     */
    public static String getDate(String strValue) {
    	return getDateTime(strValue ,"yyyy-MM-dd");
    }
    /**
     * 날짜포맷
     * @param strValue
     * @return
     */
    public static String getDate(String strValue, String strFormat) {
    	return getDateTime(strValue ,strFormat);
    }
    /**
     * 시간포맷
     * @param strValue
     * @param strFormat
     * @return
     */
    public static String getTime(String strValue, String strFormat) {
        return getDateTime(strValue ,"HH:mm:ss");
    }
    /**
     * 날짜포맷
     * @param strValue
     * @param strFormat
     * @return
     */
    public static String getDateTime(String strValue, String strFormat) {
    	SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
    	Date parseDate;
		try {
			parseDate = new SimpleDateFormat("HH:mm:ss").parse(strValue);
			return sdf.format(parseDate);
			
		} catch (ParseException e) {
			return strValue;
		}
    }
    
    /**
     * 주민번호/사업자번호 뒷자리 * 처리한 포멧팅
     * @param strValue
     * @return
     */
    public static String rrnBrnMask(String strValue){
    	String rstValue = new String();
    	
    	if(null == strValue){ return ""; }
    	rstValue = strValue.trim().replaceAll("-","");

    	if(rstValue.length() == 10) {
        	rstValue = rstValue.substring(0,3) + "-"+ rstValue.substring(3,5)+"-*****";
        } else if(rstValue.length() == 13) {
        	rstValue = rstValue.substring(0,6) + "-*******";
        } else {
        	rstValue = strValue;
        }

        return rstValue;
    	
    }
    /**
     * 주민번호/사업자번호 포멧팅
     * @param strValue
     * @return
     */
    public static String rrnBrn(String strValue){
    	String rstValue = new String();
    	
    	if(null == strValue){ return ""; }
    	rstValue = strValue.trim().replaceAll("-","");

    	if(rstValue.length() == 10) {
        	rstValue = rstValue.substring(0,3) + "-"+rstValue.substring(3,5)+"-"+rstValue.substring(5);
        } else if(rstValue.length() == 13) {
        	rstValue = rstValue.substring(0,6) + "-"+rstValue.substring(6);
        } else {
        	rstValue = strValue;
        }

        return rstValue;
    }
    /**
     * 우편번호 포맷팅
     * @param strValue
     * @return
     */
    public static String zipCode(String strValue){
    	String rstValue = new String();
    	
    	if(null == strValue){ return ""; }
    	rstValue = strValue.trim().replaceAll("-","");

    	if(rstValue.length() == 6) {
        	rstValue = rstValue.substring(0,3) + "-"+rstValue.substring(3);
        } else {
        	rstValue = strValue;
        }

        return rstValue;
    }
    /**
     * 휴대폰번호 포맷팅
     * @param phoneNumber
     * @return
     */
    public static String makePhoneNumber(String phoneNumber) {
 	   String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
 	   
 	   if(!Pattern.matches(regEx, phoneNumber)) return null;
 	   
 	   return phoneNumber.replaceAll(regEx, "$1-$2-$3");
 	   
    }
    /**
     * 계좌번호
     * @param strValue
     * @return
     */
    public static String account(String strValue){
    	String rstValue = new String();
    	int[] format = null;
    	
    	if(null == strValue){ return ""; }
    	rstValue = strValue.trim().replaceAll("-","");

    	if(13 == strValue.length()){
			format = new int[]{2,1,2,8};
		}else if(14 == strValue.length()){
			format = new int[]{3,6,2,3};
		}else if(15 == strValue.length()){
			format = new int[]{5,3,2,5};
		}else if(16 == strValue.length()){
			format = new int[]{3,6,2,5};
		}else{
			return strValue;
		}
    	
		List<String> rArr = new ArrayList<String>();
		int startIdx = 0;
		
		for(int i=0 ; i<format.length ; i++){
			rArr.add(strValue.substring(startIdx, format[i]));
			startIdx += format[i];
		}
		
		rArr.add( strValue.substring(startIdx) );
		for(int i=0 ; i<rArr.size() ; i++)
		{
			if(i==0)
				rstValue = rArr.get(i);
			else
				rstValue += "-"+rArr.get(i);
		}
    	
        return rstValue;
    }
    /**
     * 계좌번호 인덱싱 Mask(123-123456-12-***) (신용카드 결제계좌번호)
     * @param strValue
     * @return
     */
    public static String accountMask(String strValue){
    	return accountMask(strValue, FormatUtil._ACCOUNT_NOMASK_FINAL);
    }
    /**
     * 계좌번호 인덱싱 (123-123456-**-123)
     * @param strValue
     * @param type 0:뒷 4자리만 *처리, 5:뒷 4자리만 숫자 표시
     * @return
     */
    public static String accountMask(String strValue, int type){
    	String rstValue = new String();
    	
    	if(null == strValue){ return ""; }
    	strValue = strValue.trim().replaceAll("-","");
    	
    	if(type == 5){
    		int len = strValue.length();
    		if(len > 4){
    			for(int i=0; i<len-4; i++){
    				rstValue += "*";
    			}
    			rstValue += strValue.substring(len-4);
    		}
    	}//End if(type == 5)
    	else{
    		if(strValue.length() > 4){
    			rstValue = strValue.substring(0, strValue.length()-4)+"****";
    		}
    	}//End else
    	
        return rstValue;
    }
    
    /**
     * 전화번호 포맷팅
     * @param strValue
     * @return
     */
   public static String getPhone(String strValue){
    	
    	String rstValue = new String();
    	String phoneRegExp = new String("(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})");
    	
    	if(null == strValue || "".equals(strValue)){ return ""; }
    	
    	strValue = getNumberOnly(strValue);	//숫자외의 문자를 제거
    	
    	if(!Pattern.matches(phoneRegExp, strValue)){
    		return strValue;
    	}
    	rstValue = strValue.replaceAll(phoneRegExp, "$1-$2-$3");
    	
    	return rstValue;
    }
    
    /**
     * 전화번호 Mask
     * @param strValue
     * @return
     */
    public static String getPhoneMask(String strValue){
	
    	String rstValue = new String();
    	String phoneRegExp = new String("(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})");
    	
    	if(null == strValue){ return ""; }
    	
    	strValue = getNumberOnly(strValue);	//숫자외의 문자를 제거
    	
    	if(!Pattern.matches(phoneRegExp, strValue)){
    		return strValue;
    	}
    	rstValue = strValue.replaceAll(phoneRegExp, "$1-$2-****");
    	
    	return rstValue;
	}
    
    /**
     * 사업자등록번호 포맷팅
     * @param strValue
     * @return
     */
   public static String getCorpRegNo(String strValue){
    	
    	String rstValue = new String();
    	
    	if("".equals(strValue) || null == strValue) return "";
    	rstValue = strValue.substring(0,3) + "-"+ strValue.substring(3,5) + "-" + strValue.substring(5);
    	
    	return rstValue;
    }
    

   /**
     * 0-9 까지의 숫자외의 값을 제거합니다.
     * @param strValue
     * @return
     */
    public static String getNumberOnly(String strValue){
    	
    	String noNumRegExp = new String("[^0-9]");
    	return strValue.replaceAll(noNumRegExp, "");
    }
    
    /**
     * 숫자에 컴마표기 (소수점 2자리)
     * @param strVal
     * @return
     */
    public static  String getDecimal(String strVal) {
        
    	strVal = getDecimal(strVal,2);
        return strVal;
    }
    
	/**
	 * 숫자에 컴마표기 (소수점자리)
	 * @param strVal
	 * @param decimal 소수점 자리수
	 * @return
	 */
    public static  String getDecimal(String strVal, int decimal) {
       
        DecimalFormat dFormatter = null;
        StringBuffer format = new StringBuffer();
        
        format.append("#,##0");
        
        if(0 < decimal){
        	format.append(".");
        }
        
        for(int i=0; i < decimal; i++){
        	format.append("0");
        }
        
        dFormatter  = new DecimalFormat(format.toString());
        
        strVal = dFormatter.format(Double.parseDouble(strVal));

        return strVal;
    }
    
    /**
     * 계좌번호 포매팅 함수
     * @param acct
     * @return
     */
    public static String getAcctForm(String acct) {
    	acct = acct.trim();
    	if (acct == null)
           return "";
        if (acct.length() < 11)
          return acct;
        if (acct.length() == 13)
           return acct.substring(0, 2) + "-" + acct.substring(2, 3) + "-" + acct.substring(3, 5) + "-" + acct.substring(5);
        if (acct.length() == 14)
           return acct.substring(0, 3) + "-" + acct.substring(3, 9) + "-" + acct.substring(9, 11) + "-" + acct.substring(11, 14);
        if (acct.length() == 15)
          return acct.substring(0, 5) + "-" + acct.substring(5, 8) + "-" + acct.substring(8, 10) + "-" + acct.substring(10, 15);
        if (acct.length() == 16) {
          return acct.substring(0, 3) + "-" + acct.substring(3, 9) + "-" + acct.substring(9, 11) + "-" + acct.substring(11, 16);
        }
   
        return acct;
	}
}