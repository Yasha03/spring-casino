import { observable } from "mobx";
import { useContext } from "react";
import { Context } from "..";
import { Route, Router } from "react-router-dom";
import { authRoutes, publicRoutes } from "../routes";

const AppRouter = observable(() => {
    const {user} = useContext(Context);

    console.log(user);

    return(
        <Router>
            {user.isAuth && authRoutes.map(({path, Component}) => 
                <Route key={path} path={path} component={Component} exact />
            )}
            {publicRoutes.map(({path, Component}) => 
                <Route key={path} path={path} component={Component} exact />
            )}
        </Router>

    );
});

export default AppRouter;

/*
<Router>
            <Routes>
                <Route path="/login" element={<LoginForm authService={AuthService} />} />
                <Route path="/register" element={<RegisterForm authService={AuthService} />} />
            </Routes>
        </Router>*/