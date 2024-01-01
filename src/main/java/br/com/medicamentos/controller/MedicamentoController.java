package br.com.medicamentos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.medicamentos.model.Medicamento;
import br.com.medicamentos.service.MedicamentoService;
import br.com.medicamentos.utility.Message;
import br.com.medicamentos.utility.NegocioException;

@Named(value ="medicamentoMB")
@ViewScoped
//@ManagedBean
public class MedicamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	//@Dependent
	private Medicamento medicamento;
	
	@Inject
	private MedicamentoService service;
	
	private List<Medicamento> medicamentos;
	
	private String tipoCadastro = "SAVE";
	
	private String controlado = "SIM";
   
	/// :: Carregar este metodo toda vez que a classe for construida.
	@PostConstruct
	public void carregar() {
		medicamento.setControlado("SIM");
		medicamentos = service.findAll();
	}
	
	public void changeButton() {
		tipoCadastro = "UPDATE";
	}
	
	public void cancelar() {
		/// :: Apos salvar limpar formulario
		medicamento = new Medicamento();
		
		/// :: atualizar nome do botão
		tipoCadastro = "SAVE";
		
		/// :: Atualizar a lista de medicamentos
		carregar();
	}
	
	public void adicionar () {
		
		try {
			service.salvar(medicamento);

			Long id = medicamento.getId();
			String nome = medicamento.getNome();
			
			/// :: Apos salvar limpar formulario
			medicamento = new Medicamento();
			
			/// :: atualizar nome do botão
			tipoCadastro = "SAVE";
			
			/// :: Atualizar a lista de medicamentos
			carregar();
			
			if(id == null ) {
				Message.info(nome + " foi salvo com sucesso!");				
			} else {
				Message.info(nome + " foi atualizado com sucesso!");	
			}
			
		} catch (NegocioException e) {
			Message.error(e.getMessage());
		}
		catch (Exception e) {
			Message.error(e.getMessage());
		}
	}
	
	public void excluir() {
		
		try {
			service.remover(medicamento);

			/// :: Atualizar a lista de medicamentos
			carregar();
			
			Message.info(medicamento.getNome() + " foi excluido com sucesso!");
			
		} catch (NegocioException e) {
			Message.error(e.getMessage());
		}
		catch (Exception e) {
			Message.error(e.getMessage());
		}
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public String getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(String tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	public String getControlado() {
		return controlado;
	}

	public void setControlado(String controlado) {
		this.controlado = controlado;
	}	
	
	
}
