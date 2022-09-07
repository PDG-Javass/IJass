export { fetchNewGameId, fetchFirstFold, fetchSecondFold, fetchChooseTrump };

const API_URL = "http://localhost:8080";
const endpoints = {
    newgame: "/newgame",
    firstPartFold: "/firstPartFold",
    secondPartFold: "/secondPartFold",
    chooseTrump: "/chooseTrump"
};

async function fetchRawDataFromEndpoint(endpoint: string): Promise<string> {
    const url = API_URL + endpoint;
    const res = await fetch(url);
    const text = await res.text();

    if (res.ok) {
        return text;
    } else {
        throw new Error(text);
    }
}

async function fetchNewGameId(): Promise<number> {
    return await fetchRawDataFromEndpoint(endpoints["newgame"]).then((rawId) => {
        return parseInt(rawId);
    });
}

async function fetchFirstFold(idGame: number, idPlayer: number): Promise<JSON> {
    let params = `?gameId=${idGame}&playerId=${idPlayer}`;
    return await fetchRawDataFromEndpoint(endpoints["firstPartFold"] + params).then((rawJSON) => {
        return JSON.parse(rawJSON);
    });
}

async function fetchSecondFold(idGame: number, idPlayer: number, cardPlayed: number) {
    let params = `?gameId=${idGame}&playerId=${idPlayer}&cardPlayed=${cardPlayed}`;
    return await fetchRawDataFromEndpoint(endpoints["secondPartFold"] + params).then((rawJSON) => {
        console.log(rawJSON);
        return JSON.parse(rawJSON);
    });
}

async function fetchChooseTrump(idGame: number, trump: number) {
    let params = `?gameId=${idGame}` + `&trump=${trump}`;
    return await fetchRawDataFromEndpoint(endpoints["chooseTrump"] + params).then((rawJSON) => {
        console.log(rawJSON);
        return JSON.parse(rawJSON);
    });
}