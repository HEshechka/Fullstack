import React, { useState } from "react";
import axios from "axios";
import './ReportForm.scss';
import { useAuth } from "../../../contexts/auth/AuthContext.jsx";

const API_BASE_URL = "http://localhost:8091/api/reports";

const ReportForm = () => {
    const { token } = useAuth();
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [reportData, setReportData] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const [formData, setFormData] = useState({
        reportName: "",
        startDate: "",
        endDate: ""
    });

    const reportTypes = [
        { value: "SalesSummaryReport", label: "Сводный отчет о продажах" },
        { value: "ItemWiseSalesReport", label: "Отчет по продажам товаров" },
        { value: "OrderStatusReport", label: "Отчет по статусам заказов" },
        { value: "UserRegistrationReport", label: "Отчет по регистрациям пользователей" }
    ];

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setSuccess(null);
        setReportData(null);
        setIsLoading(true);

        try {
            const payload = formData.reportName === "OrderStatusReport"
                ? { reportName: formData.reportName }
                : {
                    reportName: formData.reportName,
                    startDate: formData.startDate ? new Date(formData.startDate).toISOString() : null,
                    endDate: formData.endDate ? new Date(formData.endDate).toISOString() : null
                };

            const response = await axios.post(API_BASE_URL, payload, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            });

            setSuccess("Отчет успешно создан");
            setReportData(response.data.reportData);

            if (formData.reportName !== "OrderStatusReport") {
                setFormData(prev => ({ ...prev, startDate: "", endDate: "" }));
            }
        } catch (err) {
            console.error(err);
            setError(err.response?.data?.message || "Ошибка при создании отчета");
        } finally {
            setIsLoading(false);
        }
    };

    const formatReportData = (data) => {
        try {
            const jsonData = JSON.parse(data);
            return (
                <pre className="rf-report-data">
                    {JSON.stringify(jsonData, null, 2)}
                </pre>
            );
        } catch (e) {
            return (
                <pre className="rf-report-data">
                    {data}
                </pre>
            );
        }
    };

    return (
        <div className="rf-container">
            <h2 className="rf-header">Создать отчет</h2>

            {error && <div className="rf-message rf-error">{error}</div>}
            {success && <div className="rf-message rf-success">{success}</div>}

            <form className="rf-form" onSubmit={handleSubmit}>
                <div className="rf-form-group">
                    <label htmlFor="reportName">Тип отчета:</label>
                    <select
                        id="reportName"
                        name="reportName"
                        value={formData.reportName}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Выберите тип отчета</option>
                        {reportTypes.map(type => (
                            <option key={type.value} value={type.value}>
                                {type.label}
                            </option>
                        ))}
                    </select>
                </div>

                {formData.reportName !== "OrderStatusReport" && (
                    <>
                        <div className="rf-form-group">
                            <label htmlFor="startDate">Начальная дата:</label>
                            <input
                                type="datetime-local"
                                id="startDate"
                                name="startDate"
                                value={formData.startDate}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="rf-form-group">
                            <label htmlFor="endDate">Конечная дата:</label>
                            <input
                                type="datetime-local"
                                id="endDate"
                                name="endDate"
                                value={formData.endDate}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </>
                )}

                <button
                    className="rf-submit-btn"
                    type="submit"
                    disabled={isLoading}
                >
                    {isLoading ? "Создание..." : "Создать отчет"}
                </button>
            </form>

            {reportData && (
                <div className="rf-report-results">
                    <h3>Результаты отчета:</h3>
                    <div className="rf-report-data">
                        {formatReportData(reportData)}
                    </div>
                    <button
                        type="button"
                        className="rf-download-btn"
                        onClick={() => {
                            const blob = new Blob([reportData], { type: 'text/plain' });
                            const url = URL.createObjectURL(blob);
                            const a = document.createElement('a');
                            a.href = url;
                            a.download = `${formData.reportName}_${new Date().toISOString()}.${reportData.startsWith('{') ? 'json' : 'csv'}`;
                            a.click();
                            URL.revokeObjectURL(url);
                        }}
                    >
                        Скачать отчет
                    </button>
                </div>
            )}
        </div>
    );
};

export default ReportForm;