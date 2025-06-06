import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";
import './product-page.scss';

const API_BASE_URL = 'http://localhost:8091/api';

const ProductPage = () => {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('authToken');

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const response = await axios.get(`${API_BASE_URL}/products/${id}`, {
                    headers: { Authorization: `Bearer ${token}` }
                });
                setProduct(response.data);
            } catch (err) {
                setError('Не удалось загрузить данные продукта');
            }
        };
        fetchProduct().catch((err) => {console.log(`err` + err)});
    }, [id, token]);

    if (error) return <p>{error}</p>;
    if (!product) return <p>Загрузка...</p>;

    return (
        <div className="product-detail-container">
            <Link to="/" className="back-link">← Вернуться к списку продуктов</Link>
            <h1>{product.productName}</h1>
            <p><strong>Описание:</strong> {product.description}</p>
            <p><strong>Цена:</strong> {product.price} ₽</p>
            <p><strong>Количество в наличии:</strong> {product.stockQuantity}</p>
            <p><strong>Дата добавления:</strong> {new Date(product.createdAt).toLocaleDateString()}</p>
            {/* Добавь сюда другие поля, если нужно */}
        </div>
    );
};

export default ProductPage;
