import { List, Button } from 'antd';
import React, { useState } from 'react';

const Pedidos: React.FC = () => {
  const initialData = [
    {
      nome: "Cliente #1",
      preco: 100.00,
      unidade: "R$",
      data: "10/01/2021",
      status: "Aprovado",
      acao: "Aprovar",    
    },
    {
      nome: "Cliente #2",
      preco: 100.00,
      unidade: "R$",
      data: "18/05/2021",
      status: "Aprovado",
      acao: "Aprovar",    
    }
  ];

  const [data, setData] = useState(initialData);

  const handleDelete = (index: number) => {
    const newData = data.filter((_, i) => i !== index);
    setData(newData);
  };

  return (
    <List
      size="large"
      bordered
      dataSource={data}
      renderItem={(item, index) => 
        <List.Item actions={[
        <Button type="link">Editar</Button>, 
        <Button type="link" onClick={() => handleDelete(index)}>Excluir</Button>
        ]}>
          <List.Item.Meta
            title={item.nome}
            description={`${item.unidade} ${item.preco} - ${item.data} - ${item.status}`}
          />
        </List.Item>
      }
    />
  );
};

export default Pedidos;
