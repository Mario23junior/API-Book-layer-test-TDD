package com.project.libraryapi.model.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.libraryapi.Model.Book;
import com.project.libraryapi.service.repository.BookRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {
    
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	BookRepository repository;
	
	@Test
	@DisplayName("Deve retornar verdadeiro quando existir um livro na base com o ISBN já informado")
	public void returnTrueWhenIsbnExists() {
	 
		// cenario
		  String isbn = "1234";
		  Book book = Book.builder().title("Gravit falls").author("dipper").isbn(isbn).build();
		  entityManager.persist(book);
		
		// execucao
		 boolean exist = repository.existsByIsbn(isbn);
		 
		// verificação
		 assertThat(exist).isTrue();
		
	}
	
	@Test
	@DisplayName("Deve retornar false quando não existir um livro na base com o ISBN já informado")
	public void returnFalseWhenIsbnExists() {
	 
		// cenario
		  String isbn = "1234";
		
		// execucao
		 boolean exist = repository.existsByIsbn(isbn);
		 
		// verificação
		 assertThat(exist).isFalse();
		
	}
}
















