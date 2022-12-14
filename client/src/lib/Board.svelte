<script lang="ts">
  import Score from "./utils/Score.svelte";
  import End from "./End.svelte";

  import {
    fetchNewGameId,
    fetchFirstFold,
    fetchSecondFold,
    fetchChooseTrump,
    fetchStartRound,
  } from "../utils/mappings";

  const MAX_POINTS = 1000;
  const N_FOLDS = 9;
  const TIME_SLEEP = 1000;

  // Données logiques
  let data: any = {
    idGame: 0,
    counterRound: 0,
    trump: -1,
    counterFold: 0,
    idFirstForFold: 0,
    board: [],
    idWinner: 0,
    scorePerson: 0,
    scoreBot: 0,
    hand: [],
    playableCards: [],
    playedCards: [],
  };

  let display = {
    boardStatus: {
      card: "",
      startIndex: 0,
      remainingToDisplay: 0,
    },
    trump: {
      choice: -1,
      current: "",
      showCurrent: false,
      me: false,
      showSelection: false,
    },
    cardPlayedId: -1,
    showEnd: false,
    visibleMe: false,
  };

  let startIndex = data.idFirstForFold;
  let n = data.board.length;

  // Deck du joueur et cartes des bots
  let deck = [];
  let deckBot = [
    { name: "", visible: false },
    { name: "", visible: false },
    { name: "", visible: false },
  ];

  // Fonction d'attente active
  const timeout = async (ms: number) =>
    new Promise((res) => setTimeout(res, ms));

  let next = false;
  async function waitUserInput() {
    while (next === false) {
      await timeout(50);
    }
    next = false;
  }

  // "Déplace" la carte jouée sur le plateau
  async function playCardOnBoard(x: number) {
    display.visibleMe = true;
    display.boardStatus.card = deck[x].name;

    for (let i = 0; i < deck.length; ++i) {
      deck[i].playable = false;
    }

    display.cardPlayedId = x;

    deck.splice(x, 1);

    next = true;
  }

  async function sleep(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }

  // Initialise les cartes du joueur
  function setAndShowDeck(cardState: typeof data) {
    for (let i = 0; i < 9; ++i) {
      deck.push({
        name: `cards/card_${cardState.hand[i].color}_${cardState.hand[i].value}_160.png`,
        visible: true,
        playable: false,
      });
    }

    deck = deck;
  }

  // Met à jour l'atout
  function setTrump(id: number) {
    display.trump.showSelection = false;
    display.trump.choice = id;
    display.trump.current = "trump_" + id + ".png";
    next = true;
  }

  // Rend jouables les cartes qui le sont
  function makeCardsPlayable() {
    for (let i = 0; i < data.playableCards.length; ++i) {
      deck[data.playableCards[i]].playable = true;
    }
  }

  // Fonctions d'affichage d'une carte de bot sur le plateau

  function setDeckBot(id: number) {
    let cards = data.board;
    for (let i = 0; i < cards.length; ++i) {
      if (cards[i].playerId == id) {
        deckBot[id - 1].name =
          "cards/card_" + cards[i].color + "_" + cards[i].value + "_160.png";
      }
    }
  }

  function setDeckBotAsc(id: number) {
    let cards = data.playedCards;
    let len = data.playedCards.length;
    for (let i = len - 1; i >= len - 4; --i) {
      if (cards[i].playerId == id) {
        deckBot[id - 1].name =
          "cards/card_" + cards[i].color + "_" + cards[i].value + "_160.png";
      }
    }
  }

  function setDeckBotChoose(id: number, second: boolean) {
    if (second) {
      setDeckBotAsc(id);
    } else {
      setDeckBot(id);
    }
    deckBot[id - 1].visible = true;
  }

  // Fonction d'affichage des cartes sur le plateau
  async function showCard(
    startIndex: number,
    remainingToDisplay: number,
    second: boolean
  ) {
    for (let i = 0; i < remainingToDisplay; ++i) {
      await sleep(TIME_SLEEP);
      switch (startIndex) {
        case 0:
          break;
        case 1:
          setDeckBotChoose(1, second);
          break;
        case 2:
          setDeckBotChoose(2, second);
          break;
        case 3:
          setDeckBotChoose(3, second);
          break;
      }
      startIndex = (startIndex + 1) % 4;
    }

    if (!second) {
      makeCardsPlayable();
    }
  }

  // Vérifie, choisit si nécessaire, et affiche l'atout
  async function checkTrump(data: any): Promise<void> {
    setAndShowDeck(data);
    if (data.trump == -1) {
      display.trump.showSelection = true;
      display.trump.showCurrent = false;

      await waitUserInput();
      await fetchChooseTrump(data.idGame, display.trump.choice);
      display.trump.showCurrent = true;
    } else {
      display.trump.showSelection = false;
      display.trump.showCurrent = true;
      display.trump.current = "trump_" + data.trump + ".png";
    }
  }

  // Boucle principale du jeu
  async function mainLoop(gameId: number) {
    data.idGame = gameId;
    while (data.scoreBot < MAX_POINTS && data.scorePerson < MAX_POINTS) {
      for (let i = 0; i < N_FOLDS; ++i) {
        data = await fetchFirstFold(data.idGame, 0);

        if (i == 0) {
          if (data.counterRound == 1) {
            await checkTrump(data);
          }
        }

        startIndex = data.idFirstForFold;

        n = data.board.length;

        await showCard(startIndex, n, false);

        await waitUserInput();

        data = await fetchSecondFold(data.idGame, 0, display.cardPlayedId);

        await showCard(startIndex + n, 4 - n, true);

        await sleep(TIME_SLEEP * 2);

        for (let i = 0; i < deckBot.length; ++i) {
          deckBot[i].visible = false;
        }
        display.visibleMe = false;
      }
      data = await fetchStartRound(data.idGame, 0);
      await checkTrump(data);
    }
    display.showEnd = true;
  }

  // Début de la logique
  fetchNewGameId().then((id) => {
    mainLoop(id);
  });
</script>

<!--
@component
Composant qui gère et affiche une partie du jeu.
-->
<main>
  {#if !display.showEnd}
    <div id="left">
      <Score
        scoreBot={data.scoreBot}
        scorePerson={data.scorePerson}
        wide={false}
      />
    </div>

    <div id="middle" class="table">
      <div class="board">
        <div class="tapis">
          <table class="tab_tapis">
            <tr>
              {#if display.trump.showSelection}
                <td class="card-trump"
                  ><img
                    src="trump_0.png"
                    alt="trump"
                    on:click={() => setTrump(0)}
                  /></td
                >
              {:else}
                <td />
              {/if}

              {#if deckBot[1].visible}
                <td class="card-small"
                  ><img src={deckBot[1].name} alt="carte" /></td
                >
              {:else}
                <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td
                >
              {/if}

              {#if display.trump.showSelection}
                <td class="card-trump"
                  ><img
                    src="trump_1.png"
                    alt="trump"
                    on:click={() => setTrump(1)}
                  /></td
                >
              {:else}
                <td />
              {/if}
            </tr>

            <tr>
              {#if deckBot[2].visible}
                <td class="card-small"
                  ><img src={deckBot[2].name} alt="carte" /></td
                >
              {:else}
                <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td
                >
              {/if}

              <td />

              {#if deckBot[0].visible}
                <td class="card-small"
                  ><img src={deckBot[0].name} alt="carte" /></td
                >
              {:else}
                <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td
                >
              {/if}
            </tr>

            <tr>
              {#if display.trump.showSelection}
                <td class="card-trump"
                  ><img
                    src="trump_2.png"
                    alt="trump"
                    on:click={() => setTrump(2)}
                  /></td
                >
              {:else}
                <td />
              {/if}

              {#if display.visibleMe}
                <td id="card_me" class="card-small"
                  ><img src={display.boardStatus.card} alt="carte" /></td
                >
              {:else}
                <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td
                >
              {/if}

              {#if display.trump.showSelection}
                <td class="card-trump"
                  ><img
                    src="trump_3.png"
                    alt="trump"
                    on:click={() => setTrump(3)}
                  /></td
                >
              {:else}
                <td />
              {/if}
            </tr>
          </table>
        </div>

        <div>
          <table class="tab_tapis">
            <tr>
              {#each deck as { name, visible, playable }, i}
                {#if visible}
                  <td>
                    <div class="card-small">
                      <img
                        id={name}
                        src={name}
                        class={playable ? "" : "noclick"}
                        alt="carte"
                        on:click={() => playCardOnBoard(i)}
                      />
                    </div>
                  </td>
                {/if}
              {/each}
            </tr>
          </table>
        </div>
      </div>
    </div>

    {#if display.trump.showCurrent}
      <div id="right" class="card-trump">
        <img src={display.trump.current} alt=" " />
      </div>
    {/if}
  {:else}
    <End scorePerson={data.scorePerson} scoreBot={data.scoreBot} />
  {/if}
</main>

<style>
  #left {
    position: absolute;
    top: 12px;
    left: 12px;
  }

  #right {
    position: absolute;
    top: 12px;
    right: 12px;
  }

  .tapis {
    margin-bottom: 10%;
    padding: 10px;
  }

  .tab_tapis {
    margin: auto;
  }

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    aspect-ratio: 1 / 1.5;
    border-radius: 15px;
  }

  .table {
    background-color: #015c22;
    border-radius: 1em;
    height: 800px;
    width: 1200px;
    margin: auto;
    padding: 10px;
    border: 1em solid black;
  }

  .board {
    top: 40%;
    left: 20%;
  }

  .noclick {
    pointer-events: none;
    opacity: 0.5;
  }

  .card-small {
    border: 0.2em solid black;
    border-radius: 10%;
    height: 150px;
    width: 105px;
    margin-right: 5px;
    float: left;
    background-color: white;
  }

  .card-transparent {
    border-radius: 10%;
    height: 150px;
    width: 105px;
    margin-right: 5px;
    float: left;
  }

  .card-trump {
    border: 0.2em solid black;
    border-radius: 10%;
    height: 150px;
    width: 150px;
    float: left;
    background-color: white;
  }
</style>
