package sistemaEscolar.web.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sistemaEscolar.web.Dtos.ProfessoresRecordDto;
import sistemaEscolar.web.Model.CursosModel;
import sistemaEscolar.web.Model.ProfessoresModel;
import sistemaEscolar.web.Repositories.CursosRepository;
import sistemaEscolar.web.Repositories.ProfessoresRepository;

@RestController
public class ProfessoresController {
	
	
	
	@Autowired
	ProfessoresRepository professoresRepository;
	@Autowired
	CursosRepository cursosRepository;
	
	
	
	@PostMapping("/professores")
	public ResponseEntity<Object> cadastraProfessor(@RequestBody @Valid ProfessoresRecordDto professoresdTO){
		
		
		Optional<CursosModel> cursos = cursosRepository.findById(professoresdTO.cursoId());
		if(cursos.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
			  
		}
		
		var professoresModel = new ProfessoresModel();
		
		professoresModel.setCursos(cursos.get());
		
		BeanUtils.copyProperties(professoresdTO, professoresModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(professoresRepository.save(professoresModel));
		
		
	}
	
	
	@GetMapping("/professores")
	public ResponseEntity<List<ProfessoresModel>> listarProfessores(){
		return ResponseEntity.status(HttpStatus.OK).body(professoresRepository.findAll()) ;
	}
	
	@GetMapping("/professores/{id}")
	public ResponseEntity<Object> listarUmProfessor(@PathVariable(value="id") int id ){
		
		var professoresModel = professoresRepository.findById(id);
		
		if(professoresModel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor nao encontrado...");
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(professoresModel.get());
	}
	
	@PutMapping("/professores/{id}")
	public ResponseEntity<Object> alterarProfessor(@Valid @RequestBody ProfessoresRecordDto professoresDto, @PathVariable(value="id") int id ){
		
		var professoresModel = professoresRepository.findById(id);
		
		if(professoresModel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor nao encontrado...");
			
		}
		
		BeanUtils.copyProperties(professoresDto, professoresModel);
		
		return  ResponseEntity.status(HttpStatus.OK).body(professoresRepository.save(professoresModel.get()));
	}
	
	
	@DeleteMapping("/professores/{id}")
	public ResponseEntity<Object> deletarProfessor( @PathVariable(value= "id") int id){
		
		var professoresModel= professoresRepository.findById(id);
		if(professoresModel.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor nao encontrado...");
			
		}
		
		professoresRepository.delete(professoresModel.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso") ;
		
		
	}
	
	
	
	
	

}









