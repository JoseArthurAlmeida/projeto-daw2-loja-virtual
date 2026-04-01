package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.CategoriaDAO;
import br.edu.ifpb.es.daw.dao.impl.CategoriaDAOImpl;
import br.edu.ifpb.es.daw.entities.Categoria;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainCategoriaSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            CategoriaDAO dao = new CategoriaDAOImpl(emf);
            Categoria categoria = new Categoria();

            categoria.setNome("Processadores " + System.nanoTime());

            System.out.println("Antes de salvar: " + categoria);

            dao.save(categoria);

            System.out.println("Depois de salvar: " + categoria);
        }
    }
}