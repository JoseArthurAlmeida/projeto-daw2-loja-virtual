package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.EnderecoDAO;
import br.edu.ifpb.es.daw.dao.impl.EnderecoDAOImpl;
import br.edu.ifpb.es.daw.entities.Endereco;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainEnderecoSave {
    public static void main(String[] args) throws DawException{
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            EnderecoDAO dao = new EnderecoDAOImpl(emf);
            Endereco endereco = new Endereco();

            endereco.setRua("Passa nada");
            endereco.setNumero("157");
            endereco.setBairro("Usina");
            endereco.setCidade("Esperança");
            endereco.setCep("58135000");
            endereco.setEstado("Paraiba");
            endereco.setComplemento("Proximo a lagoa");

            System.out.println("Antes de salvar: " + endereco);

            dao.save(endereco);

            System.out.println("Depois de salvar: " + endereco);
        }
    }
}
