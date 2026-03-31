package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AvaliacaoDAO;
import br.edu.ifpb.es.daw.entities.Avaliacao;
import jakarta.persistence.EntityManagerFactory;

public class AvaliacaoDAOImpl extends AbstractDAOImpl<Avaliacao, Long> implements AvaliacaoDAO {
    public AvaliacaoDAOImpl(EntityManagerFactory emf) {
        super(Avaliacao.class, emf);
    }
}
