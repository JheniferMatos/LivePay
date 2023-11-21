package br.com.caboumony.security.usuario;

import br.com.caboumony.security.security.TokenService;
import br.com.caboumony.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelas operações relacionadas a usuários, como login e
 * cadastro.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Usuario", description = "Endpoints para login e cadastro de usuários do Microsserviço de Segurança")
public class UsuarioController {
        @Autowired
        private AuthenticationManager manager;

        @Autowired
        private TokenService tokenService;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private JwtService jwtService;

        @Autowired
        Environment environment;

        /**
         * Endpoint de teste.
         *
         * @return Mensagem de teste.
         */
        @GetMapping
        public String teste() {
                return "teste";
        }

        /**
         * Endpoint para cadastrar um novo usuário.
         *
         * @param usuarioDto DTO contendo os dados do usuário a ser cadastrado.
         * @return Resposta HTTP indicando o sucesso ou falha do cadastro.
         */
        @PostMapping(value = "/cadastrar", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adicionar um novo Usuario", description = "Adiciona um novo Usuario passando uma representação JSON do Usuario!", tags = {
                        "Usuario" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        @Transactional
        public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
                if (this.usuarioRepository.findByLogin(usuarioDto.login()) != null)
                        return ResponseEntity.badRequest().build();

                String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDto.senha());
                Usuario usuario = new Usuario(usuarioDto.login(), encryptedPassword);

                this.usuarioRepository.save(usuario);
                return ResponseEntity.ok().body("Usuário criado com sucesso");
        }

        /**
         * Endpoint para efetuar o login de um usuário.
         *
         * @param usuarioDto DTO contendo os dados do usuário para o login.
         * @return Token JWT gerado após o login.
         */
        @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Login de um Usuario", description = "Loga um Usuario passando uma representação JSON do Usuario!", tags = {
                        "Usuario" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        @Transactional
        public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDto usuarioDto) {
                System.out.println(environment.getProperty("local.server.port"));
                var token = new UsernamePasswordAuthenticationToken(usuarioDto.login(), usuarioDto.senha());
                var authentication = manager.authenticate(token);

                return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
        }

        /**
         * Endpoint para validar um token JWT.
         *
         * @param token Token JWT a ser validado.
         * @return Indicação de validade do token.
         */
        @GetMapping("/validar")
        public Boolean validarToken(@RequestParam String token) {
                return jwtService.validarToken(token);
        }
}
