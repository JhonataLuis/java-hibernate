package pos.java.maven.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pos.java.maven.hibernate.HibernateUtil;

public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	//MÉTODO PARA SALVAR UM ITEM NO BANCO DE DADOS
	public void salvar(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
		
	}
	
	//MÉTODO SALVA OU ATUALIZA UM DADO DO BANCO DE DADOS
	public E UpdateMerge(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		
		return entidadeSalva;
	}
	
	//MÉTODO PARA BUSCAR UM DADO NO BANCO PELO ID
	public E pesquisar(Long id, Class<E> entidade) {
		
		E e = (E) entityManager.find(entidade, id);
		return e;
	}
	
	//MÉTODO PARA REMOVER UM ITEM DO BANCO DE DADOS
	public void deletarPoId(E entidade) {
		
		Object id = HibernateUtil.getPrimaryKey(entidade);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.getClass();
		
		entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() + 
				" where id =" +id).executeUpdate();//FAZ DELETE
		
		transaction.commit();//GRAVA A ALTERAÇAO NO BANCO DE DADOS
	}
	
	public List<E> listar(Class<E> entidade){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
	}
	
	//AÇOES AVANÇADAS EM HIBERNATE
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
