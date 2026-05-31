package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.PedidoDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.Pedido;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PedidoDAOImpl extends AbstractDAOImpl<Pedido, Long> implements PedidoDAO {

    public PedidoDAOImpl(EntityManagerFactory emf) {
        super(Pedido.class, emf);
    }

    @Override
    public List<Pedido> findByUsuario(Usuario usuario) throws PersistenciaDawException {
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT p FROM Pedido p WHERE p.usuario = :usuario";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);

            query.setParameter("usuario", usuario);
            return query.getResultList();

        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PersistenciaDawException("Ocorreu algum erro ao tentar recuperar os pedidos de um usuário", pe);
        }
    }
}