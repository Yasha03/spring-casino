import { observer } from "mobx-react-lite";
import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import "../resources/css/Login.css";
import { Link, useNavigate } from "react-router-dom";

const RegisterForm = observer(({authService}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [redirect, setRedirect] = useState(false);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        const success = await authService.register(username, password);
        setIsLoading(false);
        
        if (!success){
            setError('Invalid email/password');
        } else {
            setRedirect(true);
        }
    };

    if (redirect) {
        navigate("/login");
    }

    return (
        <div className="login-container">
            
            <Form className="login-form" onSubmit={handleSubmit}>
                <h1>Register</h1>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label className="form-label">Email address</Form.Label>
                    <Form.Control
                        className="w-100"
                        type="email"
                        placeholder="Enter email"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label className="form-label">Password</Form.Label>
                    <Form.Control
                        className="w-100"
                        type="password"
                        placeholder="Enter password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Group>
                {error && <div>{error}</div>}
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Link to="/login">Have an account? Log in</Link>
            </Form>
        </div>
    );
});

export default RegisterForm;