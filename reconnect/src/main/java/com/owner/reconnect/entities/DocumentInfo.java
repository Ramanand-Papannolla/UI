package com.owner.reconnect.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_INFO", schema = "LOCAL")
public class DocumentInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private SurveyNumDetails surveyNumDetails;
	private PlotDetails plotDetails;
	private UserDetails userDetails;
	private Blob document;
	private String name;
	private String type;

	public DocumentInfo() {
	}

	public DocumentInfo(Blob document) {
		this.document = document;
	}

	public DocumentInfo(SurveyNumDetails surveyNumDetails,
			PlotDetails plotDetails, UserDetails userDetails, Blob document,
			String name, String type) {
		this.surveyNumDetails = surveyNumDetails;
		this.plotDetails = plotDetails;
		this.userDetails = userDetails;
		this.document = document;
		this.name = name;
		this.type = type;
	}

	@SequenceGenerator(name = "docId_Generator", sequenceName = "documentIdGenerator")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docId_Generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SURVAY_FK")
	public SurveyNumDetails getSurveyNumDetails() {
		return this.surveyNumDetails;
	}

	public void setSurveyNumDetails(SurveyNumDetails surveyNumDetails) {
		this.surveyNumDetails = surveyNumDetails;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLOT_FK")
	public PlotDetails getPlotDetails() {
		return this.plotDetails;
	}

	public void setPlotDetails(PlotDetails plotDetails) {
		this.plotDetails = plotDetails;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_FK")
	public UserDetails getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Column(name = "DOCUMENT", nullable = false)
	public Blob getDocument() {
		return this.document;
	}

	public void setDocument(Blob document) {
		this.document = document;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
