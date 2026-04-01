package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.UUID;

public class MainUsuarioSave {
    public static void main(String[] args) throws DawException{
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){

            UsuarioDAO dao = new UsuarioDAOImpl(emf);
            Usuario usuario = new Usuario();

            usuario.setNome("Denis");
            usuario.setEmail("denis"+ UUID.randomUUID().toString().substring(0, 8) + "@gmail.com");
            usuario.setSenha("12345");

            System.out.println("Antes de salvar: " + usuario);

            dao.save(usuario);

            System.out.println("Depois de salvar: " + usuario);
        }
    }
}