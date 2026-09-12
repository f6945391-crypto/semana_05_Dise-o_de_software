package pe.edu.empresa.tienda.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


/**
 * Clave primaria COMPUESTA de la tabla intermedia detalle_pedido.
 *
 * La relacion muchos-a-muchos es Usuario <-> Producto (el carrito/pedido):
 * cada fila queda identificada por la combinacion (usuario_id, producto_id).
 * Se usa como @EmbeddedId dentro de DetallePedido.
 */
@Embeddable
public class DetallePedidoId implements Serializable {

    private Long usuarioId;

    private Long productoId;


    public DetallePedidoId() {
    }


    public DetallePedidoId(
            Long usuarioId,
            Long productoId
    ) {

        this.usuarioId = usuarioId;
        this.productoId = productoId;
    }


    public Long getUsuarioId() {
        return usuarioId;
    }


    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }


    public Long getProductoId() {
        return productoId;
    }


    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (
            o == null ||
            getClass() != o.getClass()
        ) {
            return false;
        }

        DetallePedidoId that = (DetallePedidoId) o;

        return Objects.equals(usuarioId, that.usuarioId)
            && Objects.equals(productoId, that.productoId);
    }


    @Override
    public int hashCode() {

        return Objects.hash(usuarioId, productoId);
    }
}
