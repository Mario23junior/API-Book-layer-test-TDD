package com.project.libraryapi.Controller.Exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.project.libraryapi.Exception.BusinessException;

public class ApiErros {
   		
	  private List<String> errors;
		
	  public ApiErros(BindingResult bindingResult) {
		  this.errors = new ArrayList<>();
		  bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
	  }
	  
	  public ApiErros(BusinessException ex) {
		   this.errors = Arrays.asList(ex.getMessage());
	  }

	public List<String> getErrors() {
		return errors;
	}
	  
	  
}
