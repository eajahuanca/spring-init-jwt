package com.mopsv.viaticos.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mopsv.viaticos.validations.ExistByUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExistByUsername
    @Column(unique = true)
    @NotBlank
    @Size(min = 5, max = 30)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank
    @Size(min = 5, max = 10)
    private String ci;

    @NotBlank
    @Size(min = 5, max = 50)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 100)
    private String cargo;

    @NotBlank
    @Size(min = 5, max = 100)
    private String unidad;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoUsuario tipo;

    @Transient
    private boolean admin;

    @JsonIgnoreProperties({"usuario", "handler", "hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
            name = "usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = {"usuario_id", "role_id"})}
    )
    private List<Role> role;

    public Usuario() {
    }

    public Usuario(String username, String ci, String nombre, String cargo, String unidad, boolean enabled, TipoUsuario tipo, List<Role> role) {
        this.username = username;
        this.ci = ci;
        this.nombre = nombre;
        this.cargo = cargo;
        this.unidad = unidad;
        this.enabled = enabled;
        this.tipo = tipo;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public TipoUsuario getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
