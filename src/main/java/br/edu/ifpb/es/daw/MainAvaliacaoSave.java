package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AvaliacaoDAO;
import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.AvaliacaoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Avaliacao;
import br.edu.ifpb.es.daw.entities.Produto;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.UUID;

public class MainAvaliacaoSave {
    public static void main(String[] args) throws DawException{
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            AvaliacaoDAO dao = new AvaliacaoDAOImpl(emf);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(emf);

            Usuario usuario = new Usuario();
            usuario.setNome("Denis Avaliador");
            usuario.setEmail("denis.aval" + UUID.randomUUID().toString().substring(0, 5) + "@gmail.com");
            usuario.setSenha("12345");
            usuarioDAO.save(usuario);


            Produto produto = new Produto();
            produto.setNome("Teclado Mecânico " + System.nanoTime());
            produto.setDescricao("Teclado muito bom");
            produto.setQuantidade(10);
            produto.setPreco(new BigDecimal("250.00"));
            produtoDAO.save(produto);

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setComentario("Excelente produto, recomendo!");
            avaliacao.setNota(5);

            avaliacao.setProduto(produto);
            avaliacao.setUsuario(usuario);

            produto.getAvaliacoes().add(avaliacao);
            usuario.getAvaliacoes().add(avaliacao);

            System.out.println("Antes de salvar a avaliação: " + avaliacao);

            dao.save(avaliacao);

            System.out.println("--- SUCESSO ---");
            System.out.println("Avaliação ID: " + avaliacao.getId());
            System.out.println("Usuário que avaliou: " + avaliacao.getUsuario().getNome());
            System.out.println("Produto avaliado: " + avaliacao.getProduto().getNome());

        }
    }
}