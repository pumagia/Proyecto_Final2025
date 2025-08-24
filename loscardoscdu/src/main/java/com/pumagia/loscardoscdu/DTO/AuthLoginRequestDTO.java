package com.pumagia.loscardoscdu.DTO;

import jakarta.validation.constraints.NotBlank;

public record  AuthLoginRequestDTO (@NotBlank String email,@NotBlank String clave){
}
