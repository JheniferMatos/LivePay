package br.com.caboumony.security.security;

import br.com.caboumony.security.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança personalizado para lidar com autenticação baseada em tokens JWT.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    /**
     * Instância autowired do TokenService para operações com tokens JWT.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * Instância autowired do UsuarioRepository para operações relacionadas ao banco de dados de usuários.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Executa a lógica do filtro de segurança para cada solicitação HTTP recebida.
     *
     * @param request     Instância HttpServletRequest representando a solicitação recebida.
     * @param response    Instância HttpServletResponse representando a resposta de saída.
     * @param filterChain Instância FilterChain para executar o próximo filtro na cadeia.
     * @throws ServletException Se ocorrer um erro durante o processamento do servlet.
     * @throws IOException      Se ocorrer um erro de I/O durante o processamento do servlet.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var login = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByLogin(login);

            var usuarioAutenticado = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Recupera o token JWT do cabeçalho Authorization da solicitação.
     *
     * @param request Instância HttpServletRequest representando a solicitação recebida.
     * @return O token JWT extraído ou null se não estiver presente.
     */
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
