package com.runyee.agdhome.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
	public static Integer convertStrToInt(String str){
		Integer val = 0;
		try {
			val = Integer.parseInt(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return val;
	}

	public static Integer maxInt(int length){
		String max_str = "999999999";
		int max = 999999999;
		if(length<=max_str.length()){
			max = ConvertUtil.convertStrToInt(max_str.substring(0,length));
		}
		return max;
	}

	public static float convertStrToFloat(String str){
		float val = 0.0f;
		try {
			val = Float.parseFloat(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return val;
	}

	public static BigDecimal convertStrToBigDecimal(String str){
		BigDecimal val =BigDecimal.ZERO;
		try {
			val = new BigDecimal(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return val;
	}

	public static boolean isEmpty(String str){
		boolean result = true;
		if(str!=null&&!"".equals(str.trim())){
			result = false;
		}
		return result;
	}

	public static boolean isZeroEmpty(String str){
		boolean result = true;
		if(str!=null&&!"".equals(str.trim())&&!"0".equals(str.trim())){
				result = false;
		}
		return result;
	}

	public static String objToString(Object obj){
		String val = "";
		if(obj!=null){
			if(obj instanceof Long){
				val = ((Long)obj).toString();
			}else if(obj instanceof BigDecimal){
				val = ((BigDecimal)obj).toString();
			}else if(obj instanceof Integer){
				val = ((Integer)obj).toString();
			}else if(obj instanceof Double){
				val = ((Double)obj).toString();
			}else if(obj instanceof Float){
				val = ((Float)obj).toString();
			}else if(obj instanceof Short){
				val = ((Short)obj).toString();
			}else if(obj instanceof String){
				val =  (String) obj;
			}
		}
		return val;
	}

	public static void main(String[] args){
		List<String> rights = new ArrayList<>();
		rights.add("33");
		rights.add("44");
		rights.add("33");
		rights.add("44");
		rights.add("33");
		rights.add("44");
		String ids = StringUtils.join(rights.toArray(), ",");
		System.out.println(ids);
	}
	
}
