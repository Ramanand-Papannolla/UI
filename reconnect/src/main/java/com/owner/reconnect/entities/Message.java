package com.owner.reconnect.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE", schema = "LOCAL")
public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private UserDetails userDetailsByReceiverFk;
	private UserDetails userDetailsBySenderFk;
	private String channel;
	private String messageBody;
	private LocalDateTime sentTime;
	private LocalDateTime deliveryTime;
	private Boolean deliveryStatus;
	private LocalDateTime readTime;

	public Message() {
	}

	public Message(UserDetails userDetailsByReceiverFk,
			UserDetails userDetailsBySenderFk, String messageBody,
			LocalDateTime sentTime, LocalDateTime deliveryTime) {
		this.userDetailsByReceiverFk = userDetailsByReceiverFk;
		this.userDetailsBySenderFk = userDetailsBySenderFk;
		this.messageBody = messageBody;
		this.sentTime = sentTime;
		this.deliveryTime = deliveryTime;
	}

	public Message(UserDetails userDetailsByReceiverFk,
			UserDetails userDetailsBySenderFk, String channel,
			String messageBody, LocalDateTime sentTime,
			LocalDateTime deliveryTime, Boolean deliveryStatus,
			LocalDateTime readTime) {
		this.userDetailsByReceiverFk = userDetailsByReceiverFk;
		this.userDetailsBySenderFk = userDetailsBySenderFk;
		this.channel = channel;
		this.messageBody = messageBody;
		this.sentTime = sentTime;
		this.deliveryTime = deliveryTime;
		this.deliveryStatus = deliveryStatus;
		this.readTime = readTime;
	}

	@SequenceGenerator(name = "messageId_Generator", sequenceName = "messageIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messageId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVER_FK", nullable = false)
	public UserDetails getUserDetailsByReceiverFk() {
		return this.userDetailsByReceiverFk;
	}

	public void setUserDetailsByReceiverFk(UserDetails userDetailsByReceiverFk) {
		this.userDetailsByReceiverFk = userDetailsByReceiverFk;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "SENDER_FK", nullable = false)
	public UserDetails getUserDetailsBySenderFk() {
		return this.userDetailsBySenderFk;
	}

	public void setUserDetailsBySenderFk(UserDetails userDetailsBySenderFk) {
		this.userDetailsBySenderFk = userDetailsBySenderFk;
	}

	@Column(name = "CHANNEL", length = 20)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "MESSAGE_BODY", nullable = false, length = 200)
	public String getMessageBody() {
		return this.messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	@Column(name = "SENT_TIME", nullable = false, length = 11)
	public LocalDateTime getSentTime() {
		return this.sentTime;
	}

	public void setSentTime(LocalDateTime sentTime) {
		this.sentTime = sentTime;
	}

	@Column(name = "DELIVERY_TIME", nullable = false, length = 11)
	public LocalDateTime getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Column(name = "DELIVERY_STATUS", length = 1)
	public Boolean getDeliveryStatus() {
		return this.deliveryStatus;
	}

	public void setDeliveryStatus(Boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Column(name = "READ_TIME", length = 11)
	public LocalDateTime getReadTime() {
		return this.readTime;
	}

	public void setReadTime(LocalDateTime readTime) {
		this.readTime = readTime;
	}

}
