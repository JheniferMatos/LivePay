package br.com.caboumony.security.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
        @NotBlank(message = "Login em branco")
        String login,

        @NotBlank(message = "Senha em branco")
        String senha) {

}
