package com.ferreteria.martillazo.repository;

import com.ferreteria.martillazo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaNombreIgnoreCase(String nombreCategoria);
    Producto findFirstByDestacadoTrue();
    boolean existsByCategoriaId(Long categoriaId);

    long countByDestacadoTrue();  // Cuenta los productos destacados
}
