package com.prolosys.rfid.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.prolosys.rfid.common.constants.LogEnum;

public class UtilsPLS {
	
	public static String[] abc = new String[]{"-","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	public static String yyyyMMdd = "yyyy-MM-dd";
	public static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat(yyyyMMdd);

	public static String HHmmss = "HH:mm:ss";
	public static SimpleDateFormat HHmmssFormat = new SimpleDateFormat(HHmmss);
	
	public static String dateToStringFormat(Date date, String stringFormat) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat(stringFormat);
		return yyyyMMddFormat.format(date);
	}
	
	public static String stringToNumberString(String string) {
		String result;
		try {
			result = Long.toString(Long.parseLong(string));
		} catch (Exception e) {
			result  = string;
		}
		return result;
	}
	
	public static Date stringToDate(String stringDate, String originFormat) {
				
		Date date=null;
		try {
			if (StringUtils.isNotBlank(stringDate) && !StringUtils.equals("0000-00-00",stringDate) && stringDate!=null && !StringUtils.equals("0000-00-00 00:00:00",stringDate)) {
				date = new Date((new SimpleDateFormat(originFormat).parse(stringDate).getTime()));
			}
		} catch (Exception e) {}
		
		return date;
	}
	
	public static Time stringToTime(String stringTime, String originFormat) {
		
		Time date=null;
		try {
			if (StringUtils.isNotBlank(stringTime) && StringUtils.isNotBlank(stringTime) && stringTime!=null) {
				date= new Time((new SimpleDateFormat(originFormat).parse(stringTime)).getTime());
			}
		} catch (Exception e) {}
		
		return date;
	}
	
	public static boolean ping(String host) {

		boolean result = false;

		InetAddress ping;
		
		try {
			ping = InetAddress.getByName(host);
			
			
			if (ping.isReachable(1000)) {
				result = true;
			}
		} catch (IOException ex) {}
		
		return result;

	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	public static Double proccessingTime(Long startTime) {
		
		Double finalTime = (double) (System.currentTimeMillis()-startTime)/1000; 
		
		return finalTime;
	}
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int stringToInteger(String string) {
		
		int result = 0;
		
		try {
			result = Integer.parseInt(string);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
		
	}
	
	
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();

		byte[] data = new byte[s.length() / 2];

		for (int i = 0; i < len; i = i + 2) {
			try {
				data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return data;
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public static String convertStringToHexString(String str) {

		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}

	public static String convertStringHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character

			if (decimal == 0) {

			} else {
				sb.append((char) decimal);
			}
			temp.append(decimal);
		}

		return sb.toString();
	}
	
	
	public static  String getLENUMFromTagId(String tagId) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(tagId);
		stringBuilder.delete(0, 6);

		try {
			Long lenum = Long.parseLong(stringBuilder.toString());
			return Long.toString(lenum);
		} catch (Exception e) {
			return null;
		}
	
	}

	public static String getMTCFromTagId(String tagId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(UtilsPLS.convertStringHexToString(tagId));

		try {
			stringBuilder.delete(0, stringBuilder.length() - 7);
		} catch (Exception e) {
			stringBuilder.setLength(0);
		}

		return stringBuilder.toString();

	}
	
	public static String buildTagEpc(String prefix, String UA, int length) {
		
		StringBuilder tagUA = new StringBuilder();

		int ceros = length -(prefix.length() + UA.length());
		tagUA.append(prefix);
		for (int i = 0; i < ceros; i++) {
			tagUA.append("0");
		}
		tagUA.append(UA);

		return tagUA.toString();
		
	}
	
    //0, 333, 6 = 000333
    public static String competeStringToLeft (String character, String value, int maxlength){
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < maxlength-value.length(); i++) {
        	result.append(character);
		}
        result.append(value);
        return result.toString();
    }
    
    
    
    public static JSONArray jsonObjectoToJsonArray(Object jsonObject) {
    	JSONArray result = new JSONArray();
    	if(jsonObject==null) {
    		
    	}else if(jsonObject instanceof JSONObject) {
    		result.put(jsonObject);
    	}else if(jsonObject instanceof JSONArray) {
    		result=(JSONArray) jsonObject;
    	}
    	return result;
    	
    }
    
    
    public static Object castValue(Object value) {
    	Object result = null;
    	if(value instanceof String) {
    		result = (String) value;
    	}else if(value instanceof Double) {
    		result = (Double) value;
    	}else if(value instanceof BigDecimal) {
    		result = (BigDecimal) value;
    	}
		return result;
    	
    }
    

	public static void logAttributesToRequest(HttpServletRequest request, List<Object> attributes) {
		
		request.setAttribute(LogEnum.MODULE.name(), attributes.get(0));
		request.setAttribute(LogEnum.ACTION.name(), attributes.get(1));
		request.setAttribute(LogEnum.DATA.name(), 	attributes.get(2));
		request.setAttribute(LogEnum.DESCRIPTION.name(), attributes.get(3));
		
	}
	
	public static JSONArray convertJsonObjectToArrayObject(JSONObject objectSAP, String first, String second) {
		
		JSONArray objectList = new JSONArray();
		
		Object object = objectSAP.getJSONObject(first).get(second);
				
		if(object instanceof JSONObject) {
			objectList.put(objectSAP.getJSONObject(first).getJSONObject(second));
		}else if(object instanceof JSONArray) {
			objectList = objectSAP.getJSONObject(first).getJSONArray(second);
		}
		return objectList;
	}


}
