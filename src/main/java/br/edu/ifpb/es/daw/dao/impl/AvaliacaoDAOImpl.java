package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AvaliacaoDAO;
import br.edu.ifpb.es.daw.entities.Avaliacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AvaliacaoDAOImpl extends AbstractDAOImpl<Avaliacao, Long> implements AvaliacaoDAO {
    public AvaliacaoDAOImpl(EntityManagerFactory emf) {
        super(Avaliacao.class,emf);
    }

    @Override
    public Double calcularMediaNotasPorProduto(Long idProduto) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.produto.id = :idProduto", Double.class)
                    .setParameter("idProduto", idProduto)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
