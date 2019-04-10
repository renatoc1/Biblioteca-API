package com.renato.biblioteca.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renato.biblioteca.api.model.Livros;
import com.renato.biblioteca.api.repository.LivrosRepository;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;

	public Livros atualizar(Long id, Livros livro) {
		
		Livros livroSalvo = livrosRepository.findById(id).orElse(null);
		if (livroSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(livro, livroSalvo, "id");
		return livrosRepository.save(livroSalvo);
		
	}
	
}
