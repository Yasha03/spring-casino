import React from "react";
import { observer } from "mobx-react-lite";

const StatTable = ({ arr, type }) => {
  const renderTable = () => {
    switch (type) {
      case "keno":
        return renderKenoTable();
      case "roulette":
        return renderRouletteTable();
      case "slots":
        return renderSlotsTable();
      default:
        return null;
    }
  };

  const renderKenoTable = () => {
    return (
      <table>
        <thead>
          <tr>
            <th>Index</th>
            <th>Дата</th>
            <th>Выбранные числа</th>
            <th>Корректные числа</th>
            <th>Ставка</th>
            <th>Выигрыш</th>
            <th>Коэффицент</th>
          </tr>
        </thead>
        <tbody>
          {arr.map((item, index) => (
            <tr key={index}>
              <td>{index}</td>
                <td>{item.createdDate}</td>
                <td>{item.selectedNumbers.join(", ")}</td>
                <td>{item.correctNumbers.join(", ")}</td>
                <td>{item.bet}</td>
                <td>{item.winning}</td>
                <td>{item.coeff}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  };

  const renderRouletteTable = () => {
    return (
        <table>
          <thead>
            <tr>
              <th>Index</th>
              <th>Дата</th>
              <th>Ставка</th>
              <th>Выигрыш</th>
              <th>Победа?</th>
            </tr>
          </thead>
          <tbody>
            {arr.map((item, index) => (
              <tr key={index}>
                  <td>{index}</td>
                  <td>{item.createdDate}</td>
                  <td>{item.bet}</td>
                  <td>{item.winning}</td>
                  <td>{item.isWin ? "Да" : "Нет"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
  };

  const renderSlotsTable = () => {
    return (
        <table>
          <thead>
            <tr>
              <th>Index</th>
              <th>Дата</th>
              <th>Ставка</th>
              <th>Числа</th>
              <th>Выигрыш</th>
              <th>Коэффицент</th>
            </tr>
          </thead>
          <tbody>
            {arr.map((item, index) => (
              <tr key={index}>
                  <td>{index}</td>
                  <td>{item.createdDate}</td>
                  <td>{item.bet}</td>
                  <td>{item.numbers.join(", ")}</td>
                  <td>{item.winning}</td>
                  <td>{item.coeff}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
  };

  return <div>{renderTable()}</div>;
};

export default observer(StatTable);
