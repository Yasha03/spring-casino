import axiosInstance from "./api";

export const play = async (sendData) => {
    try {
        const response = await axiosInstance.post('/game/keno', sendData );
        return response;
    } catch (error) {
        return error;
    }
};

export const loadStats = async () => {
    const response = await axiosInstance.post('/game/keno/stats');
    return response;
};
