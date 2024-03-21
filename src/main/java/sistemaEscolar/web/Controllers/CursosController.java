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
import sistemaEscolar.web.Dtos.CursosRecordDto;
import sistemaEscolar.web.Model.CursosModel;
import sistemaEscolar.web.Repositories.CursosRepository;

@RestController
public class CursosController {
	
	@Autowired
	CursosRepository repositorio;
	

	@PostMapping("/cursos")
	public ResponseEntity<CursosModel> salvarCurso(@RequestBody @Valid CursosRecordDto cursoDto){
		
		var CursosModel = new CursosModel();
		BeanUtils.copyProperties(cursoDto, CursosModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(CursosModel));
	}
	
	@GetMapping("/cursos")
	public ResponseEntity<List<CursosModel>> listarCursos(){
		
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.findAll());
		
	}
	
	@GetMapping("/cursos/{id}")
	public ResponseEntity<Object> listarUmCurso(@PathVariable(value="id") int id){
		
		Optional<CursosModel> curso = repositorio.findById(id);
		
		if (curso.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso nao encontrado...");
		}
		return ResponseEntity.status(HttpStatus.OK).body(curso.get());
	}
	
	@PutMapping("/cursos/{id}")
	public ResponseEntity<Object> alterarUmCurso(@RequestBody @Valid CursosRecordDto cursoDto ,@PathVariable(value="id") int id ){
		
		Optional<CursosModel> curso=repositorio.findById(id);
		if(curso.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso nao encontrado...");
			
		}
		var cursosModel = curso.get();
		BeanUtils.copyProperties(cursoDto,cursosModel );
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(cursosModel));
	}
	
	@DeleteMapping("/cursos/{id}")
	public ResponseEntity<Object> deltarCurso(@PathVariable (value="id") int id){
		
		Optional<CursosModel> curso = repositorio.findById(id);
		if(curso.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso nao encontrado...");
			
			
		}
		repositorio.delete(curso.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso!");
		
	}
	
	
	
	

}
