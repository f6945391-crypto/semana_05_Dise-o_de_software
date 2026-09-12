package pe.edu.empresa.tienda.service;

import java.util.List;

import pe.edu.empresa.tienda.model.Usuario;


public interface UsuarioService {

    List<Usuario> listarTodos();

    Usuario obtenerPorId(Long id);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(
        Long id,
        Usuario usuario
    );

    void eliminar(Long id);

    // Operaciones sobre la relacion muchos-a-muchos con Producto (detalle_pedido).

    void agregarProducto(
        Long usuarioId,
        Long productoId,
        Integer cantidad
    );

    void quitarProducto(
        Long usuarioId,
        Long productoId
    );
}
