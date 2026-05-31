package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PedidoDAO;
import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.PedidoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ItemPedido;
import br.edu.ifpb.es.daw.entities.Pedido;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainConsultas {
    public static void main(String[] args) throws DawException {
        System.out.println("======= INICIANDO TESTE DE CONSULTAS =======");
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(emf);
            PedidoDAO pedidoDAO = new PedidoDAOImpl(emf);

            // [2.1] Teste da Consulta de Estoque Crítico

            // [2.3] Teste da Consulta de Faixa de Preço

            // [2.4] Teste da Consulta de Média de Notas na Avaliação

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