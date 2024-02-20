package com.mopsv.viaticos.services;

import com.mopsv.viaticos.entities.Usuario;
import com.mopsv.viaticos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUsuarioDetalleService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public JpaUsuarioDetalleService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        if (usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }
        Usuario usuario = usuarioOptional.orElseThrow();
        List<GrantedAuthority> authorities = usuario.getRole()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getDenominacion()))
                .collect(Collectors.toList());
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}
