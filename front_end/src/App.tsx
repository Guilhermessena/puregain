import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import HomeScreen from './View/Screens/Home';
import { LoginScreen } from './View/Screens/Login';
import Pedidos from './View/Components/Pedidos';
import Produtos from './View/Components/Produtos';
import Estoque from './View/Components/Estoque'; // Removed curly braces
import Usuarios from './View/Components/Usuarios';
import { UserProvider } from './Controller/UserContext';
import { ProductsProvider } from './Controller/ProductContext';

const App: React.FC = () => {
  return (
    <UserProvider>
      <ProductsProvider>
        <Router>
          <Routes>
            <Route path="/login" element={<LoginScreen />} />
            <Route path="/home" element={<HomeScreen />}>
              <Route path="pedidos" element={<Pedidos />} />
              <Route path="produtos" element={<Produtos />} />
              <Route path="estoque" element={<Estoque />} />
              <Route path="usuarios" element={<Usuarios />} />
            </Route>
            <Route path="/" element={<Navigate to="/login" />} />
          </Routes>
        </Router>
      </ProductsProvider>
    </UserProvider>
  );
};

export default App;
