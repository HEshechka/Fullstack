import {Link} from "react-router-dom";
import React from "react";
import {useAuth} from "../../../contexts/auth/AuthContext.jsx";
import "./navigation.scss";

const Navigation = () => {
    const {isAuthenticated, logout, user} = useAuth();

    return (
        <nav className="main-navigation navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <Link className="navbar-brand hover-effect" to="/">Главная</Link>

                <button
                    className="navbar-toggler hover-effect"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav me-auto">
                        {!isAuthenticated && (
                            <li className="nav-item">
                                <Link className="nav-link hover-effect" to="/login">Вход</Link>
                            </li>
                        )}
                        {!isAuthenticated && (
                            <li className="nav-item">
                                <Link className="nav-link hover-effect" to="/register">Регистрация</Link>
                            </li>
                        )}
                        {isAuthenticated && user.role.roleName === `ADMIN` && (
                            <Link className="nav-link hover-effect" to="/admin">Админ панель</Link>
                        )}
                    </ul>

                    <div className="d-flex align-items-center">
                        {isAuthenticated && (
                            <span className="navbar-text me-3 user-greeting">
                                Привет, {user?.firstName || user?.email}!
                            </span>
                        )}
                        {isAuthenticated && (
                            <button
                                className="btn btn-outline-danger logout-btn"
                                onClick={logout}
                            >
                                Выйти
                            </button>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Navigation;