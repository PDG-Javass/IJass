<script lang="ts">
  import {
    fetchNewGameId,
    fetchFirstFold,
    fetchSecondFold,
    fetchChooseTrump,
    fetchStartRound,
  } from "../mappings";

  let card_board = "";
  let visible_me = false;

  const timeout = async (ms) => new Promise((res) => setTimeout(res, ms));
  let next = false;

  async function waitUserInput() {
    while (next === false) {
      await timeout(50);
    }
    next = false;
  }

  //play the selected card

  async function playCardOnBoard(x: number) {
    visible_me = true;
    card_board = deck[x].name;

    for (let i = 0; i < deck.length; ++i) {
      deck[i].playable = false;
    }

    display.cardPlayedId = x;

    deck.splice(x, 1);

    next = true;
  }

  const MAX_POINTS = 1000;
  const N_FOLDS = 9;
  const TIME_SLEEP = 1000;

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
      startIndex: 0,
      remainingToDisplay: 0,
    },
    trump: {
      choice: -1,
      current: "",
      me: false,
      show: false,
    },
    cardPlayedId: -1,
    p: false,
  };

  let startIndex = data.idFirstForFold;
  let n = data.board.length;
  //player's deck
  let deck = [];

  let deckBot = [
    { name: "", visible: false },
    { name: "", visible: false },
    { name: "", visible: false },
  ];

  function sleep(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }

  //show player deck
  function setAndShowDeck(cardState) {
    for (let i = 0; i < 9; ++i) {
      deck.push({
        name: `cards/card_${cardState.hand[i].color}_${cardState.hand[i].value}_160.png`,
        visible: true,
        playable: false,
      });
    }
    
    deck = deck;
  }

  //set trump if it's player turn
  function setTrump(id) {
    display.trump.show = false;
    display.trump.choice = id;
    display.trump.current = "trump_" + id + ".png";
    next = true;
  }

  function makeCardsPlayable() {
    for (let i = 0; i < data.playableCards.length; ++i) {
      deck[data.playableCards[i]].playable = true;
      console.log(data.playableCards[i]);
    }
  }

  //set bot deck
  function setDeckBot(id) {
    let cards = data.board;
    for (let i = 0; i < cards.length; ++i) {
      if (cards[i].playerId == id) {
        deckBot[id - 1].name =
          "cards/card_" + cards[i].color + "_" + cards[i].value + "_160.png";
      }
    }
  }

  function setDeckBotAsc(id) {
    let cards = data.playedCards;
    let len = data.playedCards.length;
    for (let i = len - 1; i >= len - 4; --i) {
      if (cards[i].playerId == id) {
        deckBot[id - 1].name =
          "cards/card_" + cards[i].color + "_" + cards[i].value + "_160.png";
      }
    }
  }

  function setDeckBotChoose(id, second) {
    if (second) {
      setDeckBotAsc(id);
    } else {
      setDeckBot(id);
    }
    deckBot[id - 1].visible = true;
  }

  //show card, player card become playable
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

  async function checkTrump(data) {
    setAndShowDeck(data);
    if (data.trump == -1) {
      display.trump.show = true;
      await waitUserInput();
      await fetchChooseTrump(data.idGame, display.trump.choice);
    } else {
      display.trump.show = false;
      display.trump.current = "trump_" + data.trump + ".png";
    }
  }

  async function mainLoop(gameId: number) {
    data.idGame = gameId;
    console.log(gameId);
    while (data.scoreBot < MAX_POINTS || data.scorePerson < MAX_POINTS) {
      for (let i = 0; i < N_FOLDS; ++i) {
        data = await fetchFirstFold(data.idGame, 0);

        if (i == 0) {

          if(data.counterRound == 1){
          await checkTrump(data);
          }
        }

        startIndex = data.idFirstForFold;

        n = data.board.length;

        await showCard(startIndex, n, false);

        await waitUserInput();

        data = await fetchSecondFold(data.idGame, 0, display.cardPlayedId);

        console.log("second part");

        await showCard(startIndex + n, 4 - n, true);

        await sleep(4000);

        for (let i = 0; i < deckBot.length; ++i) {
          deckBot[i].visible = false;
        }
        visible_me = false;

        console.log("fold" + i);
      }

      data = await fetchStartRound(data.idGame, 0);
      await checkTrump(data);
    }
  }

  fetchNewGameId().then((id) => {
    mainLoop(id);
  });

  function debug() {
    console.log("from debug");
    console.log(data);
  }
</script>

<div id="left">
  <table class="score" on:click={debug}>
    <tr><td /><td>Score</td><td /></tr>
    <tr><td>Moi + Lapinou</td><td /><td>Chacha + Titi</td></tr>
    <tr><td><b>{data.scorePerson}</b></td><td /><td><b>{data.scoreBot}</b></td></tr>
  </table>
</div>

<!-- board with cards -->
<div id="middle" class="table">
  <div class="board">
    <!-- fold cards -->
    <div class="tapis">
      <table class="tab_tapis">
        <tr>
          {#if display.trump.show}
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
            <td class="card-small"><img src={deckBot[1].name} alt="carte" /></td
            >
          {:else}
            <td class="card-transparent"
              ><img src="cards/card_transparent.png" alt="carte" /></td
            >
          {/if}

          {#if display.trump.show}
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
            <td class="card-small"><img src={deckBot[2].name} alt="carte" /></td
            >
          {:else}
            <td class="card-transparent"
              ><img src="cards/card_transparent.png" alt="carte" /></td
            >
          {/if}

          <td />

          {#if deckBot[0].visible}
            <td class="card-small"><img src={deckBot[0].name} alt="carte" /></td
            >
          {:else}
            <td class="card-transparent"
              ><img src="cards/card_transparent.png" alt="carte" /></td
            >
          {/if}
        </tr>

        <tr>
          {#if display.trump.show}
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

          {#if visible_me}
            <td id="card_me" class="card-small"
              ><img src={card_board} alt="carte" /></td
            >
          {:else}
            <td class="card-transparent"
              ><img src="cards/card_transparent.png" alt="carte" /></td
            >
          {/if}

          {#if display.trump.show}
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
      <!-- players cards-->
      <table class="tab_tapis">
        <tr>
          <!-- each card is visible at the beginning. On click goes to board and dispear -->
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

<div id="right" class="card-trump">
  <img src={display.trump.current} alt=" " />
</div>

<style>
  #left {
    position: absolute;
    top: 10px;
    left: 10px;
  }

  #right {
    position: absolute;
    top: 10px;
    right: 10px;
  }

  .score {
    border: 0.2em solid black;
    background-color: grey;
    padding: 10px;
    font-size: 17px;
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
    background-color: green;
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
    /*70% of height*/
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
