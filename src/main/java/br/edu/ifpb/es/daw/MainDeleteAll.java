package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.*;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainDeleteAll {

    public static void main(String[] args) {
        System.out.println("======= INICIANDO EXCLUSÃO DE DADOS (DELETE ALL) =======");

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImpl(emf);
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAOImpl(emf);
            PedidoDAO pedidoDAO = new PedidoDAOImpl(emf);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(emf);
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl(emf);

            // 1. Remove as linhas dos itens dos pedidos (dependência final)
            System.out.println("1. Removendo Itens de Pedido...");
            List<ItemPedido> itens = itemPedidoDAO.getAll();
            for (ItemPedido item : itens) {
                itemPedidoDAO.delete(item.getId());
            }

            // 2. Remove as avaliações dos produtos
            System.out.println("2. Removendo Avaliações...");
            List<Avaliacao> avaliacoes = avaliacaoDAO.getAll();
            for (Avaliacao av : avaliacoes) {
                avaliacaoDAO.delete(av.getId());
            }

            // 3. Remove os pedidos (após os itens serem removidos)
            System.out.println("3. Removendo Pedidos...");
            List<Pedido> pedidos = pedidoDAO.getAll();
            for (Pedido ped : pedidos) {
                pedidoDAO.delete(ped.getId());
            }

            // 4. Remove os produtos
            System.out.println("4. Removendo Produtos...");
            List<Produto> produtos = produtoDAO.getAll();
            for (Produto prod : produtos) {
                produtoDAO.delete(prod.getId());
            }

            // 5. Remove os usuários (apaga automaticamente os Endereços via CascadeType.ALL)
            System.out.println("5. Removendo Usuários (e Endereços em cascata)...");
            List<Usuario> usuarios = usuarioDAO.getAll();
            for (Usuario u : usuarios) {
                usuarioDAO.delete(u.getId());
            }

            // 6. Remove as categorias (última camada)
            System.out.println("6. Removendo Categorias...");
            List<Categoria> categorias = categoriaDAO.getAll();
            for (Categoria cat : categorias) {
                categoriaDAO.delete(cat.getId());
            }

            System.out.println("======= BANCO DE DADOS LIMPO COM SUCESSO! =======");

        } catch (Exception e) {
            System.err.println("ERRO: Falha ao tentar limpar o banco de dados!");
            e.printStackTrace();
        }
    }
}