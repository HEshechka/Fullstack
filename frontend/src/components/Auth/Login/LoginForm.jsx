import React, {useState} from 'react';
import AuthForm from '../AuthForm/AuthForm';
import './login.scss';

function LoginForm({ onLogin }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = async () => {
        try {
            await onLogin({ email, password });
        } catch (err) {
            setError(err.response?.data?.message || 'Ошибка авторизации');
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="login-form-container">
            <h2 className="text-center mb-4">Вход в систему</h2>
            <p className="text-center text-muted mb-4">Пожалуйста, введите ваши данные</p>

            <AuthForm
                email={email}
                setEmail={setEmail}
                password={password}
                setPassword={setPassword}
                buttonText="Войти"
                onSubmit={handleSubmit}
                error={error}
                isSubmitting={isSubmitting}
                autoComplete={{ password: 'сurrent-password' }}
            />

            <div className="login-links mt-4 text-center">
                <a href="/forgot-password" className="d-block mb-2">Забыли пароль?</a>
                <a href="/register">Ещё нет аккаунта? Зарегистрироваться</a>
            </div>
        </div>
    );
}

export default LoginForm;