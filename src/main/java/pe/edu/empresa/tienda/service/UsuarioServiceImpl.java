package pe.edu.empresa.tienda.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.empresa.tienda.model.DetallePedido;
import pe.edu.empresa.tienda.model.DetallePedidoId;
import pe.edu.empresa.tienda.model.Producto;
import pe.edu.empresa.tienda.model.Usuario;
import pe.edu.empresa.tienda.repository.DetallePedidoRepository;
import pe.edu.empresa.tienda.repository.ProductoRepository;
import pe.edu.empresa.tienda.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl
        implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ProductoRepository productoRepository;

    private final DetallePedidoRepository detallePedidoRepository;


    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository,
            DetallePedidoRepository detallePedidoRepository
    ) {

        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }


    @Override
    public List<Usuario> listarTodos() {

        return usuarioRepository.findAll();
    }


    @Override
    public Usuario obtenerPorId(Long id) {

        return usuarioRepository
                .findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "Usuario no encontrado"
                    )
                );
    }


    @Override
    public Usuario guardar(Usuario usuario) {

        validarUsuario(usuario);

        if (
            usuarioRepository.existsByEmail(
                usuario.getEmail()
            )
        ) {

            throw new IllegalArgumentException(
                "Ya existe un usuario con ese email"
            );
        }

        return usuarioRepository.save(usuario);
    }


    @Override
    public Usuario actualizar(
            Long id,
            Usuario datosUsuario
    ) {

        Usuario usuario = obtenerPorId(id);

        validarUsuario(datosUsuario);

        usuario.setNombre(
            datosUsuario.getNombre()
        );

        usuario.setEmail(
            datosUsuario.getEmail()
        );

        usuario.setTelefono(
            datosUsuario.getTelefono()
        );

        return usuarioRepository.save(usuario);
    }


    @Override
    public void eliminar(Long id) {

        Usuario usuario = obtenerPorId(id);

        usuarioRepository.delete(usuario);
    }


    @Override
    @Transactional
    public void agregarProducto(
            Long usuarioId,
            Long productoId,
            Integer cantidad
    ) {

        Usuario usuario = obtenerPorId(usuarioId);

        Producto producto = productoRepository
                .findById(productoId)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "Producto no encontrado"
                    )
                );

        if (
            cantidad == null ||
            cantidad < 1
        ) {

            throw new IllegalArgumentException(
                "La cantidad debe ser al menos 1"
            );
        }

        DetallePedidoId clave =
            new DetallePedidoId(usuarioId, productoId);

        if (
            detallePedidoRepository.existsById(clave)
        ) {

            throw new IllegalArgumentException(
                "El producto ya esta en el carrito del usuario"
            );
        }

        DetallePedido detalle = new DetallePedido(
            usuario,
            producto,
            cantidad,
            LocalDate.now()
        );

        detallePedidoRepository.save(detalle);
    }


    @Override
    @Transactional
    public void quitarProducto(
            Long usuarioId,
            Long productoId
    ) {

        DetallePedidoId clave =
            new DetallePedidoId(usuarioId, productoId);

        DetallePedido detalle = detallePedidoRepository
                .findById(clave)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "El detalle no existe"
                    )
                );

        detallePedidoRepository.delete(detalle);
    }


    private void validarUsuario(Usuario usuario) {

        if (
            usuario.getNombre() == null ||
            usuario.getNombre().isBlank()
        ) {

            throw new IllegalArgumentException(
                "El nombre es obligatorio"
            );
        }

        if (
            usuario.getEmail() == null ||
            usuario.getEmail().isBlank()
        ) {

            throw new IllegalArgumentException(
                "El email es obligatorio"
            );
        }
    }
}
