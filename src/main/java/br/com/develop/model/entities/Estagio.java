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
@Table(name = "tb_estagio")
public class Estagio implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataInicio;
    private String dataFim;
    private String cargaHoraria;
    private String status;

    @OneToOne
    private Aluno alunoEstagio;
    
    @ManyToOne
    private Orientador orientadorEstagio;
    
    @ManyToOne
    private Empresa empresaEstagio;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Aluno getAlunoEstagio() {
        return alunoEstagio;
    }

    public void setAlunoEstagio(Aluno alunoEstagio) {
        this.alunoEstagio = alunoEstagio;
    }

    public Orientador getOrientadorEstagio() {
        return orientadorEstagio;
    }

    public void setOrientadorEstagio(Orientador orientadorEstagio) {
        this.orientadorEstagio = orientadorEstagio;
    }

    public Empresa getEmpresaEstagio() {
        return empresaEstagio;
    }

    public void setEmpresaEstagio(Empresa empresaEstagio) {
        this.empresaEstagio = empresaEstagio;
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
		Estagio other = (Estagio) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
