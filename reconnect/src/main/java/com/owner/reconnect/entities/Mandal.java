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
@Table(name = "MANDAL", schema = "LOCAL")
public class Mandal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private District district;
	private String name;
	private String shortName;
	private Set<Village> village = new HashSet<Village>(0);

	public Mandal() {
	}

	public Mandal(District district, String name) {
		this.district = district;
		this.name = name;
	}

	public Mandal(District district, String name, String shortName,
			Set<Village> village) {
		this.district = district;
		this.name = name;
		this.shortName = shortName;
		this.village = village;
	}

	@SequenceGenerator(name = "mandalId_Generator", sequenceName = "mandalIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mandalId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MONDAL_DISTRICT_FK", nullable = false)
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mandal")
	public Set<Village> getVillage() {
		return this.village;
	}

	public void setVillage(Set<Village> village) {
		this.village = village;
	}

	@Override
	public String toString() {
		return "Mandal [name=" + name + "]";
	}

}
