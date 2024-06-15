import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Ajuste a URL conforme necessário
});

export default api;
