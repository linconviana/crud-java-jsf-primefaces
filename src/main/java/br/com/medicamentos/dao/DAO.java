package br.com.medicamentos.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.medicamentos.model.Base;

public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static EntityManager manager = ConnectionFactory.getEntityManeger();
	
	/// :: Buscar por id
	public T findById(Class<T> entity, Long id) {
		return manager.find(entity, id);
	}
	
	/// :: Salvar e Atualizar
	public void save(T entity) {
		try {
			
			manager.getTransaction().begin();
			
			if(entity.getId() == null) {
				manager.persist(entity);
			} else {
				manager.merge(entity);
			}
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	/// :: Salvar e Atualizar
	public void delete(Class<T> entity, Long id) {
		
		T findEntity = findById(entity, id);
		
		try {
			
			manager.getTransaction().begin();
			
			manager.remove(findEntity);
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	/// :: https://www.youtube.com/watch?v=2wQW1ZPFlk0&list=PLnOrFdw5rkTyj3km618OLh5nCatSELRFh&index=45
	@SuppressWarnings("unchecked")
	public List<T> findAll(String jpql){
		Query query = manager.createQuery(jpql);
		return query.getResultList();
	}
}
