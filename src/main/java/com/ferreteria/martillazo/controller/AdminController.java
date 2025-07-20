package com.ferreteria.martillazo.controller;

import com.ferreteria.martillazo.model.Producto;
import com.ferreteria.martillazo.model.Usuario;
import com.ferreteria.martillazo.repository.ProductoRepository;
import com.ferreteria.martillazo.repository.UsuarioRepository;
import com.ferreteria.martillazo.model.Categoria;
import com.ferreteria.martillazo.service.ProductoService;
import com.ferreteria.martillazo.service.CategoriaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // ---------------------- ESTADÍSTICAS ----------------------

    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(Model model) {
        long totalProductos = productoRepository.count();
        long totalCategorias = categoriaService.listarTodas().size();
        long productosDestacados = productoRepository.findAll().stream()
            .filter(Producto::isDestacado)
            .count();

        long totalAdmins = usuarioRepository.findAll().stream()
            .filter(u -> u.getRoles().contains("ROLE_ADMIN"))
            .count();

        long totalTrabajadores = usuarioRepository.findAll().stream()
            .filter(u -> u.getRoles().contains("ROLE_TRABAJADOR"))
            .count();

        long totalUsuarios = usuarioRepository.findAll().stream()
            .filter(u -> u.getRoles().contains("ROLE_USUARIO"))
            .count();

        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("totalCategorias", totalCategorias);
        model.addAttribute("productosDestacados", productosDestacados);
        model.addAttribute("totalAdmins", totalAdmins);
        model.addAttribute("totalTrabajadores", totalTrabajadores);
        model.addAttribute("totalUsuarios", totalUsuarios);

        return "admin/estadisticas";
    }

    // ---------------------- USUARIOS ----------------------

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "admin/usuarios";
    }

    @PostMapping("/usuarios/{id}/asignar-rol")
    public String asignarRol(@PathVariable Long id,
                            @RequestParam("rol") String rol,
                            RedirectAttributes redirectAttributes) {

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();

            if ("admin@admin.com".equalsIgnoreCase(usuario.getCorreo())) {
                redirectAttributes.addFlashAttribute("error", "No puedes modificar al administrador principal.");
                return "redirect:/admin/usuarios";
            }

            if ("ROLE_ADMIN".equalsIgnoreCase(rol)) {
                redirectAttributes.addFlashAttribute("error", "No puedes asignar el rol de ADMIN.");
                return "redirect:/admin/usuarios";
            }

            if (!usuario.getRoles().contains(rol)) {
                usuario.getRoles().add(rol);
                usuarioRepository.save(usuario);
                redirectAttributes.addFlashAttribute("success", "Rol asignado correctamente a " + usuario.getNombre());
            } else {
                redirectAttributes.addFlashAttribute("info", "El usuario ya tiene este rol.");
            }
        }

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/{id}/remover-rol")
    public String removerRol(@PathVariable Long id,
                            @RequestParam("rol") String rol,
                            RedirectAttributes redirectAttributes) {

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();

            if ("admin@admin.com".equalsIgnoreCase(usuario.getCorreo())) {
                redirectAttributes.addFlashAttribute("error", "No puedes modificar al administrador principal.");
                return "redirect:/admin/usuarios";
            }

            if ("ROLE_ADMIN".equalsIgnoreCase(rol)) {
                redirectAttributes.addFlashAttribute("error", "No puedes remover el rol de ADMIN.");
                return "redirect:/admin/usuarios";
            }

            if (usuario.getRoles().contains(rol)) {
                usuario.getRoles().remove(rol);
                usuarioRepository.save(usuario);
                redirectAttributes.addFlashAttribute("success", "Rol removido correctamente de " + usuario.getNombre());
            } else {
                redirectAttributes.addFlashAttribute("info", "El usuario no tiene ese rol.");
            }
        }

        return "redirect:/admin/usuarios";
    }

    // ---------------------- PRODUCTOS ----------------------

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "admin/lista-productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/formulario-producto";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(
            @Valid @ModelAttribute Producto producto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "admin/formulario-producto";
        }

        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de producto inválido: " + id));
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/formulario-producto";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos";
    }

    // ---------------------- CATEGORÍAS ----------------------

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/lista-categorias";
    }

    @GetMapping("/categorias/nueva")
    public String nuevaCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/formulario-categoria";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(
        @Valid @ModelAttribute("categoria") Categoria categoria,
        BindingResult result,
        Model model) {

        if (result.hasErrors()) {
            return "admin/formulario-categoria";
        }

        categoriaService.guardar(categoria);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/categorias/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.obtenerPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de categoría inválido: " + id));
        model.addAttribute("categoria", categoria);
        return "admin/formulario-categoria";
    }

    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        if (categoriaService.tieneProductosAsociados(id)) {
            redirectAttrs.addFlashAttribute("error", "No puedes eliminar esta categoría porque tiene productos asociados.");
            return "redirect:/admin/categorias";
        }
        categoriaService.eliminar(id);
        redirectAttrs.addFlashAttribute("success", "Categoría eliminada exitosamente.");
        return "redirect:/admin/categorias";
    }

    // ---------------------- DASHBOARD ----------------------

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "admin/dashboard";
    }
}
