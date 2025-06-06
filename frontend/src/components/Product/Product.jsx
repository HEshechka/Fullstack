import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";  // <-- импортируем useNavigate
import './product.scss';

const API_BASE_URL = 'http://localhost:8091/api';

const Product = () => {
    const [products, setProducts] = useState([]);
    const navigate = useNavigate(); // <-- инициализируем хук навигации

    const token = localStorage.getItem('authToken');

    const fetchProducts = async () => {
        try {
            const response = await axios.get(`${API_BASE_URL}/products`, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setProducts(response.data);
        } catch (error) {
            console.error("Ошибка при получении продуктов:", error);
        }
    };

    useEffect(() => {
        fetchProducts().catch(error => console.error(error));
    }, []);

    // Функция обработки клика по продукту
    const handleProductClick = (id) => {
        navigate(`/product/${id}`); // Перейти на страницу с детальным описанием
    };

    return (
        <div className="product-container">
            <h1>Список продуктов</h1>
            {products.length === 0 ? (
                <p>Нет доступных продуктов.</p>
            ) : (
                <ul className="product-list">
                    {products.map(product => (
                        <li
                            key={product.productId}
                            className="product-item"
                            onClick={() => handleProductClick(product.productId)}
                            style={{ cursor: 'pointer' }} // Чтобы было видно, что элемент кликабельный
                        >
                            <h3>{product.productName}</h3>
                            <p>{product.description}</p>
                            <p><strong>Цена:</strong> {product.price} ₽</p>
                            <p><strong>Количество:</strong> {product.stockQuantity}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default Product;
