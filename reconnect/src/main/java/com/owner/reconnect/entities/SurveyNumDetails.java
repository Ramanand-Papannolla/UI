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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SURVEY_NUM_DETAILS", schema = "LOCAL")
public class SurveyNumDetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Village village;
	private String surveyNumber;
	private String name;
	private DocumentInfo documentInfo;
	private Set<PlotDetails> plotDetails = new HashSet<PlotDetails>(0);
	private Set<UserDetails> userDetails = new HashSet<UserDetails>(0);

	public SurveyNumDetails() {
	}

	public SurveyNumDetails(Village village, String surveyNumber) {
		this.village = village;
		this.surveyNumber = surveyNumber;
	}

	public SurveyNumDetails(Village village, String surveyNumber, String name,
			DocumentInfo documentInfo, Set<PlotDetails> plotDetails,
			Set<UserDetails> userDetails) {
		this.village = village;
		this.surveyNumber = surveyNumber;
		this.name = name;
		this.documentInfo = documentInfo;
		this.plotDetails = plotDetails;
		this.userDetails = userDetails;
	}

	@SequenceGenerator(name = "surveyNumId_Generator", sequenceName = "surveyNumIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surveyNumId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "SURVEY_NUM_VILLAGE_FK", nullable = false)
	public Village getVillage() {
		return this.village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	@Column(name = "Survey_NUMBER", nullable = false, length = 20)
	public String getSurveyNumber() {
		return this.surveyNumber;
	}

	public void setSurveyNumber(String surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyNumDetails")
	public DocumentInfo getDocumentInfo() {
		return this.documentInfo;
	}

	public void setDocumentInfo(DocumentInfo documentInfo) {
		this.documentInfo = documentInfo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyNumDetails")
	public Set<PlotDetails> getPlotDetails() {
		return this.plotDetails;
	}

	public void setPlotDetails(Set<PlotDetails> plotDetails) {
		this.plotDetails = plotDetails;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "surveyNumDetails")
	public Set<UserDetails> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "SurveyNumDetails [name=" + name + "]";
	}

}
