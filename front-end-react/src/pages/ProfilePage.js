import { observer } from "mobx-react-lite";
import React, { useState } from "react";
import "../resources/css/Stats.css";
import StatsTable from "../components/StatsTable";

const ProfilePage = ({authService, kenoGameService, rouletteGameService, slotsGameService }) => {
    const [selectedGame, setSelectedGame] = useState("Кено");
    const [kenoData, setKenoData] = useState([]);
    const [rouletteData, setRouletteData] = useState([]);
    const [slotsData, setSlotsData] = useState([]);
    const [kenoDisplay, setKenoDisplay] = useState(false);
    const [rouletteDisplay, setRouletteDisplay] = useState(false);
    const [slotsDisplay, setSlotsDisplay] = useState(false);

    const gameOptions = ["Кено", "Рулетка", "Слоты"];
    

    const handleGameChange = async (game) => {
        setKenoDisplay(false);
        setRouletteDisplay(false);
        setSlotsDisplay(false);
        setSelectedGame(game);
        if(game === "Кено"){
            const res = await kenoGameService.getStats();
            setKenoData(res.data);
            setKenoDisplay(true);
        }
        if(game === "Рулетка"){
            const res = await rouletteGameService.getStats();
            console.log(res.data);
            setRouletteData(res.data);
            setRouletteDisplay(true);
        }
        if(game === "Слоты"){
            const res = await slotsGameService.getStats();
            setSlotsData(res.data);
            setSlotsDisplay(true);
        }
      };
    

    return (
        <div className="player-stats-container">
      <div className="game-switcher">
        {gameOptions.map((game) => (
          <button
            key={game}
            className={`game-switcher-button ${selectedGame === game ? "active" : ""}`}
            onClick={() => handleGameChange(game)}
          >
            {game}
          </button>
        ))}
      </div>
      <div className="stats-table">
        <table>
            { kenoDisplay && <StatsTable arr={kenoData} type="keno" />}
            { rouletteDisplay && <StatsTable arr={rouletteData} type="roulette" /> }
            { slotsDisplay && <StatsTable arr={slotsData} type="slots" /> }
        </table>
      </div>
    </div>
    );
};

export default observer(ProfilePage);