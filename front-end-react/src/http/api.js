import axios from "axios";

const API_URL = 'http://5.159.103.107:8099/api/v1';

const axiosInstance = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

axiosInstance.interceptors.request.use( (config) => {
    const token = localStorage.getItem('token');
    if(token) {
        config.headers.Authorization = token;
    }
    return config;
});

axiosInstance.interceptors.response.use( (config) => {
    return config;
});

export default axiosInstance;
