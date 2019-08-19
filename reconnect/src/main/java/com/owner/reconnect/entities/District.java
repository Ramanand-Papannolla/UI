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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRICT", schema = "LOCAL")
public class District implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private State state;
	private String name;
	private String shortName;
	private Set<Mandal> mandal = new HashSet<Mandal>(0);

	public District() {
	}

	public District(State state, String name) {
		this.state = state;
		this.name = name;
	}

	public District(State state, String name, String shortName,
			Set<Mandal> mandal) {
		this.state = state;
		this.name = name;
		this.shortName = shortName;
		this.mandal = mandal;
	}

	@SequenceGenerator(name = "districtId_Generator", sequenceName = "districtIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "districtId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_STATE_FK", nullable = false)
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SHORT_NAME", length = 10)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Mandal> getMandal() {
		return this.mandal;
	}

	public void setMandal(Set<Mandal> mandal) {
		this.mandal = mandal;
	}

	@Override
	public String toString() {
		return "District [name=" + name + "]";
	}

}
