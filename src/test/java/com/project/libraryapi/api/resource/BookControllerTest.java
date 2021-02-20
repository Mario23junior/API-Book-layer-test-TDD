package com.project.libraryapi.api.resource;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.libraryapi.Service.BookService;
import com.project.libraryapi.dtoModel.BookDTO;
import com.project.libraryapi.Model.Book;

import org.springframework.http.MediaType;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class BookControllerTest {
    
	static String BOOK_KEY = "/api/books/";
	
	 @Autowired
	 MockMvc mvc;
	 
	 @MockBean
	 BookService service;
	 
	@Test
	@DisplayName("Metodo para salvar livros no banco")
	public void createBookTest() throws Exception {
		BookDTO dto = BookDTO.builder().author("Arthur").title("harry ventuy").isbn("23112").build();
		Book saveBook = Book.builder().id(101).author("Arthur").title("harry ventuy").isbn("23112").build();

		BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(saveBook);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		MockHttpServletRequestBuilder requestFake = MockMvcRequestBuilders
		           .post(BOOK_KEY)
		           .contentType(MediaType.APPLICATION_JSON)
		           .accept(MediaType.APPLICATION_JSON)
		           .content(json);
		
		mvc
		.perform(requestFake)
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("id").value(101))
		.andExpect(jsonPath("title").value(dto.getTitle()))
	    .andExpect(jsonPath("author").value(dto.getAuthor()))
	    .andExpect(jsonPath("isbn").value(dto.getIsbn()));
	
	}
	
	@Test
	@DisplayName("Lançar erro quando não tentar inserir dados invalidos")
	public void createInvalidBookTest() {
		
	}
}
