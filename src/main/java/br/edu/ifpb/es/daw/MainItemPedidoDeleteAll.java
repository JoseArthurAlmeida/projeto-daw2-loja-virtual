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
            List<ItemPedido> itens = dao.getAll();

            for (ItemPedido item : itens) {
                dao.delete(item.getId());
            }

        }
    }
}