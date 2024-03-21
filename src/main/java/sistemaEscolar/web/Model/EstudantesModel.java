package sistemaEscolar.web.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="estudantes")
public class EstudantesModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String nome;
	private String matricula;
	private long identidade;
	private LocalDate nacimento ;
	private int idade;
	
	@ManyToOne
	@JoinColumn
	private CursosModel cursos;
	
	
	
	
	
	public String getCursos() {
		return cursos.getNome();
	}



	public int getIdade() {
		LocalDate dataHoje = LocalDate.now();
		 idade=Period.between(this.nacimento, dataHoje).getYears();
		return idade ;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public long getIdentidade() {
		return identidade;
	}
	public void setIdentidade( long identidade) {
		this.identidade = identidade;
	}
	public LocalDate getNacimento() {
		return nacimento;
	}
	public void setNacimento(LocalDate nacimento) {
		this.nacimento = nacimento;
	}



	public void setCursos(CursosModel cursosModel) {
		this.cursos=cursosModel;
		
	}

	
	
	
	
	
	
	
}
