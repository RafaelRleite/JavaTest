package br.com.cd2.repository;

import java.util.List;

import br.com.cd2.entidades.SigaBem;

public interface IDaoSigaBem {

	SigaBem Merge(SigaBem entidade);

	List<SigaBem> getList(SigaBem entidade);

	void Delete(SigaBem entidade);

}
