import jwtDecode from "jwt-decode";
import axiosInstance from "./api";


export const fetchBalance = async() => {
    const response = await axiosInstance.post('/users/current');
    return response.data.balance;
};

export const login = async (email, password) => {
    try {
        localStorage.removeItem('token');
        const response = await axiosInstance.post('/auth/login', {email, password} );
        const data = response.data;
        localStorage.setItem('token', data.token);
        return data;
    } catch (error) {
        console.error(error);
        return null;
    }
};

export const register = async (email, password) => {
    try {
        if(localStorage.getItem('token')) localStorage.removeItem('token');
        const response = await axiosInstance.post('/register', {email, password} );
        return true;
    } catch (error) {
        console.error(error);
        return null;
    }
};

export const check = async () => {
    try {
        const {data} = await axiosInstance.get('/auth');
        localStorage.setItem('token', data.token);
        return jwtDecode(data.token);
    } catch (error) {
        console.log(error);
        return null;
    }
};

export const logout = async () => {
    const response = await axiosInstance.post('auth/logout');
    localStorage.removeItem('token');
};
