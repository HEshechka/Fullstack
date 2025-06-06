import React from "react";
import ProductCreateForm from "../../../components/Product/ProductCreateForm/ProductCreateForm.jsx";
import ProductDeleteForm from "../../../components/Product/ProductDeleteForm/ProductDeleteForm.jsx";
import ProductUpdateForm from "../../../components/Product/ProductUpdateForm/ProductUpdateForm.jsx";
import '../forms.scss'

const AdminProduct = () => {
    return (
        <div className="forms">
            <ProductUpdateForm />
            <ProductCreateForm/>
            <ProductDeleteForm/>
        </div>
    )
}

export default AdminProduct;