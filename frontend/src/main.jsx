import React from 'react';
import ReactDOM from 'react-dom/client';
import App from '../src/App.jsx';
import { AuthProvider } from './contexts/auth/AuthContext.jsx'; // Импортируем провайдер
import { BrowserRouter as Router } from 'react-router-dom'; // Импортируем Router

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <Router>
            <AuthProvider> {/* Оборачиваем все приложение в провайдер аутентификации */}
                <App />
            </AuthProvider>
        </Router>
    </React.StrictMode>,
);