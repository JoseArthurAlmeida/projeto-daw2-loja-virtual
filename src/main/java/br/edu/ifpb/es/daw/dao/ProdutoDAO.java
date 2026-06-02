package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.Produto;
import java.math.BigDecimal;
import java.util.List;

public interface ProdutoDAO extends DAO<Produto, Long> {

    List<Produto> findProdutosComEstoqueAbaixoDe(Integer quantidadeMinima);

    List<Produto> findByFaixaPreco(BigDecimal min, BigDecimal max);

}
