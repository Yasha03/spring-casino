import { loadStats, play } from "../http/SlotsGameApi";

class SlotsGameService {

    async playProcess(bet) {
        const data = {
            bet: parseInt(bet),
        };
        const res = await play(data);
        return res;
    }

    async getStats() {
        const res = await loadStats();
        return res;
    }
    
}

export default new SlotsGameService();