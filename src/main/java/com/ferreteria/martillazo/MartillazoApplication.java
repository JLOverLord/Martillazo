package com.ferreteria.martillazo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ferreteria.martillazo.model.Categoria;
import com.ferreteria.martillazo.model.Producto;
import com.ferreteria.martillazo.model.Usuario;
import com.ferreteria.martillazo.repository.CategoriaRepository;
import com.ferreteria.martillazo.repository.ProductoRepository;
import com.ferreteria.martillazo.repository.UsuarioRepository;

@SpringBootApplication
public class MartillazoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartillazoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
		CategoriaRepository categoriaRepo,
		ProductoRepository productoRepo,
		UsuarioRepository usuarioRepo,
		PasswordEncoder passwordEncoder
	) {
		return args -> {
			if (usuarioRepo.count() == 0) {
				Usuario admin = new Usuario();
				admin.setNombre("Administrador");
				admin.setCorreo("admin@admin.com");
				admin.setContraseña(passwordEncoder.encode("admin123"));
				admin.getRoles().add("ROL_ADMIN");
				admin.getRoles().add("ROL_TRABAJADOR");
				admin.getRoles().add("ROL_USUARIO");
				usuarioRepo.save(admin);
			}

			if (categoriaRepo.count() == 0) {
				Categoria herramientas = new Categoria();
				herramientas.setNombre("Herramientas Manuales");
				categoriaRepo.save(herramientas);

				Producto martillo = new Producto();
				martillo.setNombre("Martillo de Garra (Mango de Fibra)");
				martillo.setDescripcion("Martillo de acero con mango de goma.");
				martillo.setPrecio(25.5);
				martillo.setImagen("https://cahema.pe/14398-large_default/martillo-carpintero-mango-de-fibra-24-oz-gy-hm-5407-goodyear.webp");
				martillo.setCategoria(herramientas);
				productoRepo.save(martillo);
			}
		};
	}

}
