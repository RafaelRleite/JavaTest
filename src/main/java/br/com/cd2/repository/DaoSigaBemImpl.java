package br.com.cd2.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cd2.entidades.SigaBem;
import br.com.cd2.jpautil.JPAUtil;

@Named
public class DaoSigaBemImpl implements IDaoSigaBem<SigaBem>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private JPAUtil jpaUtil;

	@Override
	public SigaBem merge(SigaBem entidade) {
//		entityManager = jpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		SigaBem retorno = entityManager.merge(entidade);

		entityTransaction.commit();

		return retorno;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SigaBem> getList(Class<SigaBem> entidade) {
//		entityManager = jpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<SigaBem> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

		entityTransaction.commit();

		return retorno;
	}

	@Override
	public void delete(SigaBem entidade) {
//		entityManager = jpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = jpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
	}

}
