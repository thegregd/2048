export interface Status {
    type: string;
    message: string;
}

export interface GameState {
    board: number[][];
    status: Status;
    score: number;
    hint?: string;
}

const URL = "http://localhost:8080/game";

export async function startGame() {
    const res = await fetch(`${URL}/start`, {
        method: "POST"
    });

    return await res.json();
}

export async function move(dir: string) {
    const res = await fetch(`${URL}/move/${dir}`, {
        method: "POST"
    });

    return await res.json();
}

export async function getHint() {
    const res = await fetch(`${URL}/hint`, {
        method: "GET"
    });

    return await res.json();
}

export async function checkGameStatus(game: GameState) {
    return game.status.type === "win" || game.status.type === "lose";
}