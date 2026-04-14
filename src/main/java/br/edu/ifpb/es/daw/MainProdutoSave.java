package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.CategoriaDAO;
import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.impl.CategoriaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.entities.Categoria;
import br.edu.ifpb.es.daw.entities.Produto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.UUID;

public class MainProdutoSave {

	public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

			ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);
			CategoriaDAO categoriaDAO = new CategoriaDAOImpl(emf);

			Categoria categoria = new Categoria();
			categoria.setNome("Intel " + System.nanoTime());
			categoriaDAO.save(categoria);

			Produto produto1 = new Produto();
			produto1.setNome("Placa de Vídeo Intel ARC " + System.nanoTime());
			produto1.setDescricao("Placa entusiasta.");
			produto1.setPreco(new BigDecimal("1250.00"));
			produto1.setQuantidade(5);
			produto1.setUrlImagem(String.format("https://exemplo.com/%s_arc.jpg", UUID.randomUUID()));

			produto1.getCategorias().add(categoria);

			Produto produto2 = new Produto();
			produto2.setNome("Processador Core i9 " + System.nanoTime());
			produto2.setDescricao("Processador de alta performance.");
			produto2.setPreco(new BigDecimal("4500.00"));
			produto2.setQuantidade(10);
			produto2.setUrlImagem(String.format("https://exemplo.com/%s_i9.jpg", UUID.randomUUID()));

			produto2.getCategorias().add(categoria);

			produtoDAO.save(produto1);
			produtoDAO.save(produto2);

			System.out.println("Categoria criada e dois produtos associados a ela");
		}
	}
}