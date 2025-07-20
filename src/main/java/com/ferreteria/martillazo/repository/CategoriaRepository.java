package com.ferreteria.martillazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteria.martillazo.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
}
