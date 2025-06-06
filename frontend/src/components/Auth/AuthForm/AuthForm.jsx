import React from 'react';
import './AuthForm.scss';

function AuthForm({
                      email,
                      setEmail,
                      password,
                      setPassword,
                      confirmPassword,
                      setConfirmPassword,
                      firstName,
                      setFirstName,
                      lastName,
                      setLastName,
                      phoneNumber,
                      setPhoneNumber,
                      showConfirmPassword = false,
                      showNameFields = false,
                      showPhoneNumber = false,
                      buttonText,
                      onSubmit,
                      error,
                      isSubmitting,
                      autoComplete = {}
                  }) {
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({ email, phoneNumber, password, confirmPassword, firstName, lastName });
    };


    return (
        <form onSubmit={handleSubmit} className="auth-form">
            {error && (
                <div className="alert alert-danger fade-in" role="alert">
                    {error}
                </div>
            )}

            {showNameFields && (
                <div className="name-fields row g-3 mb-3">
                    <div className="col-md-6">
                        <label htmlFor="firstName" className="form-label">Имя</label>
                        <input
                            type="text"
                            className="form-control"
                            id="firstName"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            required
                            disabled={isSubmitting}
                            autoComplete="given-name"
                        />
                    </div>
                    <div className="col-md-6">
                        <label htmlFor="lastName" className="form-label">Фамилия</label>
                        <input
                            type="text"
                            className="form-control"
                            id="lastName"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            required
                            disabled={isSubmitting}
                            autoComplete="family-name"
                        />
                    </div>
                </div>
            )}

            {showPhoneNumber && (
                <div className="mb-3">
                    <label htmlFor="phoneNumber" className="form-label">Номер телефона</label>
                    <input
                        type="tel"
                        className="form-control"
                        id="phoneNumber"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        required
                        disabled={isSubmitting}
                        autoComplete="tel"
                    />
                </div>
            )}

            <div className="mb-3">
                <label htmlFor="email" className="form-label">Email</label>
                <input
                    type="email"
                    className="form-control"
                    id="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                    disabled={isSubmitting}
                    autoComplete="email"
                />
            </div>

            <div className="mb-3">
                <label htmlFor="password" className="form-label">Пароль</label>
                <input
                    type="password"
                    className="form-control"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    disabled={isSubmitting}
                    autoComplete={autoComplete.password || "current-password"}
                />
            </div>

            {showConfirmPassword && (
                <div className="mb-4">
                    <label htmlFor="confirmPassword" className="form-label">Подтвердите пароль</label>
                    <input
                        type="password"
                        className="form-control"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                        disabled={isSubmitting}
                        autoComplete="new-password"
                    />
                </div>
            )}

            <button
                type="submit"
                className="btn btn-primary w-100 submit-button"
                disabled={isSubmitting}
            >
                {isSubmitting ? (
                    <>
                        <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                        {buttonText}
                    </>
                ) : buttonText}
            </button>
        </form>
    );
}

export default AuthForm;