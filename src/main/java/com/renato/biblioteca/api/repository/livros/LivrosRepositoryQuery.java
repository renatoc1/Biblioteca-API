package com.renato.biblioteca.api.repository.livros;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.renato.biblioteca.api.model.Livros;
import com.renato.biblioteca.api.repository.filter.LivrosFilter;

public interface LivrosRepositoryQuery {
	
	public Page<Livros> filtrar(LivrosFilter livrosFilter, Pageable pageable);

}
