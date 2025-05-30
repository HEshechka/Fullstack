import React, { useState } from 'react';
import { useAuth } from '../auth/AuthContext';
import AuthForm from './AuthForm'; // Используем общий компонент формы

function Login() {
    const { login, error, isLoading } = useAuth();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        const success = await login(email, password);
        if (success) {
            // Можно перенаправить пользователя, например, на домашнюю страницу
            console.log('Успешный вход!');
            // navigate('/'); // Если используете react-router-dom
        } else {
            console.log('Ошибка входа.');
        }
    };

    return (
        <div>
            <h2>Вход</h2>
            <AuthForm
                email={email}
                setEmail={setEmail}
                password={password}
                setPassword={setPassword}
                buttonText="Войти"
                onSubmit={handleLogin}
                error={error}
                isSubmitting={isLoading}
            />
        </div>
    );
}

export default Login;