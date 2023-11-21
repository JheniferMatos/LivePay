package br.com.caboumony.security.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço de autenticação que implementa a interface UserDetailsService.
 * Utilizado pelo Spring Security para carregar informações do usuário durante a autenticação.
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    /**
     * Repositório de usuários para carregar informações do usuário durante a autenticação.
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Carrega um usuário com base no nome de usuário (login) durante a autenticação.
     *
     * @param login Nome de usuário (login) do usuário a ser carregado.
     * @return UserDetails contendo informações do usuário.
     * @throws UsernameNotFoundException Se o usuário não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }
}
