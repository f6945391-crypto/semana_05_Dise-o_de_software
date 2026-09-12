package pe.edu.empresa.tienda.model;

import jakarta.persistence.*;

import java.time.LocalDate;


/**
 * Tabla intermedia de la relacion muchos-a-muchos Usuario <-> Producto.
 *
 * Representa el "carrito/pedido": que productos ha agregado cada usuario
 * y en que cantidad. Se modela como ENTIDAD propia para:
 *   - definir la clave primaria compuesta (@EmbeddedId), y
 *   - guardar atributos de la relacion (cantidad, fecha_pedido).
 *
 * @MapsId hace que usuario_id y producto_id sean, a la vez, clave foranea
 * (la conexion con cada tabla) y parte de la clave primaria compuesta.
 */
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoId id = new DetallePedidoId();

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(
        name = "fecha_pedido",
        nullable = false
    )
    private LocalDate fechaPedido;


    public DetallePedido() {
    }


    public DetallePedido(
            Usuario usuario,
            Producto producto,
            Integer cantidad,
            LocalDate fechaPedido
    ) {

        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;

        // Se arma la clave compuesta con los ids de las entidades.
        this.id = new DetallePedidoId(
            usuario.getId(),
            producto.getId()
        );
    }


    public DetallePedidoId getId() {
        return id;
    }


    public void setId(DetallePedidoId id) {
        this.id = id;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Producto getProducto() {
        return producto;
    }


    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public Integer getCantidad() {
        return cantidad;
    }


    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public LocalDate getFechaPedido() {
        return fechaPedido;
    }


    public void setFechaPedido(
            LocalDate fechaPedido
    ) {
        this.fechaPedido = fechaPedido;
    }
}
