package ca.pfroy.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ca.pfroy.domain.BaseEntity;

@SuppressWarnings("unchecked")
@Stateless
public class RepositoryService {

//	@PersistenceContext
//	private EntityManager em =  Persistence.createEntityManagerFactory("unitName").createEntityManager();

	@PersistenceContext
	private EntityManager em;

	public enum SortOrder {
		ASC, DESC
	}
	
	/*
	 * Update or Insert the entity.
	 * */
	public BaseEntity save(BaseEntity entity) {
		BaseEntity b = null;
		if (entity.isNew()) {
			em.persist(entity);
			b = entity;
		} else {
			b = em.merge(entity);
		}
		return b;
	}
	
	public void deleteById(@SuppressWarnings("rawtypes") Class clazz, Long id) {
		Object entity = em.find(clazz, id);
		em.remove(entity);
	}
	
	public <T> T find(Class<T> clazz, long id) {
		return em.find(clazz, id);
	}
	
	public <T> List<T> findAll(Class<T> clazz) {
		return em.createQuery("SELECT e FROM " + clazz.getName() + " e ORDER BY e.id").getResultList();
	}
	
	public <T> List<T> fullTextSearch(Class<T> clazz, String[] properties, String value, int maxResults) {
		String query = String.format("SELECT e FROM %s e WHERE ", clazz.getName());
		for (String property : properties) {
			query += String.format("UPPER(e.%s) LIKE ", property) + "'%" + value.toUpperCase() + "%' OR ";
		}
		query = query.substring(0, query.length() - 4);
		Query q = em.createQuery(query);
		q.setMaxResults(maxResults);
		List<T> result = q.getResultList();
		return result;
	}
	
	public <T> T findOneByProperty(Class<T> clazz, String property, Object value) {
		String queryString = "SELECT e FROM " + clazz.getName() + " e WHERE e." + property + "=:propertyValue";
		Query query = em.createQuery(queryString);
		query.setParameter("propertyValue", value);
		query.setMaxResults(1);
		List<T> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}
	

	public EntityManager getEm() {
		return em;
	}
	
}
