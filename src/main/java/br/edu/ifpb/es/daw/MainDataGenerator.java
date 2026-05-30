package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.*;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDataGenerator {

    public static void main(String[] args) throws DawException{
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            System.out.println("======= INICIANDO GERAÇÃO DE DADOS =======");

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(emf);
            EnderecoDAO enderecoDAO = new EnderecoDAOImpl(emf);
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl(emf);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl(emf);
            PedidoDAO pedidoDAO = new PedidoDAOImpl(emf);
            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImpl(emf);
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAOImpl(emf);

            // Sufixo para garantir a unicidade nas execuções
            String sufixo = String.valueOf(System.currentTimeMillis());
            System.out.println("Sufixo da execução atual: " + sufixo);

            // USUÁRIOS
            Usuario u1 = new Usuario();
            u1.setNome("João Silva");
            u1.setEmail("joao_" + sufixo + "@email.com");
            u1.setSenha("123456");
            usuarioDAO.save(u1);

            Usuario u2 = new Usuario();
            u2.setNome("Maria Oliveira");
            u2.setEmail("maria_" + sufixo + "@email.com");
            u2.setSenha("abcdef");
            usuarioDAO.save(u2);

            Usuario u3 = new Usuario();
            u3.setNome("Pedro Santos");
            u3.setEmail("pedro_" + sufixo + "@email.com");
            u3.setSenha("senha123");
            usuarioDAO.save(u3);

            // CATEGORIAS
            Categoria c1 = new Categoria();
            c1.setNome("Smartphones_" + sufixo);
            categoriaDAO.save(c1);

            Categoria c2 = new Categoria();
            c2.setNome("Computadores_" + sufixo);
            categoriaDAO.save(c2);

            Categoria c3 = new Categoria();
            c3.setNome("Periféricos_" + sufixo);
            categoriaDAO.save(c3);

            // Criar Entidades Dependentes

            // ENDEREÇOS
            salvarEndereco(enderecoDAO, u1, "Rua das Flores", "123", "Centro");
            salvarEndereco(enderecoDAO, u2, "Av. Brasil", "400", "Portal");
            salvarEndereco(enderecoDAO, u3, "Rua do Sol", "88", "Centro");

            // PRODUTOS
            Produto p1 = new Produto();
            p1.setNome("Notebook Dell XPS " + sufixo);
            p1.setDescricao("Notebook Dell i7 16GB");
            p1.setPreco(new BigDecimal("7500.00"));
            p1.setQuantidade(10);
            p1.setCategorias(new ArrayList<>(List.of(c2)));
            produtoDAO.save(p1);

            Produto p2 = new Produto();
            p2.setNome("Smartphone Samsung S23 " + sufixo);
            p2.setDescricao("Galaxy S23 256GB");
            p2.setPreco(new BigDecimal("4000.00"));
            p2.setQuantidade(3);
            p2.setCategorias(new ArrayList<>(List.of(c1)));
            produtoDAO.save(p2);

            Produto p3 = new Produto();
            p3.setNome("Mouse Sem Fio Logitech " + sufixo);
            p3.setDescricao("Mouse Bluetooth Ergonômico");
            p3.setPreco(new BigDecimal("150.00"));
            p3.setQuantidade(50);
            p3.setCategorias(new ArrayList<>(List.of(c3)));
            produtoDAO.save(p3);

            Produto p4 = new Produto();
            p4.setNome("Teclado Mecânico " + sufixo);
            p4.setDescricao("Teclado RGB Switch Blue");
            p4.setPreco(new BigDecimal("180.00"));
            p4.setQuantidade(4);
            p4.setCategorias(new ArrayList<>(List.of(c3)));
            produtoDAO.save(p4);

            Produto p5 = new Produto();
            p5.setNome("Monitor LG 27 " + sufixo);
            p5.setDescricao("Monitor IPS Full HD");
            p5.setPreco(new BigDecimal("950.00"));
            p5.setQuantidade(15);
            p5.setCategorias(new ArrayList<>(Arrays.asList(c2, c3)));
            produtoDAO.save(p5);

            // PEDIDOS
            Pedido ped1 = new Pedido();
            ped1.setValor(new BigDecimal("7650.00")); // p1 + p3
            ped1.setStatus(StatusPedido.ENTREGUE);
            ped1.setUsuario(u1);
            pedidoDAO.save(ped1);

            Pedido ped2 = new Pedido();
            ped2.setValor(new BigDecimal("4000.00")); // p2
            ped2.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
            ped2.setUsuario(u2);
            pedidoDAO.save(ped2);

            Pedido ped3 = new Pedido();
            ped3.setValor(new BigDecimal("330.00")); // p3 + p4
            ped3.setStatus(StatusPedido.PROCESSANDO);
            ped3.setUsuario(u1); // Usuário 1 tem 2 pedidos
            pedidoDAO.save(ped3);

            Pedido ped4 = new Pedido();
            ped4.setValor(new BigDecimal("950.00")); // p5
            ped4.setStatus(StatusPedido.ENVIADO);
            ped4.setUsuario(u3);
            pedidoDAO.save(ped4);

            // ITENS DE PEDIDO
            salvarItem(itemPedidoDAO, ped1, p1, 1);
            salvarItem(itemPedidoDAO, ped1, p3, 1);

            salvarItem(itemPedidoDAO, ped2, p2, 1);

            salvarItem(itemPedidoDAO, ped3, p3, 1);
            salvarItem(itemPedidoDAO, ped3, p4, 1);

            salvarItem(itemPedidoDAO, ped4, p5, 1);

            // AVALIAÇÕES
            salvarAvaliacao(avaliacaoDAO, u1, p1, 5, "Excelente notebook, muito rápido.");
            salvarAvaliacao(avaliacaoDAO, u2, p1, 4, "Muito bom, porém um pouco pesado.");
            salvarAvaliacao(avaliacaoDAO, u2, p2, 5, "Câmera perfeita!");
            salvarAvaliacao(avaliacaoDAO, u3, p3, 3, "O mouse falha às vezes.");

            System.out.println("======= DADOS GERADOS COM SUCESSO! =======");
        }

    }

    // Métodos auxiliares
    private static void salvarEndereco(EnderecoDAO dao, Usuario u, String rua, String numero, String bairro) throws PersistenciaDawException {
        Endereco e = new Endereco();
        e.setRua(rua);
        e.setNumero(numero);
        e.setBairro(bairro);
        e.setCidade("Esperança");
        e.setEstado("PB");
        e.setCep("58135-000");
        e.setUsuario(u);
        dao.save(e);
    }

    private static void salvarItem(ItemPedidoDAO dao, Pedido ped, Produto prod, int qtd) throws PersistenciaDawException {
        ItemPedido item = new ItemPedido();
        item.setPedido(ped);
        item.setProduto(prod);
        item.setQuantidade(qtd);
        item.setPreco(prod.getPreco());
        dao.save(item);
    }

    private static void salvarAvaliacao(AvaliacaoDAO dao, Usuario u, Produto p, int nota, String comentario) throws PersistenciaDawException {
        Avaliacao av = new Avaliacao();
        av.setUsuario(u);
        av.setProduto(p);
        av.setNota(nota);
        av.setComentario(comentario);
        dao.save(av);
    }
}