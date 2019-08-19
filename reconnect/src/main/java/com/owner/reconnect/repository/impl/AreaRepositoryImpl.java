package com.owner.reconnect.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.owner.reconnect.entities.District;
import com.owner.reconnect.entities.Mandal;
import com.owner.reconnect.entities.PlotDetails;
import com.owner.reconnect.entities.State;
import com.owner.reconnect.entities.SurveyNumDetails;
import com.owner.reconnect.entities.Village;
import com.owner.reconnect.repository.AreaRepository;

@Repository
public class AreaRepositoryImpl implements AreaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<State> findAllStates() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<State> criteriaQuery = cb.createQuery(State.class);
		Root<State> root = criteriaQuery.from(State.class);
		CriteriaQuery<State> all = criteriaQuery.select(root);
		TypedQuery<State> query = this.entityManager.createQuery(all);
		return query.getResultList();
	}

	@Override
	public List<District> findAllDistrictsByState(Long stateId) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<District> criteriaQuery = cb.createQuery(District.class);
		Root<District> root = criteriaQuery.from(District.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("state");
		predicates.add(cb.equal(path, stateId));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<District> query = this.entityManager
				.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<Mandal> findAllMandalsByDistrict(Long districtId) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Mandal> criteriaQuery = cb.createQuery(Mandal.class);
		Root<Mandal> root = criteriaQuery.from(Mandal.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("district");
		predicates.add(cb.equal(path, districtId));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Mandal> query = this.entityManager
				.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<Village> findAllVillagesByMondal(Long mondalId) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Village> criteriaQuery = cb.createQuery(Village.class);
		Root<Village> root = criteriaQuery.from(Village.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("mandal");
		predicates.add(cb.equal(path, mondalId));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Village> query = this.entityManager
				.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<SurveyNumDetails> findAllSurveyNumsByVillage(Long villageId) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<SurveyNumDetails> criteriaQuery = cb
				.createQuery(SurveyNumDetails.class);
		Root<SurveyNumDetails> root = criteriaQuery
				.from(SurveyNumDetails.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("village");
		predicates.add(cb.equal(path, villageId));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<SurveyNumDetails> query = this.entityManager
				.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<PlotDetails> findAllPlotsBySurveyNum(Long surveyId) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<PlotDetails> criteriaQuery = cb
				.createQuery(PlotDetails.class);
		Root<PlotDetails> root = criteriaQuery.from(PlotDetails.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("surveyNumDetails");
		predicates.add(cb.equal(path, surveyId));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<PlotDetails> query = this.entityManager
				.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public State findSateById(long stateId) {
		State state = this.entityManager.find(State.class, stateId);
		return state;
	}

	@Override
	public District findDistrictById(long districtId) {
		final District district = this.entityManager.find(District.class,
				districtId);
		return district;
	}

	@Override
	public Mandal findMandalById(long mandalId) {
		final Mandal mandal = this.entityManager.find(Mandal.class, mandalId);
		return mandal;
	}

	@Override
	public Village findVillageById(long villageId) {
		final Village village = this.entityManager.find(Village.class,
				villageId);
		return village;
	}

	@Override
	public SurveyNumDetails findSurveyById(Long id) {
		final SurveyNumDetails survey = this.entityManager.find(
				SurveyNumDetails.class, id);
		return survey;
	}

	@Override
	public PlotDetails findPlotById(Long id) {
		final PlotDetails plot = this.entityManager.find(PlotDetails.class, id);
		return plot;
	}

	@Override
	public PlotDetails addPlotDetails(PlotDetails newPlot) {
		this.entityManager.persist(newPlot);
		return newPlot;
	}

}
