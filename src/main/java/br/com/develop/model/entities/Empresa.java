package br.com.develop.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author m
 */

@Entity
@Table(name = "tb_empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "empresaAluno")
    private List<Aluno> alunos = new ArrayList<>();
    
    @OneToMany(mappedBy = "empresaEstagio")
    private List<Estagio> Estagio = new ArrayList<>();
    
    @OneToMany(mappedBy = "empresa")
    private List<AvaliacaoDaEmpresa> avaliacoesDasEmpresas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Estagio> getEstagio() {
        return Estagio;
    }
    
    public int getQtdAlunos() {
    	return alunos.size();
    }
    
    public int getQtdAvaliacoes() {
    	return avaliacoesDasEmpresas.size();
    }
    
	public List<AvaliacaoDaEmpresa> getAvaliacoesDasEmpresas() {
		return avaliacoesDasEmpresas;
	}

	public void setAvaliacoesDasEmpresas(List<AvaliacaoDaEmpresa> avaliacoesDasEmpresas) {
		this.avaliacoesDasEmpresas = avaliacoesDasEmpresas;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setEstagio(List<Estagio> estagio) {
		Estagio = estagio;
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
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}    
    
    
}
