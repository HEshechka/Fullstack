import React, { useEffect, useState } from "react";
import axios from "axios";
import './GetReportForm.scss';
import { useAuth } from "../../../contexts/auth/AuthContext.jsx";

const API_BASE_URL = "http://localhost:8091/api/reports";

const GetReportForm = () => {
    const { token } = useAuth();
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [reports, setReports] = useState([]);
    const [selectedReport, setSelectedReport] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [dateFilter, setDateFilter] = useState({
        startDate: "",
        endDate: ""
    });

    useEffect(() => {
        fetchReports();
    }, []);

    const fetchReports = async (startDate = null, endDate = null) => {
        setIsLoading(true);
        setError(null);
        try {
            let url = API_BASE_URL;
            if (startDate && endDate) {
                url += `?startDate=${new Date(startDate).toISOString()}&endDate=${new Date(endDate).toISOString()}`;
            }

            const response = await axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            });

            setReports(response.data);
            setSuccess("Отчеты успешно загружены");
        } catch (err) {
            console.error(err);
            setError(err.response?.data?.message || "Ошибка при загрузке отчетов");
        } finally {
            setIsLoading(false);
        }
    };

    const handleDateFilterChange = (e) => {
        const { name, value } = e.target;
        setDateFilter(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const applyDateFilter = () => {
        if (dateFilter.startDate && dateFilter.endDate) {
            fetchReports(dateFilter.startDate, dateFilter.endDate);
        } else {
            fetchReports();
        }
    };

    const clearDateFilter = () => {
        setDateFilter({ startDate: "", endDate: "" });
        fetchReports();
    };

    const formatReportType = (reportName) => {
        const types = {
            "SalesSummaryReport": "Сводка продаж",
            "ItemWiseSalesReport": "Продажи по товарам",
            "OrderStatusReport": "Статусы заказов",
            "UserRegistrationReport": "Регистрации пользователей"
        };
        return types[reportName] || reportName;
    };

    const formatReportData = (data) => {
        try {
            const jsonData = JSON.parse(data);
            return (
                <pre className="grf-report-content">
                    {JSON.stringify(jsonData, null, 2)}
                </pre>
            );
        } catch (e) {
            return (
                <pre className="grf-report-content">
                    {data}
                </pre>
            );
        }
    };

    const downloadReport = (report) => {
        const blob = new Blob([report.reportData], { type: 'text/plain' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${report.reportName}_${new Date(report.createdAt).toISOString()}.${report.reportData.startsWith('{') ? 'json' : 'csv'}`;
        a.click();
        URL.revokeObjectURL(url);
    };

    return (
        <div className="grf-container">
            <h2 className="grf-header">Просмотр отчетов</h2>

            {error && <div className="grf-message grf-error">{error}</div>}
            {success && <div className="grf-message grf-success">{success}</div>}

            <div className="grf-filters">
                <h3>Фильтры</h3>
                <div className="grf-filter-row">
                    <div className="grf-form-group">
                        <label htmlFor="startDate">Начальная дата:</label>
                        <input
                            type="datetime-local"
                            id="startDate"
                            name="startDate"
                            value={dateFilter.startDate}
                            onChange={handleDateFilterChange}
                        />
                    </div>

                    <div className="grf-form-group">
                        <label htmlFor="endDate">Конечная дата:</label>
                        <input
                            type="datetime-local"
                            id="endDate"
                            name="endDate"
                            value={dateFilter.endDate}
                            onChange={handleDateFilterChange}
                        />
                    </div>
                </div>

                <div className="grf-filter-actions">
                    <button
                        type="button"
                        className="grf-apply-btn"
                        onClick={applyDateFilter}
                        disabled={isLoading || (!dateFilter.startDate && !dateFilter.endDate)}
                    >
                        Применить фильтр
                    </button>
                    <button
                        type="button"
                        className="grf-reset-btn"
                        onClick={clearDateFilter}
                        disabled={isLoading || (!dateFilter.startDate && !dateFilter.endDate)}
                    >
                        Сбросить
                    </button>
                </div>
            </div>

            <div className="grf-reports-list">
                <div className="grf-list-header">
                    <h3>Список отчетов</h3>
                    <button
                        type="button"
                        className="grf-refresh-btn"
                        onClick={() => fetchReports(dateFilter.startDate, dateFilter.endDate)}
                        disabled={isLoading}
                    >
                        {isLoading ? "Обновление..." : "Обновить список"}
                    </button>
                </div>

                {isLoading && reports.length === 0 ? (
                    <div className="grf-message grf-loading">Загрузка отчетов...</div>
                ) : reports.length === 0 ? (
                    <div className="grf-message">Отчеты не найдены</div>
                ) : (
                    <div className="grf-table-wrapper">
                        <table className="grf-reports-table">
                            <thead>
                            <tr>
                                <th>Тип отчета</th>
                                <th>Дата создания</th>
                                <th>Период отчета</th>
                                <th>Действия</th>
                            </tr>
                            </thead>
                            <tbody>
                            {reports.map(report => (
                                <tr
                                    key={report.id}
                                    className={selectedReport?.id === report.id ? "grf-selected-row" : ""}
                                    onClick={() => setSelectedReport(report)}
                                >
                                    <td>{formatReportType(report.reportName)}</td>
                                    <td>{new Date(report.createdAt).toLocaleString()}</td>
                                    <td>
                                        {report.startDate && report.endDate
                                            ? `${new Date(report.startDate).toLocaleDateString()} - ${new Date(report.endDate).toLocaleDateString()}`
                                            : "Н/Д"}
                                    </td>
                                    <td>
                                        <button
                                            type="button"
                                            className="grf-action-btn grf-view-btn"
                                            onClick={(e) => {
                                                e.stopPropagation();
                                                setSelectedReport(report);
                                            }}
                                        >
                                            Просмотр
                                        </button>
                                        <button
                                            type="button"
                                            className="grf-action-btn grf-download-btn"
                                            onClick={(e) => {
                                                e.stopPropagation();
                                                downloadReport(report);
                                            }}
                                        >
                                            Скачать
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>

            {selectedReport && (
                <div className="grf-report-details">
                    <div className="grf-details-header">
                        <h3>Детали отчета</h3>
                        <button
                            type="button"
                            className="grf-close-btn"
                            onClick={() => setSelectedReport(null)}
                        >
                            Закрыть
                        </button>
                    </div>
                    <div className="grf-meta">
                        <p><strong>Тип отчета:</strong> {formatReportType(selectedReport.reportName)}</p>
                        <p><strong>Дата создания:</strong> {new Date(selectedReport.createdAt).toLocaleString()}</p>
                        {selectedReport.startDate && selectedReport.endDate && (
                            <p>
                                <strong>Период отчета:</strong>
                                {` ${new Date(selectedReport.startDate).toLocaleDateString()} - ${new Date(selectedReport.endDate).toLocaleDateString()}`}
                            </p>
                        )}
                    </div>
                    <div className="grf-content">
                        <h4>Содержимое отчета:</h4>
                        {formatReportData(selectedReport.reportData)}
                    </div>
                    <div className="grf-actions">
                        <button
                            type="button"
                            className="grf-download-btn"
                            onClick={() => downloadReport(selectedReport)}
                        >
                            Скачать отчет
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default GetReportForm;