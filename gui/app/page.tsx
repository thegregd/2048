"use client";

import {useState} from "react";
import {startGame, move, GameState, getHint} from "@/services/api";
import Board from "@/components/Board";

export default function Home() {

  const empty =
      Array.from({length:4},
          ()=>Array(4).fill(0));

  const [board,setBoard]=useState(empty);
  const [status, setStatus] = useState({ type: "playing", message: "" });
  const [score, setScore] = useState(0);
  const [hint, setHint] = useState<string | undefined>(undefined);
  const [started,setStarted]=useState(false);

  async function updateGame(game: GameState) {
    setBoard(game.board);
    setStatus(game.status);
    setScore(game.score);
    setHint(game.hint);
    
    if (game.status.type === "win" || game.status.type === "lose") {
      setStarted(false);
    } else {
      setStarted(true);
    }
  }

  async function start(){
    const game = await startGame();
    updateGame(game);
  }

  async function play(dir:string){
    const game = await move(dir);
    updateGame(game);
  }

  async function suggestMove(){
      const game = await getHint();
      updateGame(game);
  }

  return (

      <div className="container">

        <h1>
          {status.type === "win" ? "Win!" : status.type === "lose" ? "Game Over" : "2048"}
        </h1>

        <div className="stats">
            <p>Score: {score}</p>
            {status.message && <p className="status-message">{status.message}</p>}
            {hint && <p className="hint">Hint: {hint}</p>}
        </div>

        <button onClick={start}>
          {started ? "Restart" : "Start"}
        </button>

        <div className="controls">

          <button
              disabled={!started}
              onClick={()=>play("up")}
          >
            ↑
          </button>

          <div>

            <button
                disabled={!started}
                onClick={()=>play("left")}
            >
              ←
            </button>

            <Board board={board}/>

            <button
                disabled={!started}
                onClick={()=>play("right")}
            >
              →
            </button>

          </div>

          <button
              disabled={!started}
              onClick={()=>play("down")}
          >
            ↓
          </button>

        </div>

        <button
            disabled={!started}
            onClick={suggestMove}>
          {"Hint"}
        </button>

      </div>

  );
}