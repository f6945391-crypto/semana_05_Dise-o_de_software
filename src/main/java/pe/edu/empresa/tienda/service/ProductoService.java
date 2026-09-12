package pe.edu.empresa.tienda.service;

import java.util.List;

import pe.edu.empresa.tienda.model.Producto;


public interface ProductoService {

    List<Producto> listarTodos();

    Producto obtenerPorId(Long id);

    Producto guardar(Producto producto);

    Producto actualizar(
        Long id,
        Producto producto
    );

    void eliminar(Long id);
}