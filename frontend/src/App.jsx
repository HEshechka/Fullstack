import React from 'react';
import {Navigate, Route, Routes} from 'react-router-dom';
import {useAuth} from './contexts/auth/AuthContext';
import LoginPage from './pages/auth/LoginPage/LoginPage.jsx';
import RegisterPage from './pages/auth/RegisterPage/RegisterPage.jsx';
import Header from './pages/layout/Header/Header.jsx';
import '../css/bootstrap.min.css'
import Navigation from "./pages/layout/Navigation/Navigation.jsx";
import HomePage from "./pages/HomePage.jsx";
import ProductPage from "./pages/ProductPage/ProductPage.jsx";
import AdminPage from "./pages/Admin/Main/AdminPage.jsx";
import AdminProduct from "./pages/Admin/Product/AdminProduct.jsx";
import AdminUsers from "./pages/Admin/Users/AdminUsers.jsx";
import AdminReport from "./pages/Admin/Report/AdminReport.jsx";

const AdminRoute = ({children}) => {
    const {user, isLoading} = useAuth();

    if (isLoading) return <div>Загрузка...</div>;

    return user && user.role?.roleName === 'ADMIN'
        ? children
        : <Navigate to="/login" replace/>;
};


function App() {
    return (
        <div className="app">
            <Header/> {/* Ваш компонент Header */}
            <Navigation/>

            <main style={{minHeight: '60vh', padding: '20px'}}>
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>
                    <Route path="/product/:id" element={<ProductPage/>}/>

                    {/* Админский маршрут */}
                    <Route
                        path="/admin"
                        element={
                            <AdminRoute>
                                <AdminPage/>
                            </AdminRoute>
                        }
                    />

                    <Route
                        path="/admin-products"
                        element={
                            <AdminRoute>
                                <AdminProduct/>
                            </AdminRoute>
                        }
                    />

                    <Route
                        path="/admin-reports"
                        element={
                            <AdminRoute>
                                <AdminReport />
                            </AdminRoute>
                        }
                    />

                    <Route
                        path="/admin-users"
                        element={
                            <AdminRoute>
                                <AdminUsers />
                            </AdminRoute>
                        }
                    />

                    <Route path="*" element={<h2>404 - Страница не найдена</h2>}/>
                </Routes>
            </main>
            {/*<Footer /> /!* Ваш компонент Footer *!/*/}
        </div>
    );
}

export default App;