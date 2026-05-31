package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.Pedido;
import br.edu.ifpb.es.daw.entities.Usuario;

import java.util.List;

public interface PedidoDAO extends DAO<Pedido, Long> {

    // [2.2] Consulta parametrizada com uma Entidade
    List<Pedido> findByUsuario(Usuario usuario) throws PersistenciaDawException;
}
