import React, { useState } from "react";
import "../../resources/css/RouletteGame.css";

const RouletteGamePage = ({rouletteGameService, authService}) => {
    const [bet, setBet] = useState('');
    const [i, setI] = useState(1);
    const [containerStyle, setContainerStyle] = useState();
    const [isLoading, setIsLoading] = useState(false);


    const handlePlayClick = async () => {
        if(bet > 0 && bet !== null){
            setIsLoading(true);

            const result = await rouletteGameService.playProcess(bet);
            if(result.status && result.status === 200){
                let deg = result.data.deg + 360*100*i;
                setContainerStyle({ transform: "rotate(" + deg + "deg)" });
                setI(i+1);

                soundPlay();

                setTimeout(() => {
                    setIsLoading(false);
                    authService.fetchBalance();
                }, 5500);
                      
                setBet('');
            } else {
                setIsLoading(false);
                alert(result.response.data.message);
            }
        }else{
            alert("Ставка должна быть положительной!");
        }
    };

    function soundPlay() {
        let audio = new Audio();
        audio.src = "/mp3/wheel.mp3";
        audio.autoplay = true;
    }

  return (
    <div className="roulette-game-container">
    <button id="spin" onClick={handlePlayClick} disabled={isLoading}>Spin</button>
    <center><span className="arrow"></span></center>
    <center><div className="container" style={containerStyle}>
        <div className="one">win</div>
        <div className="two">lose</div>
    </div></center>

    <div>
          <h3>Введите ставку:</h3>
          <input className="betInput" 
          type="number"
          min="0"
          placeholder="Введите ставку"
          value={bet}
          onChange={(e) => {
            const value = e.target.value;
            if (/^\d*$/.test(value)) {
              setBet(value);
            }
          }}
          />
        </div>
    </div>
  );

};
  
  export default RouletteGamePage;