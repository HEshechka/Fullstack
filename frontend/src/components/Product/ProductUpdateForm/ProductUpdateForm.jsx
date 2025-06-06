import React, {useState} from "react";
import '../ProductForm.scss';
import axios  from "axios";
import {useAuth} from "../../../contexts/auth/AuthContext.jsx"; // Подключаем стили отдельно

const API_BASE_URL = 'http://localhost:8091/api';

const ProductUpdateForm = () => {
    const [productId, setProductId] = useState("")
    const [productName, setProductName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [stockQuantity, setStockQuantity] = useState("");
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const {token} = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setSuccess(null);

        if (!productName || !price || !stockQuantity || !productId) {
            setError("Пожалуйста, заполните все обязательные поля.");
            return;
        }

        const productData = {
            productId,
            productName,
            description,
            price: parseFloat(price),
            stockQuantity: parseInt(stockQuantity),
        };

        try {
            const response = await axios.put(
                `${API_BASE_URL}/products/${productId}`,
                productData,
                {
                    headers: {Authorization: `Bearer ${token}`},
                });

            const result = await response.data;
            setSuccess(`Продукт "${result.productName}"  с  ID ${result.id} успешно обновлен`);
            setProductId(result.id);
            setProductName("");
            setDescription("");
            setPrice("");
            setStockQuantity("");
        } catch (err) {
            setError("Сервер недоступен или произошла ошибка.");
        }
    };

    return (
        <form className="product-form fade-in" onSubmit={handleSubmit}>
            <h2>Обновить продукт</h2>

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

            <div className="input-group">
                <label>Название продукта:</label>
                <input
                    type="text"
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                    required
                />
            </div>

            <div className="input-group">
                <label>Описание:</label>
                <textarea
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    rows={3}
                />
            </div>

            <div className="input-group">
                <label>Цена:</label>
                <input
                    type="number"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                    step="0.01"
                    min="0"
                    required
                />
            </div>

            <div className="input-group">
                <label>Количество на складе:</label>
                <input
                    type="number"
                    value={stockQuantity}
                    onChange={(e) => setStockQuantity(e.target.value)}
                    min="0"
                    required
                />
            </div>

            <button className="submit-button" type="submit" disabled={!productName || !price || !stockQuantity} >Обновить продукт</button>
        </form>
    );
};

export default ProductUpdateForm;
