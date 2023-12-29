package br.com.medicamentos.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.medicamentos.dao.DAO;
import br.com.medicamentos.model.Medicamento;
import br.com.medicamentos.utility.NegocioException;

public class MedicamentoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Medicamento> dao;
	
	public void salvar(Medicamento entity) throws NegocioException {
		
		if(entity.getNome().length() < 3) {
			throw new NegocioException("O nome do medicamento não pode ter menos que 3 caracteres");
		}
		
		dao.save(entity);
	}
	
	public void remover(Medicamento entity) throws NegocioException {
		dao.delete(Medicamento.class, entity.getId());
	}
	
	public List<Medicamento> findAll(){
		return dao.findAll("select m from Medicamento m order by m.nome");
	}
	
	public Medicamento findById(Medicamento entity) {
		return dao.findById(Medicamento.class, entity.getId());
	}
}
