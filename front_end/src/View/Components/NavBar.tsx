import React from 'react';
import { TruckOutlined, UserOutlined, FolderAddOutlined, ShoppingCartOutlined } from '@ant-design/icons';
import { Layout, Menu, Button } from 'antd';
import { Link } from 'react-router-dom';
import logo from '../../Assets/Puregain.png';

const { Sider } = Layout;

const NavBar: React.FC = () => {
  return (
    <Sider
      breakpoint="lg"
      collapsedWidth="0"
      onBreakpoint={(broken) => {
        console.log(broken);
      }}
      onCollapse={(collapsed, type) => {
        console.log(collapsed, type);
      }}
    >
      <div className="logo" style={{ textAlign: 'center', marginBottom: '20px', marginTop: '30px' }}>
        <img src={logo} alt="Logo" style={{ height: '150px', width: '150px', objectFit: 'contain', borderRadius: '30px', margin: 'auto' }} />
      </div>
      <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
        <Menu.Item key="1" icon={<ShoppingCartOutlined />}>
          <Link to="/home/pedidos">Pedidos</Link>
        </Menu.Item>
        <Menu.Item key="2" icon={<FolderAddOutlined />}>
          <Link to="/home/produtos">Produtos</Link>
        </Menu.Item>
        <Menu.Item key="3" icon={<TruckOutlined />}>
          <Link to="/home/estoque">Estoque</Link>
        </Menu.Item>
        <Menu.Item key="4" icon={<UserOutlined />}>
          <Link to="/home/usuarios">Usuários</Link>
        </Menu.Item>
        {/* Adicionando o botão de logout */}
        <Menu.Item key="5">
          <Button type="primary" danger>
            <Link to="/login">Logout</Link>
          </Button>
        </Menu.Item>
      </Menu>
    </Sider>
  );
};

export default NavBar;
