import { makeAutoObservable } from "mobx";
import { login, register, logout, check, fetchBalance } from "../http/authApi";
import { AuthUser } from "../models/AuthUser";
import jwt_decode from "jwt-decode";
import { observer } from "mobx-react-lite";


class AuthService {
    isAuth = false;
    //user = observer(new AuthUser());
    user = new AuthUser();
    

    constructor() {
        makeAutoObservable(this);
    }

    setUser(user) {
        this.user = user;
    }

    setIsAuth(bool) {
        this.isAuth = bool;
    }

    setBalance(balance){
        this.user.balance = balance;
    }



    async fetchBalance() {
        const balance = await fetchBalance();
        this.setBalance(balance);
        return balance;
    }

    async login(username, password) {
        try {
            const response = await login(username, password);
            if(response === null) return false;
            const { email, token } = response;
            const userToken = jwt_decode(token);
            this.setUser(new AuthUser({ email: email, role: userToken.role }));
            this.setIsAuth(true);
            localStorage.setItem('token', token);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    }

    async register(username, password) {
        try {
            const response = await register(username, password);
            if(response === null) return false;
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    }

    async checkToken() {
        const token = localStorage.getItem('token');
        let dataUser = await check();
        if (dataUser !== null) {
            this.setIsAuth(true);
            this.setUser(new AuthUser(dataUser));
        } else {
            this.setIsAuth(false);
        }
    }

    async logout() {
        try {
            const token = localStorage.getItem('token');
            if (!token) return;
            await logout();
            this.setUser(new AuthUser());
            this.setIsAuth(false);
            localStorage.removeItem('token');
        } catch (error){
            console.error(error);
        }
    }

}

export default new AuthService();
