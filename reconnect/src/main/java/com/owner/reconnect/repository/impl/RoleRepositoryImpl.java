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

import com.owner.reconnect.entities.Roles;
import com.owner.reconnect.repository.RoleRepository;
import com.owner.reconnect.role.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Roles findUserRole(Role role) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> criteriaQuery = cb.createQuery(Roles.class);
		Root<Roles> root = criteriaQuery.from(Roles.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Path<String> path = root.get("roleName");
		predicates.add(cb.equal(path, role.toString()));
		criteriaQuery
				.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Roles> query = this.entityManager.createQuery(criteriaQuery);
		return query.getResultList().stream().findFirst().get();
	}
}
