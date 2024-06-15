import React from 'react';
import { Layout, theme } from 'antd';
import { Outlet, useLocation } from 'react-router-dom';
import NavBar from '../Components/NavBar';

const { Header, Content, Footer } = Layout;

const pageTitles: { [key: string]: string } = {
  '/home/pedidos': 'Pedidos',
  '/home/produtos': 'Produtos',
  '/home/estoque': 'Estoque',
  '/home/usuarios': 'Usuários',
};

const HomeScreen: React.FC = () => {
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const location = useLocation();
  const title = pageTitles[location.pathname] || 'Página';

  return (
    <Layout>
      <NavBar />
      <Layout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <div style={{ marginLeft: '15px', fontSize: '24px', fontWeight: 'bold' }}>{title}</div>
        </Header>
        
        <Content style={{ margin: '24px 16px 0' }}>
          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
              borderRadius: borderRadiusLG,
            }}
          >
            <Outlet />
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>
          © {new Date().getFullYear()} Developed by Pure Gain - FIAP
        </Footer>
      </Layout>
    </Layout>
  );
};

export default HomeScreen;
