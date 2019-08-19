package com.owner.reconnect.entities;

// Generated Jul 27, 2019 1:34:32 AM by Hibernate Tools 5.3.10.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES", schema = "LOCAL")
public class Roles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String roleName;
	private Set<UserDetails> userDetails = new HashSet<UserDetails>(0);

	public Roles() {
	}

	public Roles(String roleName, Set<UserDetails> userDetails) {
		this.roleName = roleName;
		this.userDetails = userDetails;
	}

	@SequenceGenerator(name = "roleId_Generator", sequenceName = "roleIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleId_Generator")
	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ROLE_NAME", length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLES_RSHIP", schema = "LOCAL", joinColumns = { @JoinColumn(name = "ROLE_ID_FK", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID_FK", nullable = false, updatable = false) })
	public Set<UserDetails> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

}
