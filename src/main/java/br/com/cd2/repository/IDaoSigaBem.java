package br.com.cd2.repository;

import java.util.List;

import br.com.cd2.entidades.SigaBem;

public interface IDaoSigaBem<E> {

	SigaBem merge(E entidade);

	List<SigaBem> getList(Class<E> entidade);

	void delete(E entidade);

}
