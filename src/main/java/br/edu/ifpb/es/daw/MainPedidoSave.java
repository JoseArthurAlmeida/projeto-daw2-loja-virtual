package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PedidoDAO;
import br.edu.ifpb.es.daw.dao.impl.PedidoDAOImpl;
import br.edu.ifpb.es.daw.entities.Pedido;
import br.edu.ifpb.es.daw.entities.StatusPedido;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class MainPedidoSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            PedidoDAO dao = new PedidoDAOImpl(emf);
            Pedido pedido = new Pedido();

            pedido.setValor(new BigDecimal("1500.50"));

            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

            System.out.println("Antes de salvar: " + pedido);

            dao.save(pedido);

            System.out.println("Depois de salvar: " + pedido);
        }
    }
}