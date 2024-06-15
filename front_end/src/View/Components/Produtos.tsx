// produtos.tsx
import React, { useContext } from 'react';
import { Form, Input, Button, Select, message } from 'antd';
import { ProductsContext } from '../../Controller/ProductContext';

const { TextArea } = Input;
const { Option } = Select;

const categorias = ['Proteína', 'Vitaminas', 'Minerais', 'Aminoácidos', 'Energia'];
const objetivos = ['Ganhar Massa', 'Perder Peso', 'Manter Saúde', 'Desempenho Esportivo'];
const statusOptions = ['Disponível', 'Indisponível'];

const Produtos: React.FC = () => {
  const { addProduct } = useContext(ProductsContext);
  const [form] = Form.useForm();

  const onFinish = (values: any) => {
    addProduct(values);
    form.resetFields();
    message.success('Produto cadastrado com sucesso!');
  };

  const onFinishFailed = (errorInfo: any) => {
    console.log('Failed:', errorInfo);
    message.error('Falha ao cadastrar o produto!');
  };

  return (
    <div>
      <h1>Cadastro de Produto</h1>
      <Form
        layout="vertical"
        form={form}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
      >
        <Form.Item
          label="Nome do produto"
          name="nome"
          rules={[{ required: true, message: 'Por favor, insira o nome do produto!' }]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="Preço"
          name="preco"
          rules={[{ required: true, message: 'Por favor, insira o preço do produto!' }]}
        >
          <Input type="number" prefix="R$" />
        </Form.Item>

        <Form.Item
          label="Categoria"
          name="categoria"
          rules={[{ required: true, message: 'Por favor, selecione a categoria do produto!' }]}
        >
          <Select placeholder="Selecione uma categoria">
            {categorias.map((categoria, index) => (
              <Option key={index} value={categoria}>{categoria}</Option>
            ))}
          </Select>
        </Form.Item>

        <Form.Item
          label="Por objetivo"
          name="objetivo"
          rules={[{ required: true, message: 'Por favor, selecione o objetivo do produto!' }]}
        >
          <Select placeholder="Selecione um objetivo">
            {objetivos.map((objetivo, index) => (
              <Option key={index} value={objetivo}>{objetivo}</Option>
            ))}
          </Select>
        </Form.Item>

        <Form.Item
          label="Descrição"
          name="descricao"
          rules={[{ required: true, message: 'Por favor, insira a descrição do produto!' }]}
        >
          <TextArea rows={4} />
        </Form.Item>

        <Form.Item
          label="Status"
          name="status"
          rules={[{ required: true, message: 'Por favor, selecione o status do produto!' }]}
        >
          <Select placeholder="Selecione o status">
            {statusOptions.map((status, index) => (
              <Option key={index} value={status}>{status}</Option>
            ))}
          </Select>
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ marginRight: '10px' }}>
            Salvar
          </Button>
          <Button type="default">
            Cancelar
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default Produtos;
