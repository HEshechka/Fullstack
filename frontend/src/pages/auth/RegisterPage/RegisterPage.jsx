import React from 'react';
import { useAuth } from '../../../contexts/auth/AuthContext';
import { useNavigate } from 'react-router-dom';
import RegisterForm from '../../../components/Auth/Register/RegisterForm';
import './RegisterPage.scss';

const RegisterPage = () => {
    const { register } = useAuth();
    const navigate = useNavigate();

    const handleRegister = async (formData) => {
        await register(formData);
        navigate('/login');
    };

    return (
        <div className="register-page">
            <div className="auth-card">
                <RegisterForm onRegister={handleRegister} />
            </div>
        </div>
    );
};

export default RegisterPage;