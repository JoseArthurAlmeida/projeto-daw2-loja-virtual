package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AvaliacaoDAO;
import br.edu.ifpb.es.daw.dao.PedidoDAO;
import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.AvaliacaoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.PedidoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProdutoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ItemPedido;
import br.edu.ifpb.es.daw.entities.Pedido;
import br.edu.ifpb.es.daw.entities.Produto;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

public class MainConsultas {
    public static void main(String[] args) throws DawException {
        System.out.println("======= INICIANDO TESTE DE CONSULTAS =======");
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(emf);
            PedidoDAO pedidoDAO = new PedidoDAOImpl(emf);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAOImpl(emf);

            System.out.println("\n[2.1] Teste de Estoque Crítico (Produtos com 5 ou menos no estoque)");
            List<Produto> estoqueCritico = produtoDAO.findProdutosComEstoqueAbaixoDe(5);
            for (Produto p : estoqueCritico) {
                System.out.println(" -> ALERTA: " + p.getNome() + " | Qtd: " + p.getQuantidade());
            }

            System.out.println("\n[2.3] Teste de Faixa de Preço (Entre R$ 100 e R$ 1000)");
            List<Produto> porPreco = produtoDAO.findByFaixaPreco(new BigDecimal("100.00"), new BigDecimal("1000.00"));
            for (Produto p : porPreco) {
                System.out.println(" -> Filtro: " + p.getNome() + " | Preço: R$ " + p.getPreco());
            }

            System.out.println("\n[2.4] Teste de Média de Notas por Produto");
            List<Produto> todosProdutos = produtoDAO.getAll();
            if(!todosProdutos.isEmpty()) {
                Produto p = todosProdutos.get(0);
                Double media = avaliacaoDAO.calcularMediaNotasPorProduto(p.getId());
                System.out.println(" -> Produto: " + p.getNome() + " | Média de Estrelas: " + (media != null ? media : "Sem avaliações"));
            }

            // [2.2] Teste da Consulta de Pedidos por Usuário
            System.out.println("\n[2.2] Teste da Consulta de Pedidos por Usuário (Parâmetro Entidade)");
            List<Usuario> usuariosDb = usuarioDAO.getAll();
            if (!usuariosDb.isEmpty()) {
                Usuario usuarioTeste = usuariosDb.get(0); // Pega o primeiro usuário do banco
                System.out.println("Buscando pedidos do usuário: " + usuarioTeste.getNome());

                List<Pedido> pedidosDoUsuario = pedidoDAO.findByUsuario(usuarioTeste);
                for (Pedido p : pedidosDoUsuario) {
                    System.out.println(" -> Pedido ID: " + p.getId() + " | Valor: R$ " + p.getValor() + " | Status: " + p.getStatus());
                }
            } else {
                System.out.println("Nenhum usuário encontrado no banco de dados.");
            }

            // [2.5] Teste da Consulta de JOIN FETCH (Pedido com itens)
            System.out.println("\n[2.5] Teste da Consulta de JOIN FETCH (Pedido com Itens em LAZY)");
            List<Pedido> pedidosDb = pedidoDAO.getAll();
            if (!pedidosDb.isEmpty()) {
                Long idPedidoTeste = pedidosDb.get(0).getId(); // Pega o ID do primeiro pedido

                Pedido pedidoComItens = pedidoDAO.findByIdFetchItens(idPedidoTeste);
                if (pedidoComItens != null) {
                    System.out.println("Pedido encontrado (ID " + pedidoComItens.getId() + ").");
                    System.out.println("Listando itens usando a coleção que veio pelo JOIN FETCH:");

                    for (ItemPedido item : pedidoComItens.getItens()) {
                        System.out.println(" -> " + item.getQuantidade() + "x Produto: " + item.getProduto().getNome());
                    }
                }
            } else {
                System.out.println("Nenhum pedido encontrado no banco de dados.");
            }
        }

        System.out.println("======= FIM DOS TESTES =======");
    }
}