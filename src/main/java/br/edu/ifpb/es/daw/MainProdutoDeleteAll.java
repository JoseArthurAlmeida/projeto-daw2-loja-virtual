package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.entities.Produto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainProdutoDeleteAll {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			ProdutoDAO dao = new ProdutoDAOImpl(emf);

			System.out.println("Buscando produtos...");
			List<Produto> produtos = dao.getAll();
			int deletados = 0;

			for (Produto produto : produtos) {
				try {
					dao.delete(produto.getId());
					deletados++;
				} catch (Exception e) {
					System.err.println("Não foi possível deletar o Produto ID " + produto.getId() + " (Pode estar vinculado a Itens ou Avaliações).");
				}
			}

			System.out.println("--- SUCESSO ---");
			System.out.println("Total de produtos encontrados: " + produtos.size());
			System.out.println("Total de produtos deletados: " + deletados);
		}
	}

}
