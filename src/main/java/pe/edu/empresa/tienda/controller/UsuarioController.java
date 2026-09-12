package pe.edu.empresa.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pe.edu.empresa.tienda.model.Usuario;
import pe.edu.empresa.tienda.service.ProductoService;
import pe.edu.empresa.tienda.service.UsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    private final ProductoService productoService;


    public UsuarioController(
            UsuarioService service,
            ProductoService productoService
    ) {

        this.service = service;
        this.productoService = productoService;
    }


    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
            "usuarios",
            service.listarTodos()
        );

        return "usuarios/lista";
    }


    @GetMapping("/{id}")
    public String detalle(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
            "usuario",
            service.obtenerPorId(id)
        );

        // Lista completa de productos para el desplegable del carrito.
        model.addAttribute(
            "productos",
            productoService.listarTodos()
        );

        return "usuarios/detalle";
    }


    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute(
            "usuario",
            new Usuario()
        );

        return "usuarios/formulario";
    }


    @PostMapping
    public String guardar(
            @ModelAttribute Usuario usuario
    ) {

        service.guardar(usuario);

        return "redirect:/usuarios";
    }


    @GetMapping("/{id}/editar")
    public String editar(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
            "usuario",
            service.obtenerPorId(id)
        );

        return "usuarios/formulario";
    }


    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @ModelAttribute Usuario usuario
    ) {

        service.actualizar(id, usuario);

        return "redirect:/usuarios";
    }


    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id
    ) {

        service.eliminar(id);

        return "redirect:/usuarios";
    }


    // ---- Relacion muchos-a-muchos con Producto (carrito / detalle_pedido) ----

    @PostMapping("/{id}/productos")
    public String agregarProducto(
            @PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad
    ) {

        service.agregarProducto(id, productoId, cantidad);

        return "redirect:/usuarios/" + id;
    }


    @PostMapping("/{id}/productos/{productoId}/eliminar")
    public String quitarProducto(
            @PathVariable Long id,
            @PathVariable Long productoId
    ) {

        service.quitarProducto(id, productoId);

        return "redirect:/usuarios/" + id;
    }
}
