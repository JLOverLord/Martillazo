package com.ferreteria.martillazo.controller;

import com.ferreteria.martillazo.model.CarritoItem;
import com.ferreteria.martillazo.model.Producto;
import com.ferreteria.martillazo.service.CarritoService;
import com.ferreteria.martillazo.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String verCarrito(Model model, HttpSession session) {
        model.addAttribute("carrito", carritoService.obtenerCarrito(session));
        model.addAttribute("total", carritoService.calcularTotal(session));
        return "carrito";
    }

    @PostMapping("/agregar")
    public String agregarCarrito(@RequestParam Long productoId, @RequestParam(defaultValue = "1") int cantidad, HttpSession session) {
        Producto producto = productoService.obtenerPorId(productoId).orElse(null);
        if (producto != null) {
            CarritoItem item = new CarritoItem();
            item.setProductoId(producto.getId());
            item.setNombre(producto.getNombre());
            item.setImagen(producto.getImagen());
            item.setPrecio(producto.getPrecio());
            item.setCantidad(cantidad);

            carritoService.agregarProducto(session, item);
        }
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarDelCarrito(@RequestParam Long productoId, HttpSession session) {
        carritoService.eliminarProducto(session, productoId);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito(HttpSession session) {
        carritoService.vaciarCarrito(session);
        return "redirect:/carrito";
    }
}
