package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ItemPedidoDAO;
import br.edu.ifpb.es.daw.dao.impl.ItemPedidoDAOImpl;
import br.edu.ifpb.es.daw.entities.ItemPedido;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainItemPedidoDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            ItemPedidoDAO dao = new ItemPedidoDAOImpl(emf);

            System.out.println("Buscando itens de pedido...");
            List<ItemPedido> itens = dao.getAll();
            int deletados = 0;

            for (ItemPedido item : itens) {
                try {
                    dao.delete(item.getId());
                    deletados++;
                } catch (Exception e) {
                    System.err.println("Não foi possível deletar o ItemPedido ID " + item.getId());
                }
            }

            System.out.println("--- SUCESSO ---");
            System.out.println("Total de itens encontrados: " + itens.size());
            System.out.println("Total de itens deletados: " + deletados);
        }
    }
}