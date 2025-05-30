import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/users'; // Замените на URL вашего бэкенда

export const loginUser = async (email, password) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/login`, { // Уточните URL вашего эндпоинта
            email,
            password,
        });
        return response.data; // Предполагаем, что бэкенд возвращает токен или данные пользователя
    } catch (error) {
        console.error('Login error:', error.response?.data || error.message);
        throw error;
    }
};

export const registerUser = async (userData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/register`, userData); // Уточните URL вашего эндпоинта
        return response.data; // Предполагаем, что бэкенд возвращает данные о зарегистрированном пользователе
    } catch (error) {
        console.error('Registration error:', error.response?.data || error.message);
        throw error;
    }
};

// Возможно, потребуется функция для проверки токена или получения информации о текущем пользователе
export const getCurrentUser = async (token) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/users/me`, { // Пример эндпоинта
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.data;
    } catch (error) {
        console.error('Get current user error:', error.response?.data || error.message);
        throw error;
    }
};