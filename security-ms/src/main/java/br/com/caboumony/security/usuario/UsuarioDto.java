package br.com.caboumony.security.usuario;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) para representar dados do usuário durante operações de cadastro e login.
 */
public record UsuarioDto(
        @NotBlank(message = "Login em branco")
        String login,

        @NotBlank(message = "Senha em branco")
        String senha) {
}
