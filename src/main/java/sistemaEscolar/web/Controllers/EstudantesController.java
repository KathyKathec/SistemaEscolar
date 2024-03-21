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
import sistemaEscolar.web.Dtos.EstudantesRecordDto;
import sistemaEscolar.web.Model.CursosModel;
import sistemaEscolar.web.Model.EstudantesModel;
import sistemaEscolar.web.Repositories.CursosRepository;
import sistemaEscolar.web.Repositories.EstudantesRepository;

@RestController
public class EstudantesController {

	
	@Autowired
	EstudantesRepository repositorio;
	@Autowired
	CursosRepository cursosRepository;
	
	@PostMapping("/estudantes")
	public ResponseEntity<Object> salvarEstudante(@RequestBody @Valid EstudantesRecordDto estudantesDto ){
		
	
		// Busca o curso pelo ID fornecido
	    Optional<CursosModel> cursoOptional = cursosRepository.findById(estudantesDto.cursoId());
	    if (cursoOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
	    }
	    
	    
		var estudantesModel = new EstudantesModel();
		
		// Associa o curso ao estudante
	    estudantesModel.setCursos(cursoOptional.get());
	    
	    BeanUtils.copyProperties(estudantesDto, estudantesModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(estudantesModel));
		
	}
	
	@GetMapping("/estudantes")
	public ResponseEntity<List<EstudantesModel>> listarEstudantes(){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.findAll());
	}
	
	@GetMapping("/estudantes/{id}")
	public ResponseEntity<Object> listaUmEstudante(@PathVariable(value="id") int id){
		
		Optional <EstudantesModel> estudante = repositorio.findById(id);
		if(estudante.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante nao encontrado...");
			
		}return ResponseEntity.status(HttpStatus.OK).body(estudante.get());
	}
	
	@PutMapping("estudantes/{id}")
	public ResponseEntity<Object> atualizaEstudante(@RequestBody @Valid EstudantesRecordDto estudantesDto, @PathVariable(value="id") int id){
		
		Optional<EstudantesModel> estudante = repositorio.findById(id);
		
		if(estudante.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante nao encontrado...");
			
		}
		
	BeanUtils.copyProperties(estudantesDto, estudante.get());
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(estudante.get()));
		
	}
	
	@DeleteMapping("/estudantes/{id}")
	public ResponseEntity<Object> deletarEstudante(@PathVariable(value="id") int id){
		
		Optional<EstudantesModel> estudante = repositorio.findById(id);
		if(estudante.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante nao encontrado...");
			
		}
		repositorio.delete(estudante.get());
		return ResponseEntity.status(HttpStatus.OK).body("Estudante deletado com sucesso!");
	}
	
	
	
}
