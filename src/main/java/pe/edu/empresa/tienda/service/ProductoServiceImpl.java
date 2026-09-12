package pe.edu.empresa.tienda.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.empresa.tienda.model.Producto;
import pe.edu.empresa.tienda.repository.ProductoRepository;


@Service
public class ProductoServiceImpl
        implements ProductoService {

    private final ProductoRepository repository;


    public ProductoServiceImpl(
            ProductoRepository repository
    ) {
        this.repository = repository;
    }


    @Override
    public List<Producto> listarTodos() {

        return repository.findAll();
    }


    @Override
    public Producto obtenerPorId(Long id) {

        return repository
                .findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "Producto no encontrado"
                    )
                );
    }


    @Override
    public Producto guardar(
            Producto producto
    ) {

        validarProducto(producto);

        return repository.save(producto);
    }


    @Override
    public Producto actualizar(
            Long id,
            Producto datosProducto
    ) {

        Producto producto =
            obtenerPorId(id);

        validarProducto(datosProducto);

        producto.setNombre(
            datosProducto.getNombre()
        );

        producto.setDescripcion(
            datosProducto.getDescripcion()
        );

        producto.setPrecio(
            datosProducto.getPrecio()
        );

        producto.setStock(
            datosProducto.getStock()
        );

        return repository.save(producto);
    }


    @Override
    public void eliminar(Long id) {

        Producto producto =
            obtenerPorId(id);

        repository.delete(producto);
    }


    private void validarProducto(
            Producto producto
    ) {

        if (
            producto.getNombre() == null ||
            producto.getNombre().isBlank()
        ) {

            throw new IllegalArgumentException(
                "El nombre es obligatorio"
            );
        }


        if (
            producto.getPrecio() == null ||
            producto.getPrecio().compareTo(
                BigDecimal.ZERO
            ) <= 0
        ) {

            throw new IllegalArgumentException(
                "El precio debe ser mayor a cero"
            );
        }


        if (
            producto.getStock() == null ||
            producto.getStock() < 0
        ) {

            throw new IllegalArgumentException(
                "El stock no puede ser negativo"
            );
        }
    }
}