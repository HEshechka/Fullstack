import React, {useState} from "react";
import '../ProductForm.scss';
import axios  from "axios";
import {useAuth} from "../../../contexts/auth/AuthContext.jsx"; // Подключаем стили отдельно

const API_BASE_URL = 'http://localhost:8091/api';

const ProductDeleteForm = () => {
    const [productId, setProductId] = useState("")
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const {token} = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setSuccess(null);

        try {
            const response = await axios.delete(
                `${API_BASE_URL}/products/${productId}`,
                {
                    headers: {Authorization: `Bearer ${token}`},
                });

            const result = await response.data;
            console.log(result);
            setSuccess(`Продукт с ID ${result} успешно удалён.`);
        } catch (err) {
            setError("Сервер недоступен или произошла ошибка.");
        }
    };

    return (
        <form className="product-form fade-in" onSubmit={handleSubmit}>
            <h2>Удалить продукт</h2>

            {error && <div className="error-message">{error}</div>}
            {success && <div className="success-message">{success}</div>}

            <div className="input-group">
                <label>ID Продукта*:</label>
                <input
                    type="text"
                    value={productId}
                    onChange={(e) => setProductId(e.target.value)}
                    required
                />
            </div>

            <button className="submit-button" type="submit" disabled={!productId} >Удалить продукт</button>
        </form>
    );
};

export default ProductDeleteForm;
