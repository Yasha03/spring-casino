import { loadStats, play } from "../http/KenoGameApi";

class KenoGameService {

    async playProcess(bet, selectedNumbers) {
        const data = {
            selectedNumbers: selectedNumbers,
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

export default new KenoGameService();