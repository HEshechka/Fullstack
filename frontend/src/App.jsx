import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import { useAuth } from './auth/AuthContext';
import Header from './components/Header/Header'; // Ваш компонент Header
import Footer from './components/Footer/Footer'; // Ваш компонент Footer
import HomePage from './pages/HomePage/HomePage'; // Ваша домашняя страница
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import ProductPage from './pages/ProductPage/ProductPage'; // Пример страницы продукта

// Приватный маршрут для защищенных страниц
const PrivateRoute = ({ children }) => {
    const { isAuthenticated, isLoading } = useAuth();

    if (isLoading) {
        return <div>Загрузка...</div>; // Или красивый спиннер
    }

    return isAuthenticated ? children : <Navigate to="/login" replace />;
};

function App() {
    const { isAuthenticated, logout, user } = useAuth();

    return (
        <div className="app">
            <Header /> {/* Ваш компонент Header */}
            <nav style={{ padding: '10px 20px', background: '#f0f0f0' }}>
                <Link to="/" style={{ marginRight: '15px' }}>Главная</Link>
                {!isAuthenticated && <Link to="/login" style={{ marginRight: '15px' }}>Вход</Link>}
                {!isAuthenticated && <Link to="/register" style={{ marginRight: '15px' }}>Регистрация</Link>}
                {isAuthenticated && <Link to="/dashboard" style={{ marginRight: '15px' }}>Панель</Link>}
                {isAuthenticated && (
                    <span style={{ marginRight: '15px' }}>
            Привет, {user?.firstName || user?.email}!
          </span>
                )}
                {isAuthenticated && <button onClick={logout}>Выйти</button>}
            </nav>

            <main style={{ minHeight: '60vh', padding: '20px' }}>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/register" element={<RegisterPage />} />
                    <Route path="/product/:id" element={<ProductPage />} /> {/* Пример маршрута продукта */}
                    {/* Защищенный маршрут */}
                    <Route
                        path="/dashboard"
                        element={
                            <PrivateRoute>
                                <h2>Панель управления (только для авторизованных)</h2>
                                <p>Здесь может быть информация о профиле пользователя или заказы.</p>
                            </PrivateRoute>
                        }
                    />
                    <Route path="*" element={<h2>404 - Страница не найдена</h2>} />
                </Routes>
            </main>
            <Footer /> {/* Ваш компонент Footer */}
        </div>
    );
}

export default App;