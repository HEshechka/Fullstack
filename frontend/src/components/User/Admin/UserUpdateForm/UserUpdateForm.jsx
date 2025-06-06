import React, {useState} from "react";
import axios from "axios";
import {useAuth} from "../../../../contexts/auth/AuthContext.jsx"; // Подключаем стили отдельно

const API_BASE_URL = 'http://localhost:8091/api/users';

const UserUpdateForm = () => {
    const [id, setId] = useState("");
    const [roleId, setRoleId] = useState("");
    const [email, setEmail] = useState(null);
    const [firstName, setFirstName] = useState(null);
    const [lastName, setLastName] = useState(null);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const {token} = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setSuccess(null);

        if (!id) {
            setError("Пожалуйста, заполните все обязательные поля.");
            return;
        }

        const userData = {
            id: parseInt(id),
            firstName: firstName,
            lastName: lastName,
            email: email,
            roleId: parseInt(roleId),
        };

        try {
            const response = await axios.put(
                `${API_BASE_URL}/${id}`,
                userData,
                {
                    headers: {Authorization: `Bearer ${token}`},
                });

            const result = await response.data;
            setSuccess(`Пользователь "${result.lastName} ${result.firstName}"  с  ID ${result.id} успешно обновлен`);
            setId("");
            setFirstName(null);
            setLastName(null);
            setEmail(null);
            setRoleId("");
        } catch (err) {
            setError("Сервер недоступен или произошла ошибка.");
        }
    };

    return (
        <form className="product-form fade-in" onSubmit={handleSubmit}>
            <h2>Обновить пользователя</h2>

            {error && <div className="error-message">{error}</div>}
            {success && <div className="success-message">{success}</div>}

            <div className="input-group">
                <label>ID Пользователя*:</label>
                <input
                    type="number"
                    min="1"
                    value={id}
                    onChange={(e) => setId(e.target.value)}
                    required
                />
            </div>

            <div className="input-group">
                <label>Имя:</label>
                <input
                    type="text"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                />
            </div>

            <div className="input-group">
                <label>Фамилия:</label>
                <input
                    type="text"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                />
            </div>

            <div className="input-group">
                <label>Email:</label>
                <input
                    type="text"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>

            <div className="input-group">
                <label>ID роли:</label>
                <input
                    type="number"
                    value={roleId}
                    onChange={(e) => setRoleId(e.target.value)}
                    min="1"
                />
            </div>

            <button className="submit-button" type="submit"
                    disabled={!id} > Обновить пользователя</button>
                        </form>
                        );
                    };

export default UserUpdateForm;
