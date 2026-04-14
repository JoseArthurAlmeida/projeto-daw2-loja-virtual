package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AvaliacaoDAO;
import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.impl.AvaliacaoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.entities.Avaliacao;
import br.edu.ifpb.es.daw.entities.Produto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class MainAvaliacaoSave {
    public static void main(String[] args) throws DawException{
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){

            AvaliacaoDAO dao = new AvaliacaoDAOImpl(emf);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);

            Produto produto = new Produto();
            produto.setNome("Teclado Genérico " + System.nanoTime());
            produto.setDescricao("Teclado bom");
            produto.setQuantidade(10);// Regra 12
            produto.setPreco(new BigDecimal("50.00"));

            produtoDAO.save(produto);

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setComentario("ainda não testei, mas veio bem embalado.");
            avaliacao.setNota(5);

            avaliacao.setProduto(produto);

            produto.getAvaliacoes().add(avaliacao);

            System.out.println("Antes de salvar a avaliação: " + avaliacao);

            dao.save(avaliacao);

            System.out.println("Depois de salvar: " + avaliacao);
            System.out.println("Produto avaliado: " + avaliacao.getProduto().getNome());
        }
    }
}