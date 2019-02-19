package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	private List<Integer> autoresId = new ArrayList<>(); // fazemos new para evitar NullPointerException

	// Context and Dependency Injection
	@Inject
	private LivroDao dao;

	@Inject
	private AutorDao autorDao;

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	@Transactional
	public String salvar() { // Mudamos o tipo de retorno
		for (Integer autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}

		dao.salvar(livro);
		System.out.println("Livro Cadastrado: " + livro);

		return "/livros/lista?faces-redirect=true"; // E retornamos a página que o usuário irá sem o .xhtml
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
