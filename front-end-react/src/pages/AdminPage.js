import { observer } from "mobx-react-lite";
import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const AdminPage = ({authService}) => {
    console.log("adminq qqq");
    return (
        <div className="container">
           <h1>Admin panel</h1>
        </div>
    );
};

export default observer(AdminPage);