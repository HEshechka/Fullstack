import React, { useState } from 'react';
import AuthForm from '../AuthForm/AuthForm';
import './Register.scss';

function RegisterForm({ onRegister }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [error, setError] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = async () => {
        if (password !== confirmPassword) {
            setError('Пароли не совпадают');
            return;
        }

        setIsSubmitting(true);
        setError('');

        try {
            await onRegister({ email, phoneNumber, password, firstName, lastName });
        } catch (err) {
            setError(err.response?.data?.message || 'Ошибка регистрации');
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="register-form-container">
            <h2 className="text-center mb-4">Регистрация</h2>
            <p className="text-center text-muted mb-4">Создайте новый аккаунт</p>

            <AuthForm
                email={email}
                setEmail={setEmail}
                password={password}
                setPassword={setPassword}
                confirmPassword={confirmPassword}
                setConfirmPassword={setConfirmPassword}
                firstName={firstName}
                setFirstName={setFirstName}
                lastName={lastName}
                setLastName={setLastName}
                phoneNumber={phoneNumber}
                setPhoneNumber={setPhoneNumber}
                showPhoneNumber={true}
                showConfirmPassword={true}
                showNameFields={true}
                buttonText="Зарегистрироваться"
                onSubmit={handleSubmit}
                error={error}
                isSubmitting={isSubmitting}
                autoComplete={{ password: 'new-password' }}
            />
        </div>
    );
}

export default RegisterForm;
