import React, { useEffect, useState } from "react";
import "../../resources/css/SlotsGame.css";
import { CSSTransition } from "react-transition-group";

const SlotsGamePage = ({slotsGameService, authService}) => {
    const [bet, setBet] = useState('');
    const [gameState, setGameState] = useState([0, 0, 0]);
    const [textMsg, setTextMsg] = useState('');
    const [playAnimation, setPlayAnimataion] = useState(false);
    

    const slotsData = [
        {
            id: 1,
            value: [0,1,2,3,4,5,6,7,8,9]
        },
        {
            id: 2,
            value: [0,1,2,3,4,5,6,7,8,9]
        },
        {
            id: 3,
            value: [0,1,2,3,4,5,6,7,8,9]
        }
    ]

    const handlePlayClick = async () => {
        const resultResponse = await slotsGameService.playProcess(bet);
        console.log(resultResponse);
        if(resultResponse.status && resultResponse.status === 200){
            setPlayAnimataion(true);
            setGameState(resultResponse.data.numbers);
            authService.fetchBalance();
            setTextMsg("Ваш выигрыш " + resultResponse.data.winning);
        } else {
            alert(resultResponse.response.data.message);
        }
    };

    // useEffect( () => {
    //     setGameState([0, 0, 0]);
    // }, [playAnimation]);


  return (
    <div className="game-container">
        <center><h1>Слоты</h1></center>
        <div className="slots-container">
            <div className="slots-window">
                {gameState.map((el, idx) => (
                    <div className="slots-window-item">
                        {/* <CSSTransition in={playAnimation} timeout={1000} className="animationItem" onExited={() => setPlayAnimataion(false)}>
                            <div className="slots-window-item-lisp">
                                {slotsData[idx].value.map((itemLisp, idx) => (
                                    <div className="slots-window-item-lisp-item" key={`itemLisp${idx}`}>{itemLisp}</div>
                                ))}
                            </div>
                        </CSSTransition> */}
                        <div key={`slot${idx}`}>{el}</div>
                    </div>
                ))}
            </div>
        </div>

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
          <button className="betSubmit" onClick={handlePlayClick}>Играть</button>
        </div>

        <div className="msg-container">
          {textMsg && textMsg}
        </div>
    </div>
  );

};
  
  export default SlotsGamePage;