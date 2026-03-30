package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.entities.Produto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.UUID;

public class MainProdutoSave {

	public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

			ProdutoDAO dao = new ProdutoDAOImpl(emf);
			Produto produto = new Produto();

			produto.setNome("Placa de Vídeo Nvidia GeForce RTX 4090");
			produto.setDescricao("Placa de vídeo entusiasta com 24GB GDDR6X.");
			produto.setPreco(new BigDecimal("12500.00"));
			produto.setQuantidade(5);
			produto.setUrlImagem(String.format("https://exemplo.com/imagens/%s_rtx4090_gigabyte.jpg", UUID.randomUUID()));

			System.out.println("Antes de salvar: " + produto);

			dao.save(produto);

			System.out.println("Depois de salvar: " + produto);
		}
	}
}