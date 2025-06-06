import React from "react";
import { Link } from "react-router-dom";
import './admin.scss';
import { useAuth } from "../../../contexts/auth/AuthContext.jsx";

const AdminPage = () => {
    const {isAuthenticated, user} = useAuth();

    if (!isAuthenticated) return <p>Вы не авторизованы</p>;
    if (user) {}
    if (user.role.roleName !== 'ADMIN') return <p>У вас нет доступа к этой странице</p>;

    return (
        <div className="admin-page">
            <h1>Панель администратора</h1>
            <nav className="admin__nav">
                <ul className="admin__list">
                    <li className="admin__list--item">
                        <Link to="/admin-products">Товары</Link>
                    </li>
                    <li className="admin__list--item">
                        <Link to="/admin-reports">Отчёты</Link>
                    </li>
                    <li className="admin__list--item">
                        <Link to="/admin-users">Пользователи</Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
};

export default AdminPage;
