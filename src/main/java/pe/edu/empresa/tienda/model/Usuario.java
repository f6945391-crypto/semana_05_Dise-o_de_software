package pe.edu.empresa.tienda.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        length = 100
    )
    private String nombre;

    @Column(
        nullable = false,
        length = 150,
        unique = true
    )
    private String email;

    @Column(
        length = 20
    )
    private String telefono;

    // Lado "uno" de la relacion muchos-a-muchos con Producto.
    // Un usuario tiene muchas lineas de detalle (detalle_pedido).
    @OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<DetallePedido> detalles = new ArrayList<>();


    public Usuario() {
    }


    public Usuario(
            String nombre,
            String email
    ) {

        this.nombre = nombre;
        this.email = email;
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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public List<DetallePedido> getDetalles() {
        return detalles;
    }


    public void setDetalles(
            List<DetallePedido> detalles
    ) {
        this.detalles = detalles;
    }
}
