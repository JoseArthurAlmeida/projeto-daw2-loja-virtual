package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PedidoDAO;
import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.PedidoDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Pedido;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainUsuarioDeleteAll {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            UsuarioDAO dao = new UsuarioDAOImpl(emf);
            PedidoDAO pedidoDao = new PedidoDAOImpl(emf);
            List<Pedido> pedidos = pedidoDao.getAll();
            for (Pedido p : pedidos) {
                pedidoDao.delete(p.getId());
            }
            List<Usuario> usuarios = dao.getAll();
            for(Usuario usuario : usuarios){
                dao.delete(usuario.getId());
            }
            System.out.println("Todos os usuários, endereços, avaliações e pedidos foram removidos.");
        }
    }
}