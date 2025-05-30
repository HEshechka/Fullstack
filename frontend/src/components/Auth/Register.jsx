import React, { useState } from 'react';
import { useAuth } from '../auth/AuthContext';
import AuthForm from './AuthForm'; // Используем общий компонент формы

function Register() {
    const { register, error, isLoading } = useAuth();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    const handleRegister = async () => {
        if (password !== confirmPassword) {
            // Здесь можно установить локальную ошибку, если пароли не совпадают
            alert('Пароли не совпадают!');
            return;
        }

        // Здесь вы можете добавить валидацию для firstName, lastName и т.д.

        const userData = {
            email,
            password,
            firstName,
            lastName,
            roleId: 1, // Пример: ID роли по умолчанию для нового пользователя
            // addresses: [] // Если адреса должны быть пустыми при регистрации
        };

        const success = await register(userData);
        if (success) {
            console.log('Успешная регистрация!');
            // Можно перенаправить пользователя на страницу входа или на домашнюю страницу
            // navigate('/login'); // Если используете react-router-dom
        } else {
            console.log('Ошибка регистрации.');
        }
    };

    return (
        <div>
            <h2>Регистрация</h2>
            <form onSubmit={(e) => { e.preventDefault(); handleRegister(); }}>
                <div>
                    <label htmlFor="firstName">Имя:</label>
                    <input
                        type="text"
                        id="firstName"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="lastName">Фамилия:</label>
                    <input
                        type="text"
                        id="lastName"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        required
                    />
                </div>
                {/* Используем AuthForm для email и пароля */}
                <AuthForm
                    email={email}
                    setEmail={setEmail}
                    password={password}
                    setPassword={setPassword}
                    confirmPassword={confirmPassword}
                    setConfirmPassword={setConfirmPassword}
                    showConfirmPassword={true} // Показываем поле подтверждения пароля
                    buttonText="Зарегистрироваться"
                    onSubmit={() => handleRegister()} // AuthForm вызывает onSubmit
                    error={error}
                    isSubmitting={isLoading}
                />
            </form>
        </div>
    );
}

export default Register;