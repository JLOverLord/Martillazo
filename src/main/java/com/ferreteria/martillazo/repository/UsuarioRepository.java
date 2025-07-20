package com.ferreteria.martillazo.repository;

import com.ferreteria.martillazo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    long countByRolesContaining(String rol);  // Cuenta por rol
}
