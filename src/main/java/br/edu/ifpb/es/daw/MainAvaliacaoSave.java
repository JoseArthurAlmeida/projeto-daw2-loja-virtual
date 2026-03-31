package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AvaliacaoDAO;
import br.edu.ifpb.es.daw.dao.impl.AvaliacaoDAOImpl;
import br.edu.ifpb.es.daw.entities.Avaliacao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainAvaliacaoSave {
    public static void main(String[] args) throws DawException{
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){

            AvaliacaoDAO dao = new AvaliacaoDAOImpl(emf);
            Avaliacao avaliacao = new Avaliacao();

            avaliacao.setComentario("ainda não testei, mas veio bem embalado.");
            avaliacao.setNota(5);

            System.out.println("Antes de salvar: " + avaliacao);

            dao.save(avaliacao);

            System.out.println("Depois de salvar: " + avaliacao);
        }
    }
}