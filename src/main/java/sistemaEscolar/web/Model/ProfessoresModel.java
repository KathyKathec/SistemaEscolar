package sistemaEscolar.web.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="professores")
public class ProfessoresModel implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private int id;
	
	
	private String nome;
	private long identidade;
	private String nacionalidade;
	private String siape;
	
	@ManyToOne
	@JoinColumn
	private CursosModel cursos;//este nombre debe ser el mismo del mapped by
	
	
	
	
	public String getCursos() {
		return cursos.getNome();
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
	public long getIdentidade() {
		return identidade;
	}
	public void setIdentidade(long identidade) {
		this.identidade = identidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getSiape() {
		return siape;
	}
	public void setSiape(String siape) {
		this.siape = siape;
	}
	public void setCursos(CursosModel cursosModel) {
		
		this.cursos=cursosModel;
	}
	
	
	
	
	
	
	
}
