package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.ProdutoDAO;
import br.edu.ifpb.es.daw.entities.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAOImpl extends AbstractDAOImpl<Produto, Long> implements ProdutoDAO {
    public ProdutoDAOImpl(EntityManagerFactory emf) {
        super(Produto.class,emf);
    }

    @Override
    public List<Produto> findProdutosComEstoqueAbaixoDe(Integer quantidadeMinima) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p WHERE p.quantidade <= :quantidadeMinima", Produto.class)
                    .setParameter("quantidadeMinima", quantidadeMinima)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> findByFaixaPreco(BigDecimal min, BigDecimal max) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p WHERE p.preco BETWEEN :min AND :max", Produto.class)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
