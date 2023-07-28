import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import AuthService from "./services/AuthService";
import KenoGameService from "./services/KenoGameService";
import Navigation from "./components/Navigation";
import AdminPage from "./pages/AdminPage";
import ProtectedRoute from "./components/ProtectedRoute";
import KenoGamePage from "./pages/games/KenoGamePage";
import RouletteGamePage from "./pages/games/RouletteGamePage";
import RouletteGameService from "./services/RouletteGameService";
import SlotsGamePage from "./pages/games/SlotsGamePage";
import SlotsGameService from "./services/SlotsGameService";
import ProfilePage from "./pages/ProfilePage";

function App() {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem("token");
    AuthService.checkToken()
      .then(() => setLoading(false))
      .catch((error) => {
        console.error(error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (

    <Router>
      <Navigation authService={AuthService} />
      <Routes>
        <Route path="/login" element={<LoginForm authService={AuthService} />} />
        <Route path="/register" element={<RegisterForm authService={AuthService} />} />
        <Route
          path="/admin"
          element={
            <ProtectedRoute
              element={<AdminPage authService={AuthService} />}
              allowedRoles={["ADMIN"]}
              redirectTo="/login"
              authService={AuthService}
            />
          }
        />
        <Route
          path="/games/keno"
          element={
            <ProtectedRoute
              element={<KenoGamePage kenoGameService={KenoGameService} authService={AuthService} />}
              allowedRoles={["ADMIN", "USER"]}
              redirectTo="/login"
              authService={AuthService}
            />
          }
        />
        <Route
          path="/games/roulette"
          element={
            <ProtectedRoute
              element={<RouletteGamePage rouletteGameService={RouletteGameService} authService={AuthService} />}
              allowedRoles={["ADMIN", "USER"]}
              redirectTo="/login"
              authService={AuthService}
            />
          }
        />
        <Route
          path="/games/slots"
          element={
            <ProtectedRoute
              element={<SlotsGamePage slotsGameService={SlotsGameService} authService={AuthService} />}
              allowedRoles={["ADMIN", "USER"]}
              redirectTo="/login"
              authService={AuthService}
            />
          }
        />
        <Route
          path="/profile"
          element={
            <ProtectedRoute
              element={<ProfilePage authService={AuthService} kenoGameService={KenoGameService} 
              rouletteGameService={RouletteGameService} slotsGameService={SlotsGameService} />}
              allowedRoles={["ADMIN", "USER"]}
              redirectTo="/login"
              authService={AuthService}
            />
          }
        />
      </Routes>
    </Router>

  );
}

export default App;
