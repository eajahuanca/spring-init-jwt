package com.mopsv.viaticos.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mopsv.viaticos.entities.Role;
import com.mopsv.viaticos.entities.TipoUsuario;
import com.mopsv.viaticos.entities.Usuario;
import com.mopsv.viaticos.repositories.RoleRepository;
import com.mopsv.viaticos.repositories.TipoUsuarioRepository;
import com.mopsv.viaticos.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImplement(UsuarioRepository usuarioRepository,
                                   RoleRepository roleRepository,
                                   TipoUsuarioRepository tipoUsuarioRepository,
                                   PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Optional<Role> optionalRoleUsuario = roleRepository.findByDenominacion("ROLE_USUARIO");
        Optional<TipoUsuario> optionalTipoUsuario = tipoUsuarioRepository.findById(usuario.getTipo().getId());
        List<Role> roles = new ArrayList<>();
        optionalRoleUsuario.ifPresent(roles::add);
        optionalTipoUsuario.ifPresent(usuario::setTipo);
        if (usuario.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByDenominacion("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        usuario.setRole(roles);
        usuario.setEnabled(true);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
}
