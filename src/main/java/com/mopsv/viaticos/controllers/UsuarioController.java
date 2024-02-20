package com.mopsv.viaticos.controllers;

import com.mopsv.viaticos.entities.Usuario;
import com.mopsv.viaticos.helpers.ValidationHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mopsv.viaticos.services.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/users")
public class UsuarioController extends ValidationHelper {

    private final UsuarioService usuarioService;
    private final ValidationHelper validationHelper;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, ValidationHelper validationHelper){
        this.usuarioService = usuarioService;
        this.validationHelper = validationHelper;
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasFieldErrors()){
            return validationHelper.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario, BindingResult result){
        usuario.setAdmin(false);
        return crear(usuario, result);
    }
}
