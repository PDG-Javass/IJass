<script lang="ts">
  const json = `{
  "idGame" : 0,
  "counterRound" : 1,
  "trump" : 3,
  "counterFold" : 1,
  "idFirstForFold" : 1,
  "board" : {
    "content" : [ {
      "color" : 1,
      "value" : 5,
      "playerId" : 1
    }, {
      "color" : 1,
      "value" : 6,
      "playerId" : 2
    } ]
  },
  "idWinner" : 0,
  "scorePerson" : 0,
  "scoreBot" : 0,
  "hand" : [ {
    "color" : 2,
    "value" : 6,
    "playerId" : 0
  }, {
    "color" : 1,
    "value" : 3,
    "playerId" : 0
  }, {
    "color" : 3,
    "value" : 8,
    "playerId" : 0
  }, {
    "color" : 2,
    "value" : 5,
    "playerId" : 0
  }, {
    "color" : 3,
    "value" : 1,
    "playerId" : 0
  }, {
    "color" : 3,
    "value" : 3,
    "playerId" : 0
  }, {
    "color" : 3,
    "value" : 4,
    "playerId" : 0
  }, {
    "color" : 3,
    "value" : 5,
    "playerId" : 0
  }, {
    "color" : 1,
    "value" : 1,
    "playerId" : 0
  } ],
  "playableCards" : [ 1, 2, 4, 5, 6, 7 ]
}
  `;

  const data = JSON.parse(json);

  //player's deck
  let deck = [
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
    { name: "", visible: true, playable: false },
  ];

  let deckBot = [
    { name: "", visible: false},
    { name: "", visible: false},
    { name: "", visible: false},
  ];

  let card_board = "";
  let visible_me = false;

  let trump_me = true;
  let trump_current = "";

  function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

//set bot deck
function setBotDeck(id){
  let cards = data.board.content;

  for(let i = 0; i < cards.length; ++i){
    if(cards[i].playerId == id){
      deckBot[id-1].name = "cards/card_" + cards[i].color + "_" + cards[i].value+ "_160.png";
    }
  }
}


//show card, player card become playable
  async function showCard(begin){
  for(let i = 0; i < 4; ++i){
    await sleep(2000);
    switch(begin){
      case 0:
        {for(let i = 0; i < 9; ++i){
        deck[i].playable = true;
  }}
        break;
      case 1:
        setBotDeck(1);
        deckBot[0].visible = true;
        break;
      case 2:
        setBotDeck(2);
        deckBot[1].visible = true;
        break;
      case 3:
        setBotDeck(3);
        deckBot[2].visible = true;
        break;
      
    }
    ++begin;
    
}

}

//set trump if it's player turn
  function setTrump(id){
    trump_me = false;
    trump_current = "trump_" + id + ".png"

    for(let i = 0; i < 9; ++i){
          deck[i].playable = true;
    }
  }

  //play the selected card
  function moveCardToBoard(x: number) {
 
    deck[x].visible = false;
    visible_me = true;
    card_board = deck[x].name;

    for(let i = 0; i < 9; ++i){
      deck[i].playable = false;
    }
    
    showCard(1);

  }

  //show player deck
  function setDeck(cardState) {
    for (let i = 0; i < deck.length; ++i) {
      deck[i].name = `cards/card_${cardState.hand[i].color}_${cardState.hand[i].value}_160.png`;

      deck[i].visible = true;
    }
  }


  

  function playGame(){
  let j = 1;
  for(let i = 0; i < 8; ++i){
    
    if(j == 4){
      j = 0;
    }

    

    //await sleep(2000);
    visible_me = false;

    ++j;
  
  }

}

setDeck(data);
//playGame();
  
</script>

<div id="left"><table class="score"><tr><td/><td>Score</td><td/></tr>
<tr><td>Moi + Lapinou</td><td/><td>Chacha + Titi</td></tr>
<tr><td>xxxx</td><td/><td>xxxx</td></tr></table></div>

<!-- board with cards -->
  <div id="middle" class="table">
    <div class="board">
      <!-- fold cards -->
      <div class="tapis">
        <table class="tab_tapis">
          <tr>
            {#if trump_me}
            <td class="card-trump"><img src="trump_0.png" alt="trump" on:click={() => setTrump(0)}></td>
            {:else}
            <td/>
            {/if}

            {#if deckBot[1].visible}
            <td class="card-small"
              ><img src={deckBot[1].name} alt="carte" /></td>
            {:else}
              <td class="card-transparent"
                ><img src="cards/card_transparent.png" alt="carte" /></td>
            {/if}
            
            {#if trump_me}
            <td class="card-trump"><img src="trump_1.png" alt="trump" on:click={() => setTrump(1)}></td>
            {:else}
            <td/>
            {/if}

          </tr>

          <tr>
            {#if deckBot[2].visible}
            <td class="card-small"
              ><img src={deckBot[2].name} alt="carte" /></td>
            {:else}
              <td class="card-transparent"
                ><img src="cards/card_transparent.png" alt="carte" /></td>
            {/if}

            <td/>

            {#if deckBot[0].visible}
            <td class="card-small"
              ><img src={deckBot[0].name} alt="carte" /></td>
            {:else}
              <td  class="card-transparent"
                ><img src="cards/card_transparent.png" alt="carte" /></td>
            {/if}
          </tr>

          <tr>
            {#if trump_me}
            <td class="card-trump"><img src="trump_2.png" alt="trump" on:click={() => setTrump(2)}></td>
            {:else}
            <td/>
            {/if}

            {#if visible_me}
              <td id="card_me" class="card-small"
                ><img src={card_board} alt="carte" /></td>
            {:else}
            <td class="card-transparent"
                ><img src="cards/card_transparent.png" alt="carte" /></td>
            {/if}

            {#if trump_me}
            <td class="card-trump"><img src="trump_3.png" alt="trump" on:click={() => setTrump(3)}></td>
            {:else}
            <td/>
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
                      <img id={name}
                        src={name}
                        class="{playable ? '' : 'noclick'}"
                        alt="carte"
                        on:click={() => moveCardToBoard(i)}/>
                    </div>
                </td>
              {/if}
            {/each}
          </tr>
        </table>
      </div>
    </div>
  </div>

  <div id="right" class="card-trump"><img src={trump_current} alt=" "></div>




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

  .score{
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

  .noclick{
    pointer-events: none
    
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

  .card-transparent{
    border-radius: 10%;
    height: 150px;
    width: 105px;
    margin-right: 5px;
    float: left;
  }

  .card-trump{
    border: 0.2em solid black;
    border-radius: 10%;
    height: 150px;
    width: 150px;
    float: left;
    background-color: white;
  }
</style>
