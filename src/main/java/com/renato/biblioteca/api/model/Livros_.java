package com.renato.biblioteca.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livros.class)
public abstract class Livros_ {

	public static volatile SingularAttribute<Livros, String> nome;
	public static volatile SingularAttribute<Livros, Long> id;
	public static volatile SingularAttribute<Livros, Integer> edicao;
	public static volatile SingularAttribute<Livros, Integer> quantidade;
	public static volatile SingularAttribute<Livros, String> autor;

	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String EDICAO = "edicao";
	public static final String QUANTIDADE = "quantidade";
	public static final String AUTOR = "autor";

}

