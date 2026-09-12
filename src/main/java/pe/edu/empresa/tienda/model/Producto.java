package pe.edu.empresa.tienda.model;
 
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        length = 100
    )
    private String nombre;

    @Column(
        length = 250
    )
    private String descripcion;

    @Column(
        nullable = false,
        precision = 10,
        scale = 2
    )
    private BigDecimal precio;

    @Column(
        nullable = false
    )
    private Integer stock;


    public Producto() {
    }


    public Producto(
            String nombre,
            String descripcion,
            BigDecimal precio,
            Integer stock
    ) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(
            String descripcion
    ) {
        this.descripcion = descripcion;
    }


    public BigDecimal getPrecio() {
        return precio;
    }


    public void setPrecio(
            BigDecimal precio
    ) {
        this.precio = precio;
    }


    public Integer getStock() {
        return stock;
    }


    public void setStock(Integer stock) {
        this.stock = stock;
    }
}