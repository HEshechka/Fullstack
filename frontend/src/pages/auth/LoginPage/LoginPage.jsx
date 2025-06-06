import React from 'react';
import { useAuth } from '../../../contexts/auth/AuthContext';
import { useNavigate } from 'react-router-dom';
import LoginForm from '../../../components/Auth/Login/LoginForm.jsx';
import './login.scss';

const LoginPage = () => {
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleLogin = async (formData) => {
        await login(formData.email, formData.password);
        navigate('/');
    };

    return (
        <div className="login-page">
            <div className="auth-card">
                <LoginForm onLogin={handleLogin} />
            </div>
        </div>
    );
};

export default LoginPage;