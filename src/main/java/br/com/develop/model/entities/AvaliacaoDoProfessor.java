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
@Table(name = "tb_avaliacao_do_professor")
public class AvaliacaoDoProfessor implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assiduidade;
    private String disciplina;
    private String sociabilidade;
    private String responsabilidade;
    private String iniciativa;

    @OneToOne
    private Aluno aluno;
    
    @ManyToOne
    private Orientador orientador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getAssiduidade() {
		return assiduidade;
	}

	public void setAssiduidade(String assiduidade) {
		this.assiduidade = assiduidade;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSociabilidade() {
		return sociabilidade;
	}

	public void setSociabilidade(String sociabilidade) {
		this.sociabilidade = sociabilidade;
	}

	public String getResponsabilidade() {
		return responsabilidade;
	}

	public void setResponsabilidade(String responsabilidade) {
		this.responsabilidade = responsabilidade;
	}

	public String getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(String iniciativa) {
		this.iniciativa = iniciativa;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
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
		AvaliacaoDoProfessor other = (AvaliacaoDoProfessor) obj;
		return Objects.equals(id, other.id);
	}
    
}
    