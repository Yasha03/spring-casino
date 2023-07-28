import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import Admin from "./pages/Admin";
import AuthService from "./services/AuthService";
import { ADMIN_PANEL_ROUTE, LOGIN_ROUTE, REGISTER_ROUTE } from "./utils/consts";


export const authRoutes = [
    {
        path: ADMIN_PANEL_ROUTE,
        Component: Admin
    }
]

export const publicRoutes = [
    {
        path: LOGIN_ROUTE,
        Component: <LoginForm authService={AuthService} />
    },
    {
        path: REGISTER_ROUTE,
        Component: RegisterForm
    }
]
