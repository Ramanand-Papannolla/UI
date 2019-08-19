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
@Table(name = "VILLAGE", schema = "LOCAL")
public class Village implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Mandal mandal;
	private String name;
	private String shortName;
	private Set<SurveyNumDetails> surveyNumDetails = new HashSet<SurveyNumDetails>(
			0);

	public Village() {
	}

	public Village(Mandal mandal, String name) {
		this.mandal = mandal;
		this.name = name;
	}

	public Village(Mandal mandal, String name, String shortName,
			Set<SurveyNumDetails> surveyNumDetails) {
		this.mandal = mandal;
		this.name = name;
		this.shortName = shortName;
		this.surveyNumDetails = surveyNumDetails;
	}

	@SequenceGenerator(name = "villageId_Generator", sequenceName = "villageIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "villageId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VILLAGE_MANDAL_FK", nullable = false)
	public Mandal getMandal() {
		return this.mandal;
	}

	public void setMandal(Mandal mandal) {
		this.mandal = mandal;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "village")
	public Set<SurveyNumDetails> getSurveyNumDetails() {
		return this.surveyNumDetails;
	}

	public void setSurveyNumDetails(Set<SurveyNumDetails> surveyNumDetails) {
		this.surveyNumDetails = surveyNumDetails;
	}

	@Override
	public String toString() {
		return "Village [name=" + name + "]";
	}

}
