package com.owner.reconnect.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PLOT_DETAILS", schema = "LOCAL")
public class PlotDetails implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private SurveyNumDetails surveyNumDetails;
	private UserDetails userDetails;
	private String plotNumber;
	private String name;
	private DocumentInfo documentInfo;

	public PlotDetails() {
	}

	public PlotDetails(SurveyNumDetails surveyNumDetails,
			UserDetails userDetails) {
		this.surveyNumDetails = surveyNumDetails;
		this.userDetails = userDetails;
	}

	public PlotDetails(SurveyNumDetails surveyNumDetails,
			UserDetails userDetails, String plotNumber, String name,
			DocumentInfo documentInfo) {
		this.surveyNumDetails = surveyNumDetails;
		this.userDetails = userDetails;
		this.plotNumber = plotNumber;
		this.name = name;
		this.documentInfo = documentInfo;
	}

	@SequenceGenerator(name = "plotId_Generator", sequenceName = "plotIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plotId_Generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLOT_SURVEY_NUM_FK", nullable = false)
	public SurveyNumDetails getSurveyNumDetails() {
		return this.surveyNumDetails;
	}

	public void setSurveyNumDetails(SurveyNumDetails surveyNumDetails) {
		this.surveyNumDetails = surveyNumDetails;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLOT_DTLS_USER_FK", nullable = false)
	public UserDetails getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Column(name = "PLOT_NUMBER", length = 30)
	public String getPlotNumber() {
		return plotNumber;
	}

	public void setPlotNumber(String plotNumber) {
		this.plotNumber = plotNumber;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PlotDetails [name=" + name + "]";
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plotDetails")
	public DocumentInfo getDocumentInfo() {
		return this.documentInfo;
	}

	public void setDocumentInfo(DocumentInfo documentInfo) {
		this.documentInfo = documentInfo;
	}

}
