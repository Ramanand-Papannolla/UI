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

import com.owner.reconnect.entities.UserDetails;
import com.owner.reconnect.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserDetails registerUser(UserDetails user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public UserDetails findById(Long userId) {
		return entityManager.find(UserDetails.class, userId);
	}

	@Override
	public UserDetails findByEmailId(String userName) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetails> criteriaQuery = cb
				.createQuery(UserDetails.class);
		Root<UserDetails> root = criteriaQuery.from(UserDetails.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("userName");
		predicates.add(cb.equal(path, userName));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<UserDetails> query = this.entityManager
				.createQuery(criteriaQuery);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<UserDetails> findAll() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetails> criteriaQuery = cb
				.createQuery(UserDetails.class);
		Root<UserDetails> root = criteriaQuery.from(UserDetails.class);
		CriteriaQuery<UserDetails> all = criteriaQuery.select(root);
		TypedQuery<UserDetails> query = this.entityManager
				.createQuery(all);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}
}
