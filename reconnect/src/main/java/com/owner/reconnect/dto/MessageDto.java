package com.owner.reconnect.dto;

import java.time.LocalDateTime;

public class MessageDto {
	private Long id;
	private UserDetailsDto sender;
	private UserDetailsDto receiver;
	private String channel;
	private String messageBody;
	private LocalDateTime sentTime;
	private LocalDateTime deliveryTime;
	private Boolean deliveryStatus;
	private LocalDateTime readTime;

	public Long getId() {
		return id;
	}

	public String getChannel() {
		return channel;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public LocalDateTime getSentTime() {
		return sentTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public Boolean getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public void setSentTime(LocalDateTime sentTime) {
		this.sentTime = sentTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setDeliveryStatus(Boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDateTime getReadTime() {
		return readTime;
	}

	public void setReadTime(LocalDateTime readTime) {
		this.readTime = readTime;
	}

	public UserDetailsDto getSender() {
		return sender;
	}

	public UserDetailsDto getReceiver() {
		return receiver;
	}

	public void setSender(UserDetailsDto sender) {
		this.sender = sender;
	}

	public void setReceiver(UserDetailsDto receiver) {
		this.receiver = receiver;
	}
}
