package sistemaEscolar.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaEscolar.web.Model.EstudantesModel;

public interface EstudantesRepository extends JpaRepository<EstudantesModel, Integer> {

}
