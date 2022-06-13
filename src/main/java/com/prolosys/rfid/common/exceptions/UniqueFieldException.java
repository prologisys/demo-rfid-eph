package com.prolosys.rfid.common.exceptions;

import java.util.HashMap;
import java.util.Map;

public class UniqueFieldException extends Exception{

	private static final long serialVersionUID = -3744258691006428466L;

	public Map< String, Map<String, String>> error = new HashMap<String, Map<String, String>>();
	
	public UniqueFieldException(String data) {
		super(data);
	}
	
	public UniqueFieldException(String field, String message) {
		super(message);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("exists", message);
		error.put(field, map);	
		
	}
	
	
}


