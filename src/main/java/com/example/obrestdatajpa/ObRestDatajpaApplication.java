package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

//		SpringApplication.run(ObRestDatajpaApplication.class, args);
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

//		crear libro
		Book book = new Book(null, "La Iliada", "Homero", 122, 23.99, LocalDate.of(1999, 12, 21), true);
		repository.save(book);

//		get
		System.out.println("Libros en bd: " + repository.count());
	}

}
