package br.com.caboumony.security.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidade que representa um usuário no sistema.
 * Implementa a interface UserDetails para integração com o Spring Security.
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails {

    /**
     * Identificador único do usuário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome de usuário (login) único.
     */
    @Column(unique = true)
    private String login;

    /**
     * Senha do usuário.
     */
    private String senha;

    /**
     * Construtor que recebe login e senha do usuário.
     *
     * @param login Nome de usuário (login) do usuário.
     * @param senha Senha do usuário.
     */
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    /**
     * Construtor de cópia que cria um novo usuário com base em outro usuário.
     *
     * @param usuario Usuário a ser copiado.
     */
    public Usuario(Usuario usuario) {
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
    }

    /**
     * Retorna as autoridades do usuário (papel/role).
     *
     * @return Lista de GrantedAuthority representando as autoridades do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return Senha do usuário.
     */
    @Override
    public String getPassword() {
        return senha;
    }

    /**
     * Retorna o nome de usuário (login) do usuário.
     *
     * @return Nome de usuário (login) do usuário.
     */
    @Override
    public String getUsername() {
        return login;
    }

    /**
     * Indica se a conta do usuário está expirada.
     *
     * @return Sempre retorna true, indicando que a conta não está expirada.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica se a conta do usuário está bloqueada.
     *
     * @return Sempre retorna true, indicando que a conta não está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica se as credenciais do usuário estão expiradas.
     *
     * @return Sempre retorna true, indicando que as credenciais não estão expiradas.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica se o usuário está habilitado.
     *
     * @return Sempre retorna true, indicando que o usuário está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
