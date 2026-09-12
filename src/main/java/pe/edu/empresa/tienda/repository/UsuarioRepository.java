package pe.edu.empresa.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.empresa.tienda.model.Usuario;


public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
}
