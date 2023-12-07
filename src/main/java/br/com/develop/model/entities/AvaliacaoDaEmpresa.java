package br.com.develop.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author m
 */

@Entity
@Table(name = "tb_avaliacao_da_empresa")
public class AvaliacaoDaEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rendimentoDeTrabalho;
    private String conhecimentos;
    private String cumprimentoDasTarefas;
    private String aprendizagem;
    private String desempenho;

    @OneToOne
    private Aluno aluno;
    
    @ManyToOne
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getRendimentoDeTrabalho() {
		return rendimentoDeTrabalho;
	}

	public void setRendimentoDeTrabalho(String rendimentoDeTrabalho) {
		this.rendimentoDeTrabalho = rendimentoDeTrabalho;
	}

	public String getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(String conhecimentos) {
		this.conhecimentos = conhecimentos;
	}

	public String getCumprimentoDasTarefas() {
		return cumprimentoDasTarefas;
	}

	public void setCumprimentoDasTarefas(String cumprimentoDasTarefas) {
		this.cumprimentoDasTarefas = cumprimentoDasTarefas;
	}

	public String getAprendizagem() {
		return aprendizagem;
	}

	public void setAprendizagem(String aprendizagem) {
		this.aprendizagem = aprendizagem;
	}

	public String getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(String desempenho) {
		this.desempenho = desempenho;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliacaoDaEmpresa other = (AvaliacaoDaEmpresa) obj;
		return Objects.equals(id, other.id);
	}
    
}
    