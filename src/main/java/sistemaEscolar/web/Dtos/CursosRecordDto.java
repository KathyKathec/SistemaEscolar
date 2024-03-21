package sistemaEscolar.web.Dtos;

import jakarta.validation.constraints.NotBlank;

public record CursosRecordDto(@NotBlank String nome, @NotBlank String descricao,  String horario, @NotBlank String localDeCurso) {

}
