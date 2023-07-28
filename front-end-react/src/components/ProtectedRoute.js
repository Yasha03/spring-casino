import { observer } from "mobx-react-lite";
import React from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import AdminPage from "../pages/AdminPage";

const ProtectedRoute = ({ element: Element, allowedRoles, redirectTo, authService, ...rest }) => {
    const userRole = authService.user.role;
    if (!userRole || !allowedRoles.includes(userRole)) {
        return <Navigate to={redirectTo} />;
    }

    return Element;
};

export default observer(ProtectedRoute);
