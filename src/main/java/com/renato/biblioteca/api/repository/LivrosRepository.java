package com.renato.biblioteca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.biblioteca.api.model.Livros;
import com.renato.biblioteca.api.repository.livros.LivrosRepositoryQuery;

public interface LivrosRepository extends JpaRepository<Livros, Long>, LivrosRepositoryQuery{

}
