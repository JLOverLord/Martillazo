package com.ferreteria.martillazo.controller;

import com.ferreteria.martillazo.model.Categoria;
import com.ferreteria.martillazo.model.Producto;
import com.ferreteria.martillazo.model.Usuario;
import com.ferreteria.martillazo.repository.CategoriaRepository;
import com.ferreteria.martillazo.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping({"/", "/index"})
    public String home(@RequestParam(name = "category", required = false) String category, Model model) {

        String currentCategory = (category == null || category.isEmpty()) ? "index" : category;

        List<Producto> productos;
        String titulo;
        String descripcion;

        if ("index".equals(currentCategory)) {
            productos = productoRepository.findAll();
            titulo = "Ferretería Martillazo";
            descripcion = "Tu destino para herramientas y materiales de ferretería de calidad. ¡Descubre nuestra amplia gama de productos para cada proyecto!";

            Producto destacado = productoRepository.findFirstByDestacadoTrue();
            if (destacado != null) {
                model.addAttribute("highlightedProduct", destacado);
            }

        } else {
            productos = productoRepository.findByCategoriaNombreIgnoreCase(currentCategory);
            titulo = categoriaRepository.findByNombreIgnoreCase(currentCategory)
                    .map(Categoria::getNombre)
                    .orElse("Categoría");
            descripcion = getDescripcionPorCategoria(currentCategory);
        }

        model.addAttribute("products", productos);
        model.addAttribute("pageTitle", titulo);
        model.addAttribute("categoryTitle", titulo);
        model.addAttribute("categoryDescription", descripcion);
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("categorias", categoriaRepository.findAll());

        return "index";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("pageTitle", "Nosotros");
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("usuario", new Usuario());
        return "nosotros";
    }

    @GetMapping("/ofertas")
    public String ofertas(Model model) {
        model.addAttribute("pageTitle", "Ofertas Especiales");
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("usuario", new Usuario());
        return "ofertas";
    }

    @GetMapping("/preguntas-frecuentes")
    public String preguntasFrecuentes(Model model) {
        model.addAttribute("pageTitle", "Preguntas Frecuentes");
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("usuario", new Usuario());
        return "preguntas-frecuentes";
    }

    private String getDescripcionPorCategoria(String categoria) {
        return switch (categoria.toLowerCase()) {
            case "herramientas-manuales" -> "Explora nuestra amplia selección de herramientas manuales para todos tus proyectos.";
            case "clavos-y-pernos" -> "Soluciones robustas para todas tus necesidades de fijación y anclaje.";
            case "construccion" -> "Materiales esenciales para construir proyectos sólidos y duraderos.";
            case "jardineria" -> "Todo lo necesario para cuidar y embellecer tu jardín.";
            default -> "";
        };
    }
}
