// ProductContext.tsx
import React, { createContext, useState, useEffect } from 'react';
import api from '../Controller/Api';

export const ProductsContext = createContext<any>(null);

interface ProductsProviderProps {
  children: React.ReactNode;
}

export const ProductsProvider: React.FC<ProductsProviderProps> = ({ children }) => {
  const [products, setProducts] = useState<any[]>([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await api.get('/produto');
      setProducts(response.data);
    } catch (error) {
      console.error('Erro ao carregar produtos:', error);
    }
  };

  const addProduct = async (product: any) => {
    try {
      const response = await api.post('/produto/create-produto', product);
      setProducts([...products, response.data]);
    } catch (error) {
      console.error('Erro ao adicionar produto:', error);
      throw error;
    }
  };

  const removeProduct = async (id: string) => {
    try {
      await api.delete(`/produto/delete-produto/${id}`);
      setProducts(products.filter((product: any) => product.id !== id));
    } catch (error) {
      console.error('Erro ao remover produto:', error);
      throw error;
    }
  };

  const searchProducts = async (searchText: string) => {
    try {
      const response = await api.get(`/produto/search?text=${searchText}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar produtos:', error);
      throw error;
    }
  };

  return (
    <ProductsContext.Provider value={{ products, addProduct, removeProduct, searchProducts }}>
      {children}
    </ProductsContext.Provider>
  );
};
