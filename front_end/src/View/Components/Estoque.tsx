import React, { useContext, useState } from 'react';
import { Input, List, Form, Button } from 'antd';
import { ProductsContext } from '../../Controller/ProductContext';

const { Search } = Input;

const Estoque: React.FC = () => {
  const [searchText, setSearchText] = useState('');
  const { products, searchProducts } = useContext(ProductsContext);

  const handleSearch = (text: string) => {
    setSearchText(text);
  };

  const renderProductList = () => {
    if (!searchText) return null; // Se não houver pesquisa, não renderiza a lista

    const filteredProducts = products.filter((product: any) =>
      product.nome.toLowerCase().includes(searchText.toLowerCase())
    );

    return (
      <List
        itemLayout="vertical"
        size="large"
        dataSource={filteredProducts}
        renderItem={(product: any) => (
          <List.Item key={product.codigo}>
            <h3>{product.nome}</h3>
            <p><strong>Código:</strong> {product.codigo}</p>
            <p><strong>Preço:</strong> R${product.preco}</p>
            <p><strong>Categoria:</strong> {product.categoria}</p>
            <p><strong>Objetivo:</strong> {product.objetivo}</p>
            <p><strong>Descrição:</strong> {product.descricao}</p>
            {/* Renderizar fotos aqui */}
          </List.Item>
        )}
      />
    );
  };

  return (
    <div>
      <h1>Consultar estoque</h1>
      <Form layout="inline">
        <Form.Item>
          <Search
            placeholder="Pesquisar produto"
            onSearch={handleSearch}
          />
        </Form.Item>
      </Form>
      {renderProductList()}
    </div>
  );
};

export default Estoque;
