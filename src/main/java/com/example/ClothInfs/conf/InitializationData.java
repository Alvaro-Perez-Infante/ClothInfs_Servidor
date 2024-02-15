package com.example.ClothInfs.conf;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.ClothInfs.model.Role;
import com.example.ClothInfs.model.Ropa;
import com.example.ClothInfs.model.Usuario;
import com.example.ClothInfs.repository.RopaRepository;
import com.example.ClothInfs.repository.UsuarioRepository;
import com.github.javafaker.Faker;

@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RopaRepository ropaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Insertar lógica para borrar usuarios y ropa si es necesario
        // e.g., usuarioRepository.deleteAll(); ropaRepository.deleteAll();

    	// Usuario 1 - Rol USER
        Usuario usuario1 = new Usuario();
        usuario1.setFirstName("Alice");
        usuario1.setLastName("Johnson");
        usuario1.setEmail("alice.johnson@example.com");
        usuario1.setPassword(passwordEncoder.encode("password123"));
        usuario1.getRoles().add(Role.ROLE_USER);
        usuarioRepository.save(usuario1);

        // Usuario 2 - Rol ADMIN
        Usuario usuario2 = new Usuario();
        usuario2.setFirstName("Bob");
        usuario2.setLastName("Smith");
        usuario2.setEmail("bob.smith@example.com");
        usuario2.setPassword(passwordEncoder.encode("password456"));
        usuario2.getRoles().add(Role.ROLE_ADMIN);
        usuarioRepository.save(usuario2);

        // Usuario 3 - Rol USER
        Usuario usuario3 = new Usuario();
        usuario3.setFirstName("Carol");
        usuario3.setLastName("Davis");
        usuario3.setEmail("carol.davis@example.com");
        usuario3.setPassword(passwordEncoder.encode("password789"));
        usuario3.getRoles().add(Role.ROLE_USER);
        usuarioRepository.save(usuario3);

        // Crear ropas de ejemplo
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Ropa ropa = new Ropa();
            ropa.setNombre(faker.commerce().productName());
            ropa.setTipo(faker.commerce().material());
            ropa.setSize(faker.options().option("S", "M", "L", "XL"));
            ropa.setPrecio(faker.number().randomDouble(2, 10, 100));
            ropa.setCantidad(faker.number().numberBetween(1, 50));
            ropaRepository.save(ropa);
        }
    }
}
