package com.pumagia.loscardoscdu.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id_usuario","email","message","jwt","status",})
public record AuthResponseDTO(Long id_usuario, String email, String message,String jwt, boolean status) {
}
