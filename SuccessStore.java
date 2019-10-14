package com.blogger.rest.store;

import java.util.HashMap;
import java.util.Map;

public class SuccessStore {
	static public Map<String, String> getSuccessMessage(Integer id) {
		Map<String, String> resp = new HashMap<String, String>();

		resp.put("id", String.valueOf(id));
		resp.put("message", "success");

		return resp;
	}
}
