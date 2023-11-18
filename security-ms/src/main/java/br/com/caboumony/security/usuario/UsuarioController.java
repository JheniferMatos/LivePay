package br.com.caboumony.security.usuario;

import br.com.caboumony.security.security.TokenService;
import br.com.caboumony.security.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
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

    @GetMapping
    public String teste(){
        return "teste";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        if(this.usuarioRepository.findByLogin(usuarioDto.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDto.senha());
        Usuario usuario = new Usuario(usuarioDto.login(), encryptedPassword);

        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().body("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDto usuarioDto){
        System.out.println(environment.getProperty("local.server.port"));
        var token = new UsernamePasswordAuthenticationToken(usuarioDto.login(),usuarioDto.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((Usuario)authentication.getPrincipal()));
    }

    @GetMapping("/validar")
    public Boolean validarToken(@RequestParam String token){
        return jwtService.validarToken(token);
    }


}
