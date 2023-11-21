package br.com.caboumony.security.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    /**
     * Busca um usuário pelo login.
     *
     * @param login O login do usuário.
     * @return Um UserDetails (usuário) se encontrado.
     */
    UserDetails findByLogin(String login);
}
