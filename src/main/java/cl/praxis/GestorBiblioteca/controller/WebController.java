package cl.praxis.GestorBiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cl.praxis.GestorBiblioteca.service.LibraryService;

@Controller
public class WebController {

    private final LibraryService libraryService;

    public WebController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/buscar")
    public String buscarLibro(@RequestParam(name = "id", required = false) String id, Model model) {
        if (id != null) {
            String resultado = libraryService.buscarLibro(id);
            model.addAttribute("resultado", resultado);
        }
        return "buscar";
    }

    @GetMapping("/prestamo-form")
    public String mostrarPrestamoForm() {
        return "prestamo-form";
    }

    @GetMapping("/devolucion-form")
    public String mostrarDevolucionForm() {
        return "devolucion-form";
    }

    @PostMapping("/prestamo")
    public String registrarPrestamo(@RequestParam String libroId, @RequestParam String usuarioId, Model model) {
        String resultado = libraryService.registrarPrestamo(libroId, usuarioId);
        model.addAttribute("resultado", resultado);
        return "resultado";
    }

    @PostMapping("/devolucion")
    public String registrarDevolucion(@RequestParam String libroId, @RequestParam String usuarioId, Model model) {
        String resultado = libraryService.registrarDevolucion(libroId, usuarioId);
        model.addAttribute("resultado", resultado);
        return "resultado";
    }
}
