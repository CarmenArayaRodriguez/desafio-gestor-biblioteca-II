package cl.praxis.GestorBiblioteca.controller;

import cl.praxis.GestorBiblioteca.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cl.praxis.GestorBiblioteca.service.LibraryService;

import java.util.List;

@Controller
@RequestMapping("/biblioteca")
public class AccionController {

    private static final Logger logger = LoggerFactory.getLogger(AccionController.class);
    private final LibraryService libraryService;

    public AccionController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/agregar")
    public String agregarLibro(@RequestParam String id, @RequestParam String title, @RequestParam String author, Model model) {
        String respuesta = libraryService.agregarLibro(id, title, author);
        if (respuesta.startsWith("Error")) {
            model.addAttribute("mensajeError", respuesta);
            return "agregar-libro";
        }
        model.addAttribute("mensajeSuccess", respuesta);
        return "redirect:/biblioteca/lista-libros";
    }
    @GetMapping("/agregar-libro")
    public String mostrarFormularioAgregarLibro(Model model) {
        model.addAttribute("libro", new Book());
        return "agregar-libro";
    }

@GetMapping("/buscarTodos")
public String buscarLibros(@RequestParam(required = false) String query, Model model) {
    List<Book> libros;
    if (query != null && !query.isEmpty()) {
        libros = libraryService.buscarLibros(query);
    } else {
        libros = libraryService.obtenerTodosLosLibros();
    }
    model.addAttribute("libros", libros);
    return "lista-libros";
}

    @GetMapping("/lista-libros")
    public String mostrarListaDeLibros(Model model) {
        List<Book> libros = libraryService.obtenerTodosLosLibros();
        model.addAttribute("libros", libros);
        return "lista-libros";
    }
    @GetMapping("/buscar")
    public String buscarLibro(@RequestParam String id) {
        String resultado = libraryService.buscarLibro(id);
        logger.info("Búsqueda de libro con ID: {}", id);
        return resultado;
    }

    @PostMapping("/prestamo")
    public String registrarPrestamo(@RequestParam String libroId, @RequestParam String usuarioId) {
        String resultado = libraryService.registrarPrestamo(libroId, usuarioId);
        logger.info("Préstamo registrado para el libro con ID {} por el usuario {}", libroId, usuarioId);
        return resultado;
    }

    @PostMapping("/devolucion")
    public String registrarDevolucion(@RequestParam String libroId, @RequestParam String usuarioId) {
        String resultado = libraryService.registrarDevolucion(libroId, usuarioId);
        logger.info("Devolución registrada para el libro con ID {} por el usuario {}", libroId, usuarioId);
        return resultado;
    }
}
