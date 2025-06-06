import React from "react";
import './AdminReport.scss'
import ReportForm from "../../../components/Reports/ReportForm/ReportForm.jsx";
import GetReportForm from "../../../components/Reports/GetReportsForm/GetReportForm.jsx";

const AdminReport = () => {
    return (
        <div className="report-form">
            <GetReportForm />
            <ReportForm />
        </div>
    )
}

export default AdminReport;