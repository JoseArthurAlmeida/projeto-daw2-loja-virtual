package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.CategoriaDAO;
import br.edu.ifpb.es.daw.dao.impl.CategoriaDAOImpl;
import br.edu.ifpb.es.daw.entities.Categoria;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainCategoriaDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            CategoriaDAO dao = new CategoriaDAOImpl(emf);

            System.out.println("Buscando categorias...");
            List<Categoria> categorias = dao.getAll();
            int deletados = 0;

            for (Categoria categoria : categorias) {
                try {
                    dao.delete(categoria.getId());
                    deletados++;
                } catch (Exception e) {
                    System.err.println("Não foi possível deletar a Categoria ID " + categoria.getId() + " (Pode ter Produtos usando ela).");
                }
            }

            System.out.println("--- SUCESSO ---");
            System.out.println("Total de categorias encontradas: " + categorias.size());
            System.out.println("Total de categorias deletadas: " + deletados);
        }
    }
}