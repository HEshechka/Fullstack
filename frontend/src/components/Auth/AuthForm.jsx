import React from 'react';

function AuthForm({
                      email,
                      setEmail,
                      password,
                      setPassword,
                      confirmPassword,
                      setConfirmPassword,
                      showConfirmPassword = false, // Опционально для регистрации
                      buttonText,
                      onSubmit,
                      error,
                      isSubmitting,
                  }) {
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit();
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
            </div>
            <div>
                <label htmlFor="password">Пароль:</label>
                <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
            </div>
            {showConfirmPassword && (
                <div>
                    <label htmlFor="confirmPassword">Повторите пароль:</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                </div>
            )}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit" disabled={isSubmitting}>
                {isSubmitting ? 'Загрузка...' : buttonText}
            </button>
        </form>
    );
}

export default AuthForm;