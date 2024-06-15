import React, { createContext, useState, useEffect } from 'react';
import api from '../Controller/Api';

export const UserContext = createContext<any>(null);

interface UserProviderProps {
  children: React.ReactNode;
}

interface User {
  id: string;
  nome: string;
  sobrenome: string;
  email: string;
  senha: string;
  celular: string;
  status: string;
}

export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {
  const [users, setUsers] = useState<User[]>([]);
  const [loggedInUser, setLoggedInUser] = useState<User | null>(null);

  const fetchUsers = async () => {
    try {
      const response = await api.get('/usuario');
      setUsers(response.data);
    } catch (error) {
      console.error('Erro ao carregar usuários:', error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const addUser = async (user: Omit<User, 'id'>) => {
    try {
      const response = await api.post('/usuario/create-usuario', user);
      setUsers([...users, response.data]);
    } catch (error) {
      console.error('Erro ao adicionar usuário:', error);
      throw error;
    }
  };

  const removeUser = async (id: string) => {
    try {
      await api.delete(`/usuario/delete-usuario/${id}`);
      setUsers(users.filter((user) => user.id !== id));
    } catch (error) {
      console.error('Erro ao remover usuário:', error);
      throw error;
    }
  };

  const login = async (email: string, senha: string) => {
    try {
      const response = await api.get(`/usuario/login?email=${email}&senha=${senha}`);
      setLoggedInUser(response.data);
      return response.data;
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      throw error;
    }
  };

  return (
    <UserContext.Provider value={{ users, loggedInUser, addUser, removeUser, login }}>
      {children}
    </UserContext.Provider>
  );
};
