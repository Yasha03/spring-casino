import { observer } from "mobx-react-lite";
import { Link } from "react-router-dom";
import "../resources/css/Navigation.css";
import { useState, useEffect } from "react";
import { observable, set } from "mobx";

const Navigation = ({ authService, user }) => {
  const handleLogout = async () => {
    await authService.logout();
  };

  return (
    <nav className="navigation">
      <div className="navigation-content">
        <div className="game-links">
          {authService.isAuth && (
            <>
              <Link className="game-link" to="/games/keno">
                Кено
              </Link>
              <Link className="game-link" to="/games/slots">
                Слоты
              </Link>
              <Link className="game-link" to="/games/roulette">
                Рулетка
              </Link>
            </>
          )}
        </div>

        <div className="welcome-section">
          {authService.isAuth ? (
            <>
              <p className="welcome-message">
                Добро пожаловать,   
                <Link to="/profile" className="game-link">
                  {authService.user.sub ? authService.user.sub : authService.user.email}
                </Link>
              </p>
              <p className="balance">Баланс: {authService.user.balance}</p>
              <button className="logout-button" onClick={handleLogout}>
                Выйти
              </button>
            </>
          ) : (
            <p><Link className="login-href" to="/login">Вход</Link>Вы не авторизованы.</p>
          )}
        </div>
      </div>
    </nav>
  );
};

const NavigationContainer = ({ authService }) => {
  const user = authService.user;

  useEffect(() => {
    const fetchBalance = async () => {
      const fetchedBalance = await authService.fetchBalance(); 
      user.balance = fetchedBalance;
    };

    fetchBalance();
  }, [authService, user.balance]);

  return <Navigation authService={authService} user={user} />;
};

export default observer(NavigationContainer);


