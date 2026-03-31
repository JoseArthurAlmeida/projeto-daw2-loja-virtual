package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.EnderecoDAO;
import br.edu.ifpb.es.daw.dao.impl.EnderecoDAOImpl;
import br.edu.ifpb.es.daw.entities.Endereco;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainEnderecoDeleteAll {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            EnderecoDAO dao = new EnderecoDAOImpl(emf);
            List<Endereco> enderecos = dao.getAll();

             for(Endereco endereco : enderecos){
                 dao.delete(endereco.getId());
             }
        }
    }
}