package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ItemPedidoDAO;
import br.edu.ifpb.es.daw.dao.impl.ItemPedidoDAOImpl;
import br.edu.ifpb.es.daw.entities.ItemPedido;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class MainItemPedidoSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            ItemPedidoDAO dao = new ItemPedidoDAOImpl(emf);
            ItemPedido item = new ItemPedido();

            item.setQuantidade(2);
            item.setPreco(new BigDecimal("299.90"));

            System.out.println("Antes de salvar: " + item);

            dao.save(item);

            System.out.println("Depois de salvar: " + item);
        }
    }
}