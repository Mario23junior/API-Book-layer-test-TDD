package com.project.libraryapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.libraryapi.dtoModel.BookDTO;

@RestController
@RequestMapping("/api/books/")
public class BookController {
    
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO create() {
		
		BookDTO dto = new BookDTO();
		dto.setId((long) 11);
		dto.setAuthor("Author");
		dto.setTitle("meu livro");
		dto.setIsbn("129949323");
		return dto;
	}
}