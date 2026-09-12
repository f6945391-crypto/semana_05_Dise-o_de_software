package pe.edu.empresa.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pe.edu.empresa.tienda.model.Producto;
import pe.edu.empresa.tienda.service.ProductoService;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;


    public ProductoController(
            ProductoService service
    ) {

        this.service = service;
    }


    @GetMapping
    public String listar(
            Model model
    ) {

        model.addAttribute(
            "productos",
            service.listarTodos()
        );

        return "productos/lista";
    }


    @GetMapping("/{id}")
    public String detalle(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
            "producto",
            service.obtenerPorId(id)
        );

        return "productos/detalle";
    }


    @GetMapping("/nuevo")
    public String nuevo(
            Model model
    ) {

        model.addAttribute(
            "producto",
            new Producto()
        );

        return "productos/formulario";
    }


    @PostMapping
    public String guardar(
            @ModelAttribute Producto producto
    ) {

        service.guardar(producto);

        return "redirect:/productos";
    }


    @GetMapping("/{id}/editar")
    public String editar(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
            "producto",
            service.obtenerPorId(id)
        );

        return "productos/formulario";
    }


    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @ModelAttribute Producto producto
    ) {

        service.actualizar(
            id,
            producto
        );

        return "redirect:/productos";
    }


    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id
    ) {

        service.eliminar(id);

        return "redirect:/productos";
    }
}