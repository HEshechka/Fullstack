import React from "react";

import '../forms.scss'
import UserUpdateForm from "../../../components/User/Admin/UserUpdateForm/UserUpdateForm.jsx";

const AdminUsers = () => {
    return (
        <div className="forms">
            <UserUpdateForm />
        </div>
    )
}

export default AdminUsers;