package com.renato.biblioteca.api.repository.livros;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.renato.biblioteca.api.model.Livros;
import com.renato.biblioteca.api.model.Livros_;
import com.renato.biblioteca.api.repository.filter.LivrosFilter;

public class LivrosRepositoryImpl implements LivrosRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Livros> filtrar(LivrosFilter livrosFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Livros> criteria = builder.createQuery(Livros.class);
		Root<Livros> root = criteria.from(Livros.class);

		Predicate[] predicates = criarRestricoes(livrosFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Livros> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(livrosFilter));
	}

	private Predicate[] criarRestricoes(LivrosFilter livrosFilter, CriteriaBuilder builder, Root<Livros> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(livrosFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Livros_.nome)),
					"%" + livrosFilter.getNome().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(livrosFilter.getAutor())) {
			predicates.add(builder.like(builder.lower(root.get(Livros_.autor)),
					"%" + livrosFilter.getAutor().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Livros> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Long total(LivrosFilter livrosFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Livros> root = criteria.from(Livros.class);

		Predicate[] predicates = criarRestricoes(livrosFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
