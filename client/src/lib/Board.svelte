<script lang="ts">
  const data = [
    {
      family: 1,
      value: 0
    },
    {
      family: 0,
      value: 1,
    },
    {
      family: 3,
      value: 4
    },
    {
      family: 0,
      value: 3
    },
    {
      family: 1,
      value: 8
    },
    {
      family: 0,
      value: 5
    },
    {
      family: 0,
      value: 6
    },
    {
      family: 0,
      value: 7
    },
    {
      family: 0,
      value: 8
    },
  ]

  //player's deck
  let deck = [
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
    { name: "", visible: true },
  ];

  let card_board = "";
  let visible = false;
  let visible_co = false;
  let visible_op_1 = false;
  let visible_op_2 = false;

  function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

   async function showCard(begin){
    for(let i = 0; i < 4; ++i){
      await sleep(2000);
      switch(begin){
        case 0:
          visible_op_1 = true;
          break;
        case 1:
          visible_co = true;
          break;
        case 2:
          visible_op_2 = true;
          break;
        case 3:
          break;
      }
      ++begin;
      
  }

  }

  //play the selected card
  function moveCardToBoard(x: number) {
 
    deck[x].visible = false;
    visible = true;
    card_board = deck[x].name;

    showCard(0);

  }

  function setDeck(cardState) {
    for (let i = 0; i < deck.length; ++i) {
      deck[i].name = `cards/card_${cardState[i].family}_${cardState[i].value}_160.png`;
      console.log(`cards/card_${cardState[i].family}_${cardState[i].value}_160.png`);
    }
  }

  setDeck(data);
  
</script>

<body>
  <!-- board with cards -->
    <div class="table">
      <div class="board">
        <!-- fold cards -->
        <div class="tapis">
          <table class="tab_tapis">
            <tr>
              <td />
              {#if visible_co}
              <td class="card-small"
                ><img src="cards/card_2_7_160.png" alt="carte" /></td>
              {:else}
                <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td>
              {/if}
              <td />
            </tr>
            <tr>
              {#if visible_op_1}
              <td class="card-small"
                ><img src="cards/card_2_8_160.png" alt="carte" /></td>
              {:else}
                <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td>
              {/if}

              <td />
              {#if visible_op_2}
              <td class="card-small"
                ><img src="cards/card_2_3_160.png" alt="carte" /></td>
              {:else}
                <td  class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td>
              {/if}
            </tr>
            <tr>
              <td />

              {#if visible}
                <td id="card_me" class="card-small"
                  ><img src={card_board} alt="carte" /></td>
              {:else}
              <td class="card-transparent"
                  ><img src="cards/card_transparent.png" alt="carte" /></td>
              {/if}

              <td/>
            </tr>
          </table>
        </div>

        <div>
          <!-- players cards-->
          <table class="tab_tapis">
            <tr>
              <!-- each card is visible at the beginning. On click goes to board and dispear -->
              {#each deck as { name, visible }, i}
                {#if visible}
                  <td 
                    ><div class="card-small">
                      <img
                        src={name}
                        alt="carte"
                        on:click={() => moveCardToBoard(i)}
                      />
                    </div></td
                  >
                {/if}
              {/each}
            </tr>
          </table>
        </div>
      </div>
    </div>

</body>

<style>
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
</style>
