package sistemaEscolar.web.Model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cursos")
public class CursosModel  implements Serializable {
 
	//un curso puede tener muchos profes, pero el profe puede estar en un curso
	//un curso puede tener muchos estudiantes, pero un estudiante puede tener un solo curso
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	
	private String nome;
	private String descricao;
	private String horario;
	private String localDeCurso;
	
	@OneToMany(mappedBy= "cursos", cascade = CascadeType.ALL) //esto establece la relacion de uno para muchos, el mapped by especifica el nombre de la variable del curso en las otras clases
	private Set<EstudantesModel> estudantes;//use set y no list porque el set no permite objetos duplicados

	@OneToMany(mappedBy= "cursos", cascade = CascadeType.ALL)
	private Set<ProfessoresModel> professores;
	
	
	
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getLocalDeCurso() {
		return localDeCurso;
	}
	public void setLocalDeCurso(String localDeCurso) {
		this.localDeCurso = localDeCurso;
	}
	public Set<ProfessoresModel> getProfessores() {
		return professores;
	}
	public Set<EstudantesModel> getEstudantes() {
		return estudantes;
	}
	
}
