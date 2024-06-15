import React, { useState, useContext } from 'react';
import { Form, Input, Button, List, message, Select } from 'antd';
import { UserContext } from '../../Controller/UserContext';

const { Option } = Select;

const Usuarios: React.FC = () => {
  const [nome, setNome] = useState('');
  const [sobrenome, setSobrenome] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [celular, setCelular] = useState('');
  const [status, setStatus] = useState('');
  const { users, addUser, removeUser } = useContext(UserContext);

  const handleAddUser = async () => {
    if (nome && sobrenome && email && password && celular && status) {
      try {
        await addUser({ nome, sobrenome, email, senha: password, celular, status });
        setNome('');
        setSobrenome('');
        setEmail('');
        setPassword('');
        setCelular('');
        setStatus('');
        message.success('Usuário cadastrado com sucesso!');
      } catch (error) {
        message.error('Erro ao cadastrar usuário.');
      }
    } else {
      message.error('Por favor, preencha todos os campos.');
    }
  };

  const handleRemoveUser = async (userId: string) => {
    try {
      await removeUser(userId);
      message.success('Usuário removido com sucesso!');
    } catch (error) {
      message.error('Erro ao remover usuário.');
    }
  };

  return (
    <div>
      <h2>Cadastro de usuários</h2>
      <Form layout="vertical">
        <Form.Item label="Nome">
          <Input value={nome} onChange={(e) => setNome(e.target.value)} />
        </Form.Item>
        <Form.Item label="Sobrenome">
          <Input value={sobrenome} onChange={(e) => setSobrenome(e.target.value)} />
        </Form.Item>
        <Form.Item label="E-mail">
          <Input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </Form.Item>
        <Form.Item label="Senha">
          <Input.Password value={password} onChange={(e) => setPassword(e.target.value)} />
        </Form.Item>
        <Form.Item label="Celular">
          <Input value={celular} onChange={(e) => setCelular(e.target.value)} />
        </Form.Item>
        <Form.Item label="Status">
          <Select value={status} onChange={(value) => setStatus(value)}>
            <Option value="sim">Ativado</Option>
            <Option value="não">Desativado</Option>
          </Select>
        </Form.Item>
        <Form.Item>
          <Button type="primary" onClick={handleAddUser}>Cadastrar</Button>
        </Form.Item>
      </Form>
      <List
        dataSource={users}
        renderItem={(user: any) => (
          <List.Item>
            <List.Item.Meta
              title={`${user.nome} ${user.sobrenome}`}
              description={user.email}
            />
            <Button type="primary" onClick={() => handleRemoveUser(user.id)}>Remover</Button>
          </List.Item>
        )}
      />
    </div>
  );
};

export default Usuarios;
