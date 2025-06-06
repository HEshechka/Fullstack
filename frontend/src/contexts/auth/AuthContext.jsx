import React, { createContext, useState, useEffect, useContext } from 'react';
import { loginUser, registerUser, getCurrentUser } from '../../api/auth.js'; // Импортируем функции API

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem('authToken')); // Храним токен в localStorage
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    // При загрузке приложения пытаемся получить данные пользователя, если есть токен
    useEffect(() => {
        const loadUser = async () => {
            if (token) {
                try {
                    // Проверяем токен и получаем данные пользователя с бэкенда
                    const currentUser = await getCurrentUser(token);
                    setUser(currentUser);
                    setError(null);
                } catch (err) {
                    console.error("Failed to load user from token:", err);
                    logout(); // Если токен недействителен, выходим из системы
                }
            }
            setIsLoading(false);
        };

        loadUser();
    }, [token]);

    const login = async (email, password) => {
        setIsLoading(true);
        setError(null);
        try {
            const data = await loginUser(email, password);
            // Предполагаем, что бэкенд возвращает JWT токен в поле 'token'
            const receivedToken = data.token;
            const userData = data.user; // Если бэкенд возвращает также данные пользователя

            if (receivedToken) {
                setToken(receivedToken);
                localStorage.setItem('authToken', receivedToken); // Сохраняем токен
                setUser(userData || { email: email }); // Устанавливаем пользователя (можно расширить)
                return true;
            } else {
                throw new Error('Токен не был получен от сервера.');
            }
        } catch (err) {
            setError(err.response?.data?.message || 'Ошибка входа. Проверьте учетные данные.');
            setUser(null);
            setToken(null);
            localStorage.removeItem('authToken');
            return false;
        } finally {
            setIsLoading(false);
        }
    };

    const register = async (userData) => {
        setIsLoading(true);
        setError(null);
        try {
            const response = await registerUser(userData);
            // После регистрации — сразу логинимся:
            const loginResult = await login(userData.email, userData.password);
            return loginResult;
        } catch (err) {
            setError(err.response?.data?.message || 'Ошибка регистрации. Попробуйте еще раз.');
            return false;
        } finally {
            setIsLoading(false);
        }
    };


    const logout = () => {
        setUser(null);
        setToken(null);
        localStorage.removeItem('authToken'); // Удаляем токен
        setError(null);
    };

    const value = {
        user,
        token,
        isLoading,
        error,
        login,
        register,
        logout,
        isAuthenticated: !!user && !!token, // Простой флаг аутентификации
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

// Хук для удобного использования контекста
export const useAuth = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};