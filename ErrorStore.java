package com.blogger.rest.store;

import java.util.HashMap;
import java.util.Map;

public class ErrorStore {
	
	static public Map<String, String> getErrorResponse(Integer status, String message) {
		Map<String, String> resp = new HashMap<String, String>();
		
		resp.put("errorCode", String.valueOf(status));
		resp.put("errorMessage", message);
		
		return resp;
	}

}
