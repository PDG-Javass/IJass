<script lang="ts">
  export let selected;
  //player's deck
  let deck = [
    { name: "cards/card_0_0_160.png", visible: true },
    { name: "cards/card_0_1_160.png", visible: true },
    { name: "cards/card_0_2_160.png", visible: true },
    { name: "cards/card_0_3_160.png", visible: true },
    { name: "cards/card_0_4_160.png", visible: true },
    { name: "cards/card_0_5_160.png", visible: true },
    { name: "cards/card_0_6_160.png", visible: true },
    { name: "cards/card_0_7_160.png", visible: true },
    { name: "cards/card_0_8_160.png", visible: true },
  ];

  let card_board = "";
  let visible = false;

  //play the selected card
  function moveCardToBoard(x) {
    deck[x].visible = false;
    visible = true;
    card_board = deck[x].name;
  }
</script>

<body>
  <!-- board with cards -->
  <div class="main">
    <div class="table">
      <div class="board">
        <!-- fold cards -->
        <div class="tapis">
          <table class="tab_tapis">
            <tr>
              <td />
              <td id="card_co" class="card-small"
                ><img src="cards/card_0_0_160.png" alt="carte" /></td
              >
              <td />
            </tr>
            <tr>
              <td id="card_op_1" class="card-small"
                ><img src="cards/card_1_8_160.png" alt="carte" /></td
              >
              <td />
              <td id="card_op_2" class="card-small"
                ><img src="cards/card_2_7_160.png" alt="carte" /></td
              >
            </tr>
            <tr>
              <td />

              {#if visible}
                <td id="card_me" class="card-small"
                  ><img src={card_board} alt="carte" /></td
                >
              {/if}

              <td />
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
                  <td id="card_0"
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

  .center {
    text-align: center;
  }

  .main {
    width: 1000px;
    margin: auto;
    padding: 10px;
  }

  .table {
    background-color: green;
    height: 800px;
    width: 120%;
    margin: 0 auto;
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
</style>
