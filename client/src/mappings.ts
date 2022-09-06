export { fetchNewGameId };

const API_URL = "http://localhost:8080";
const endpoints = {
    newgame: "/newgame",
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
