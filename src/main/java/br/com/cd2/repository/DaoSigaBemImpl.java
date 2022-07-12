package br.com.cd2.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cd2.entidades.SigaBem;

public class DaoSigaBemImpl implements IDaoSigaBem {

	@Inject
	EntityManager entityManager;

	@Override
	public SigaBem Merge(SigaBem entidade) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		SigaBem retorno = entityManager.merge(entidade);

		entityTransaction.commit();

		return retorno;

	}

	@Override
	public List<SigaBem> getList(SigaBem entidade) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<SigaBem> retorno = entityManager.createQuery("SigaBem.DaoGenerico", SigaBem.class).getResultList();

		entityTransaction.commit();

		return retorno;
	}

	@Override
	public void Delete(SigaBem entidade) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.remove(entidade);

		entityTransaction.commit();
	}

}
