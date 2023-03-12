package com.example.obrestdatajpa.controller;


import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class); // para mostrar logs de errores o comportamiento de la app

    //inyectar clase por constructor, es lo recomendable aunque se puede usar @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    //buscar solo un libro por id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id); //122xxx
        // OPCION 1 mediante POO
        if (bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();

        //OPCION 2 mediante P. Funcional
       // return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-agent"));
       if (book.getId() != null) {
           log.warn("trying to create a book"); // logs
           return ResponseEntity.badRequest().build();
       }
       Book result = bookRepository.save(book);
       return ResponseEntity.ok(result);
    }

    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) { //Si no tiene id es una creacion
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        // actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        log.info("REST request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
    
}
