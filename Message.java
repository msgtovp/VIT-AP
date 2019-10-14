package com.blogger.rest.model;

public class Message {

	private Integer messageId;
	private String subjectName;
	private String description;
	private String authorName;
	private Long createdTime;

	public Message() {
		super();
	}

	public Message(Integer messageId, String subjectName, String description, String authorName, Long createdTime) {
		super();
		this.messageId = messageId;
		this.subjectName = subjectName;
		this.description = description;
		this.authorName = authorName;
		this.createdTime = createdTime;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

}
