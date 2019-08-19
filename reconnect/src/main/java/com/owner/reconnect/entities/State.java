package com.owner.reconnect.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STATE", schema = "LOCAL")
public class State implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String shortName;
	private Set<District> district = new HashSet<District>(0);

	public State() {
	}

	public State(String name) {
		this.name = name;
	}

	public State(String name, String shortName, Set<District> district) {
		this.name = name;
		this.shortName = shortName;
		this.district = district;
	}

	@SequenceGenerator(name = "stateId_Generator", sequenceName = "stateIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stateId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SHORT_NAME", length = 20)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<District> getDistrict() {
		return this.district;
	}

	public void setDistrict(Set<District> district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "State [name=" + name + "]";
	}

}
