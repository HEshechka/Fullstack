import React from 'react';
import './header.scss';
// Импортируем изображения (замените на свои)
import logo from '../../../favicon.svg';

const Header = () => {
    return (
        <header className="main-header">
            <div className="container">
                <div className="d-flex justify-content-between align-items-center py-3">
                    {/* Логотип и название */}
                    <div className="d-flex align-items-center">
                        <img src={logo} alt="CubeBoy Logo" className="header-logo me-3" />
                        <h1 className="header-title m-0"><a href="http://localhost:5173/" className="header__brand--link">CubeBoy</a></h1>
                    </div>

                    {/* Контакты */}
                    <div className="header-contacts">
                        <a href="https://wa.me/79010000000" className="contact-link">
                            <i className="bi bi-whatsapp contact-icon"></i>
                            <span>+7 (901) 000-00-00</span>
                        </a>

                        <a href="https://t.me/username" className="contact-link ms-4">
                            <i className="bi bi-telegram contact-icon"></i>
                            <span>@username</span>
                        </a>

                        <a href="https://vk.com/id123123123" className="contact-link ms-4">
                            <i className="bi bi-vk contact-icon"></i>
                            <span>vk.com/cubeboy</span>
                        </a>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;