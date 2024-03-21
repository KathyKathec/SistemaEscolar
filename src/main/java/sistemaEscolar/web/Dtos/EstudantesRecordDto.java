package sistemaEscolar.web.Dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sistemaEscolar.web.Model.CursosModel;

public record EstudantesRecordDto(@NotBlank String nome, @NotBlank String matricula, @NotNull long identidade, @NotNull int cursoId, LocalDate nacimento, int age) {

}
