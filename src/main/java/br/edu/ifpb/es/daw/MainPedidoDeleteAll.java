package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PedidoDAO;
import br.edu.ifpb.es.daw.dao.impl.PedidoDAOImpl;
import br.edu.ifpb.es.daw.entities.Pedido;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainPedidoDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            PedidoDAO dao = new PedidoDAOImpl(emf);

            System.out.println("Buscando pedidos...");
            List<Pedido> pedidos = dao.getAll();
            int deletados = 0;

            for (Pedido pedido : pedidos) {
                try {
                    dao.delete(pedido.getId());
                    deletados++;
                } catch (Exception e) {
                    System.err.println("Não foi possível deletar o Pedido ID " + pedido.getId() + " (Pode ter Itens vinculados).");
                }
            }

            System.out.println("--- SUCESSO ---");
            System.out.println("Total de pedidos encontrados: " + pedidos.size());
            System.out.println("Total de pedidos deletados: " + deletados);
        }
    }
}
