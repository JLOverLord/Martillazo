package com.ferreteria.martillazo.controller;

import com.ferreteria.martillazo.model.Usuario;
import com.ferreteria.martillazo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute Usuario usuario) {
        String rawPassword = usuario.getContraseña(); // Guarda texto plano
        usuario.setContraseña(passwordEncoder.encode(rawPassword));
        usuario.getRoles().add("ROLE_USER");
        usuarioRepository.save(usuario);

        // Autenticación automática con contraseña sin codificar
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuario.getCorreo(), rawPassword)
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        return isAdmin ? "redirect:/admin/dashboard" : "redirect:/";
    }
}
