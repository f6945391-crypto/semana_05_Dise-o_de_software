package pe.edu.empresa.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.empresa.tienda.model.DetallePedido;
import pe.edu.empresa.tienda.model.DetallePedidoId;


/**
 * Repositorio de la tabla intermedia detalle_pedido.
 * La clave es DetallePedidoId (clave primaria compuesta).
 */
public interface DetallePedidoRepository
        extends JpaRepository<DetallePedido, DetallePedidoId> {

}
