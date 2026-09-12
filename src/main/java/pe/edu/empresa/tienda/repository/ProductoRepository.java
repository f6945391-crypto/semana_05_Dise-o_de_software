package pe.edu.empresa.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.empresa.tienda.model.Producto;


public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

}