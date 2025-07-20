package com.ferreteria.martillazo.service;

import com.ferreteria.martillazo.model.CarritoItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoService {
    private static final String CARRITO_KEY = "carrito";

    public List<CarritoItem> obtenerCarrito(HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute(CARRITO_KEY);
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute(CARRITO_KEY, carrito);
        }
        return carrito;
    }

    public void agregarProducto(HttpSession session, CarritoItem item) {
        List<CarritoItem> carrito = obtenerCarrito(session);

        for (CarritoItem existente : carrito) {
            if (existente.getProductoId().equals(item.getProductoId())) {
                existente.setCantidad(existente.getCantidad() + item.getCantidad());
                return;
            }
        }
        carrito.add(item);
    }

    public void eliminarProducto(HttpSession session, Long productoId) {
        List<CarritoItem> carrito = obtenerCarrito(session);
        carrito.removeIf(item -> item.getProductoId().equals(productoId));
    }

    public void vaciarCarrito(HttpSession session) {
        session.removeAttribute(CARRITO_KEY);
    }

    public double calcularTotal(HttpSession session) {
        return obtenerCarrito(session).stream()
                .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                .sum();
    }
}
