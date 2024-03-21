package sistemaEscolar.web.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sistemaEscolar.web.Model.CursosModel;

public record ProfessoresRecordDto(@NotBlank String nome, @NotNull long identidade, @NotBlank String nacionalidade, @NotBlank String siape, @NotNull int cursoId, CursosModel curso) {

}