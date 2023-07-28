import { observer } from "mobx-react-lite";
import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../../resources/css/KenoGame.css";
import Modal from "../../components/Modal";
import { CSSTransition } from "react-transition-group";

const KenoGamePage = ({kenoGameService, authService}) => {
    const [selectedNumbers, setSelectedNumbers] = useState([]);
    const [bet, setBet] = useState('');
    const [modalActive, setModalActive] = useState();
    const [resultGame, setResultGame] = useState();
    const [errorModalActive, setErrorModalActive] = useState();
    const [errorGame, setErrorGame] = useState();
  
    const handleNumberClick = (number) => {
      if (selectedNumbers.includes(number)) {
        setSelectedNumbers(selectedNumbers.filter((n) => n !== number));
      } else {
        if (selectedNumbers.length < 10) {
          setSelectedNumbers([...selectedNumbers, number]);
        } else {
          alert('Можно выбрать только 10 чисел');
        }
      }
    };
  
    const handleBetChange = (event) => {
      setBet(event.target.value);
    };
  
    const handlePlayClick = async () => {
        if(selectedNumbers.length === 10) {
          if(bet > 0 && bet !== null){
            const result = await kenoGameService.playProcess(parseInt(bet), selectedNumbers);
            if(result.status && result.status === 200){
              setResultGame(result.data);
              setModalActive(true);

              setSelectedNumbers([]);
              setBet('');
              authService.fetchBalance();
            } else {
              console.log(result.response.data);
              setErrorGame(result.response.data)
              setErrorModalActive(true);
            }
            
          } else {
            alert("Ставка должна быть положительной!");
          }
        } else {
          alert("Выберите 10 чисел!");
        }
    };
  
    const getNumberRows = () => {
      const rows = [];
      let row = [];
  
      for (let i = 1; i <= 80; i++) {
        row.push(
          <button
            key={i}
            onClick={() => handleNumberClick(i)}
            className={`number-button ${
              selectedNumbers.includes(i) ? 'selected' : ''
            }`}
            disabled={selectedNumbers.length === 10 && !selectedNumbers.includes(i)}
          >
            {i}
          </button>
        );
  
        if (i % 10 === 0) {
          rows.push(<div key={i} className="number-row">{row}</div>);
          row = [];
        }
      }
  
      return rows;
    };
  
    return (
      <div className="keno-game-container">
        <center><h1 className="keno-game-title">Игра Кено</h1></center>
        <center><div>
          <h2>Выберите 10 чисел</h2>
          <div className="number-grid">{getNumberRows()}</div>
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
        <button className="betSubmit" onClick={handlePlayClick}>Играть</button>
        {resultGame && (
          <CSSTransition in={modalActive} classNames='alert' timeout={500} unmountOnExit>
          <Modal active={modalActive} setActive={setModalActive}>
            <div>Количество правильных чисел: {resultGame.countCorrectNumbers.toString()}</div>
            <div>Правильные значения: {resultGame.correctNumbers.join(", ")}</div>
            <div>Ваша ставка: {resultGame.bet.toString()}</div>
            <div>Ваш выигрыш: {resultGame.winning.toString()}</div>
          </Modal>
          </CSSTransition>
        )}
        {errorGame && (
          <CSSTransition in={errorModalActive} classNames='alert' timeout={500} unmountOnExit>
          <Modal active={errorModalActive} setActive={setErrorModalActive}>
            <div>Исключение: {errorGame.exceptionName}</div>
            <div>Сообщение: {errorGame.message}</div>
          </Modal>
          </CSSTransition>
        )}
        
      </div>
    );
  };
  
  export default KenoGamePage;
