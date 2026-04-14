package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.*;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.UUID;

public class MainPedidoSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            PedidoDAO pedidoDAO = new PedidoDAOImpl(emf);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl(emf);
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(emf);

            System.out.println("Iniciando persistência completa do Pedido...");

            Categoria categoria = new Categoria();
            categoria.setNome("Hardware High-End " + System.nanoTime());
            categoriaDAO.save(categoria);

            Usuario cliente = new Usuario();
            cliente.setNome("Fulano de Tal " + System.nanoTime());
            cliente.setEmail("user" + System.nanoTime() + "@teste.com");
            cliente.setSenha("123456");
            usuarioDAO.save(cliente);

            Produto produto = new Produto();
            produto.setNome("RTX 4090 Rog Strix " + System.nanoTime());
            produto.setDescricao("Placa de video top");
            produto.setPreco(new BigDecimal("14500.00"));
            produto.setQuantidade(10);
            produto.setUrlImagem("http://loja.com/img/" + UUID.randomUUID());

            // Produto -> Categoria
            produto.getCategorias().add(categoria);
            produtoDAO.save(produto);

            Pedido pedido = new Pedido();
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
            pedido.setValor(produto.getPreco());

            // Pedido -> Usuário
            pedido.setUsuario(cliente);

            ItemPedido item = new ItemPedido();
            item.setQuantidade(1);
            item.setPreco(produto.getPreco());

            // Item -> Produto
            item.setProduto(produto);

            item.setPedido(pedido);
            pedido.getItens().add(item);

            System.out.println("Salvando Pedido (ID atual: " + pedido.getId() + ")...");

            pedidoDAO.save(pedido);

            System.out.println("--- SUCESSO ---");
            System.out.println("Pedido ID: " + pedido.getId());
            System.out.println("Item ID: " + pedido.getItens().get(0).getId());
            System.out.println("Usuário associado: " + pedido.getUsuario().getNome());
            System.out.println("Produto no item: " + pedido.getItens().get(0).getProduto().getNome());
        } catch (Exception e) {
            System.err.println("Erro ao salvar pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}