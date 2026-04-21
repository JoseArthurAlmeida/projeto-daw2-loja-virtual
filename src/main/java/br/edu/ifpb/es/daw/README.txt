# Ordem de Execução Recomendada
Clica com o botão direito e executa as classes main seguindo esta sequência:

1. MainItemPedidoDeleteAll: Remove as linhas dos itens dos pedidos (dependência final).
2. MainAvaliacaoDeleteAll: Remove as avaliações dos produtos.
3. MainPedidoDeleteAll: Liberta os pedidos (após os itens serem removidos).
4. MainProdutoDeleteAll: Remove os produtos e as suas associações com categorias.
5. MainUsuarioDeleteAll: Remove os utilizadores (apaga automaticamente os Endereços via CascadeType.ALL).
6. MainCategoriaDeleteAll: Remove as categorias (última camada).