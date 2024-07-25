package cl.praxis.GestorBiblioteca.service;

import cl.praxis.GestorBiblioteca.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private Map<String, Book> books = new HashMap<>();

    public LibraryService() {
        books.put("1", new Book("1", "La casa de los espíritus", "Isabel Allende", true));
        books.put("2", new Book("2", "Cien años de soledad", "Gabriel García Márquez", true));
        books.put("3", new Book("3", "Ficciones", "Jorge Luis Borges", true));
        books.put("4", new Book("4", "Pedro Páramo", "Juan Rulfo", true));
        books.put("5", new Book("5", "Los detectives salvajes", "Roberto Bolaño", true));
        books.put("6", new Book("6", "La ciudad y los perros", "Mario Vargas Llosa", true));
        books.put("7", new Book("7", "El amor en los tiempos del cólera", "Gabriel García Márquez", true));
        books.put("8", new Book("8", "Rayuela", "Julio Cortázar", true));
        books.put("9", new Book("9", "La fiesta del chivo", "Mario Vargas Llosa", true));
        books.put("10", new Book("10", "Aura", "Carlos Fuentes", true));
        books.put("11", new Book("11", "Antes que anochezca", "Reinaldo Arenas", true));
        books.put("12", new Book("12", "Doña Bárbara", "Rómulo Gallegos", true));
        books.put("13", new Book("13", "El túnel", "Ernesto Sabato", true));
        books.put("14", new Book("14", "Eva Luna", "Isabel Allende", true));
        books.put("15", new Book("15", "La casa verde", "Mario Vargas Llosa", true));
    }

    public String agregarLibro(String id, String title, String author) {
        if (books.containsKey(id)) {
            return "Error: El libro con este ID ya existe.";
        } else {
            books.put(id, new Book(id, title, author, true));
            return "Libro agregado con éxito.";
        }
    }

    public String buscarLibro(String id) {
        if (books.containsKey(id)) {
            Book book = books.get(id);
            logger.info("Consulta de libro exitosa: {}", book.getTitle());
            return book.getDetails();
        } else {
            logger.info("Consulta de libro fallida: No se encontró el libro con ID {}", id);
            return "Libro no encontrado";
        }
    }

    public List<String> buscarTodosLibros() {
        return books.values().stream()
                .map(Book::getDetails)
                .collect(Collectors.toList());
    }

    public List<Book> buscarLibros(String query) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) || book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Book> obtenerTodosLosLibros() {
        return books.values().stream()
                .sorted(Comparator.comparing(book -> Integer.parseInt(book.getId())))
                .collect(Collectors.toList());
    }


    public String registrarPrestamo(String libroId, String usuarioId) {
        Book book = books.get(libroId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            logger.info("Préstamo registrado para el libro ID {} por el usuario {}", libroId, usuarioId);
            return "Préstamo registrado con éxito para el libro ID " + libroId;
        }
        return "Error: El libro no está disponible o no existe";
    }

    public String registrarDevolucion(String libroId, String usuarioId) {
        Book book = books.get(libroId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            logger.info("Devolución registrada para el libro ID {} por el usuario {}", libroId, usuarioId);
            return "Devolución registrada con éxito para el libro ID " + libroId;
        }
        return "Error: El libro no está prestado o no existe";
    }

}
