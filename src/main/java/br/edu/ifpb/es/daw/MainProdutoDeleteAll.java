package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.entities.Produto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainProdutoDeleteAll {

	public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			ProdutoDAO dao = new ProdutoDAOImpl(emf);
			List<Produto> produtos = dao.getAll();
			for (Produto produto : produtos) {
				dao.delete(produto.getId());
			}
		}
	}

}
