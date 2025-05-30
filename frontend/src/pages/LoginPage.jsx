import React from 'react';
import Login from '../components/Login';

function LoginPage() {
    return (
        <div style={{ maxWidth: '400px', margin: '50px auto', padding: '20px', border: '1px solid #ddd', borderRadius: '8px' }}>
            <Login />
        </div>
    );
}

export default LoginPage;