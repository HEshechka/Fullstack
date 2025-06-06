import React, { useState } from 'react';

const ProductsList = ({ products }) => {
    const itemsPerPage = 6; // 6 штук (по 3 в строке - 2 ряда)
    const [currentPage, setCurrentPage] = useState(1);

    const totalPages = Math.ceil(products.length / itemsPerPage);

    const startIndex = (currentPage - 1) * itemsPerPage;
    const currentProducts = products.slice(startIndex, startIndex + itemsPerPage);

    const goToPage = (page) => {
        if (page >= 1 && page <= totalPages) {
            setCurrentPage(page);
        }
    };

    return (
        <div className="product-container">
            <h1>Список продуктов</h1>
            {products.length === 0 ? (
                <p>Нет доступных продуктов.</p>
            ) : (
                <>
                    <ul className="product-list">
                        {currentProducts.map(product => (
                            <li key={product.id} className="product-item">
                                <h3>{product.productName}</h3>
                                <p>{product.description}</p>
                                <p><strong>Цена:</strong> {product.price} ₽</p>
                                <p><strong>Количество:</strong> {product.stockQuantity}</p>
                            </li>
                        ))}
                    </ul>

                    <div className="pagination">
                        <button onClick={() => goToPage(currentPage - 1)} disabled={currentPage === 1}>
                            Назад
                        </button>

                        {[...Array(totalPages)].map((_, i) => (
                            <button
                                key={i}
                                onClick={() => goToPage(i + 1)}
                                disabled={currentPage === i + 1}
                            >
                                {i + 1}
                            </button>
                        ))}

                        <button onClick={() => goToPage(currentPage + 1)} disabled={currentPage === totalPages}>
                            Вперед
                        </button>
                    </div>
                </>
            )}
        </div>
    );
};

export default ProductsList;
