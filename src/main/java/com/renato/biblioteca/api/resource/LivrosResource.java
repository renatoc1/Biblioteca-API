package com.renato.biblioteca.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renato.biblioteca.api.event.RecursoCriadoEvent;
import com.renato.biblioteca.api.model.Livros;
import com.renato.biblioteca.api.repository.LivrosRepository;
import com.renato.biblioteca.api.repository.filter.LivrosFilter;
import com.renato.biblioteca.api.service.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResource {

	@Autowired
	private LivrosRepository livrosRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private LivrosService livrosService;

	@GetMapping
	public Page<Livros> pesquisar(LivrosFilter livroFilter, Pageable pageable) {
		return livrosRepository.filtrar(livroFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<Livros> criar(@Valid @RequestBody Livros livro, HttpServletResponse response) {
		Livros livroSalvo = livrosRepository.save(livro);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, livroSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livros> buscarPeloId(@PathVariable Long id) {
		Livros livro = livrosRepository.findOne(id);
		return livro != null ? ResponseEntity.ok(livro) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		livrosRepository.delete(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livros> atualizar(@PathVariable Long id, @Valid @RequestBody Livros livro) {
		Livros livroSalvo = livrosService.atualizar(id, livro);
		return ResponseEntity.ok(livroSalvo);
	}

}
