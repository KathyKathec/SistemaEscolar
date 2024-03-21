package sistemaEscolar.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaEscolar.web.Model.ProfessoresModel;

public interface ProfessoresRepository extends JpaRepository<ProfessoresModel, Integer>{

}
