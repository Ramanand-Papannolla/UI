package com.owner.reconnect.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "USER_DETAILS", schema = "LOCAL")
public class UserDetails implements
		org.springframework.security.core.userdetails.UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String emailid;
	private String mobileNumber;
	private boolean connected;
	private Set<Roles> roles = new HashSet<Roles>(0);
	private Set<PlotDetails> plotDetails = new HashSet<PlotDetails>(0);
	private Set<Message> messageForReceiverFk = new HashSet<Message>(0);
	private DocumentInfo documentInfo;
	private Set<SurveyNumDetails> surveyNumDetails = new HashSet<SurveyNumDetails>(
			0);
	private Set<Message> messageForSenderFk = new HashSet<Message>(0);

	public Collection<? extends GrantedAuthority> authorities = new HashSet<>();

	public UserDetails() {
	}

	public UserDetails(String userName, String firstName, String lastName,
			String password, boolean connected) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.connected = connected;
	}

	public UserDetails(String userName, String firstName, String lastName,
			String password, String emailid, String mobileNumber,
			boolean connected, Set<Roles> roles, Set<PlotDetails> plotDetails,
			Set<Message> messageForReceiverFk, DocumentInfo documentInfo,
			Set<SurveyNumDetails> surveyNumDetails,
			Set<Message> messageForSenderFk) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailid = emailid;
		this.mobileNumber = mobileNumber;
		this.connected = connected;
		this.roles = roles;
		this.plotDetails = plotDetails;
		this.messageForReceiverFk = messageForReceiverFk;
		this.documentInfo = documentInfo;
		this.surveyNumDetails = surveyNumDetails;
		this.messageForSenderFk = messageForSenderFk;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
	@SequenceGenerator(name = "userIdGenerator", sequenceName = "userIdGenerator", allocationSize = 1)
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 25)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 25)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAILID", length = 50)
	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Column(name = "MOBILE_NUMBER", length = 10)
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Transient
	public boolean getConnected() {
		return this.connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLES_RSHIP", schema = "LOCAL", joinColumns = { @JoinColumn(name = "USER_ID_FK", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID_FK", nullable = false, updatable = false) })
	public Set<Roles> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	public Set<PlotDetails> getPlotDetails() {
		return this.plotDetails;
	}

	public void setPlotDetails(Set<PlotDetails> plotDetails) {
		this.plotDetails = plotDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetailsByReceiverFk")
	public Set<Message> getMessageForReceiverFk() {
		return this.messageForReceiverFk;
	}

	public void setMessageForReceiverFk(Set<Message> messageForReceiverFk) {
		this.messageForReceiverFk = messageForReceiverFk;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	public DocumentInfo getDocumentInfo() {
		return this.documentInfo;
	}

	public void setDocumentInfo(DocumentInfo documentInfo) {
		this.documentInfo = documentInfo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_SURVEY_NUM_RSHIP", schema = "LOCAL", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SURVEY_ID", nullable = false, updatable = false) })
	public Set<SurveyNumDetails> getSurveyNumDetails() {
		return this.surveyNumDetails;
	}

	public void setSurveyNumDetails(Set<SurveyNumDetails> surveyNumDetails) {
		this.surveyNumDetails = surveyNumDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetailsBySenderFk")
	public Set<Message> getMessageForSenderFk() {
		return this.messageForSenderFk;
	}

	public void setMessageForSenderFk(Set<Message> messageForSenderFk) {
		this.messageForSenderFk = messageForSenderFk;
	}

	@Transient
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	@Transient
	public String getUsername() {
		return this.userName;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
