package com.blogger.rest.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blogger.rest.model.Message;

public class MessageStore {
	
	static private Map<Integer, Message> messages = new HashMap<Integer, Message>();
	
	static {
		messages.put(100, new Message(100, "Subject 1", "Description 1 goes here", "vijay", System.currentTimeMillis()));
		messages.put(101, new Message(101, "Subject 2", "Description 2 goes here", "akash", System.currentTimeMillis()));
		messages.put(102, new Message(102, "Subject 3", "Description 3 goes here", "mahes", System.currentTimeMillis()));
		messages.put(103, new Message(103, "Subject 4", "Description 4 goes here", "arjun", System.currentTimeMillis()));
	}
	
	public List<Message> getMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(Integer messageId) {
		return messages.get(messageId);
	} 

}
