<script lang="ts">
  const URL = "https://server-xxcwjwt7qq-ue.a.run.app/coucou";

  async function getData() {
    const res = await fetch(URL);
    const text = await res.text();

    if (res.ok) {
      return text;
    } else {
      throw new Error(text);
    }
  }

  let data;

	function handleClick() {
		data = getData();
	}



  //player's deck
  let deck = [
    {name: "cards/card_0_0_160.png", visible: true},
    {name: "cards/card_0_1_160.png", visible: true},
    {name: "cards/card_0_2_160.png", visible: true},
    {name: "cards/card_0_3_160.png", visible: true},
    {name: "cards/card_0_4_160.png", visible: true},
    {name: "cards/card_0_5_160.png", visible: true},
    {name: "cards/card_0_6_160.png", visible: true},
    {name: "cards/card_0_7_160.png", visible: true},
    {name: "cards/card_0_8_160.png", visible: true}

  ];


  let card_board ='';
  let visible = false;

//play the selected card
  function moveCardToBoard(x){
    deck[x].visible = false;
    visible = true;
    card_board = deck[x].name;
  }




</script>

<main>
  <h1>Bienvenue</h1>
  <h2>Voici les dernières données</h2>

  {#if data != undefined}
    {#await data}
      <p>...veuillez patienter</p>
    {:then content}
      <p>Le serveur dit {content}</p>
    {:catch error}
      <p style="color: red">Erreur</p>
    {/await}
  {/if}
  <button on:click={handleClick}>Obtenir des données</button>
</main>

<body>
  <!-- board with cards -->
  <div class="main">
    <div class="table">
      <div class="board">

        <!-- fold cards -->
        <div class="tapis">
          <table class="tab_tapis">
            <tr>
              <td></td>
              <td id="card_co" class="card-small"><img src="cards/card_0_0_160.png" alt="carte"></td>
              <td></td>
            </tr>
            <tr>
              <td id="card_op_1" class="card-small"><img src="cards/card_1_8_160.png" alt="carte"></td>
              <td></td>
              <td id="card_op_2" class="card-small"><img src="cards/card_2_7_160.png" alt="carte"></td>
            </tr>
            <tr>
              <td></td>

              {#if visible}
              <td id="card_me" class="card-small"><img src={card_board} alt="carte"></td>
              {/if}

              <td></td>
            </tr>
          </table>
        </div>

        <div>
          <!-- players cards-->
          <table class="tab_tapis">
            <tr>

              <!-- each card is visible at the beginning. On click goes to board and dispear -->
              {#each deck as {name, visible}, i}
                {#if visible}
                <td id="card_0"><div class="card-small">
                  <img src={name} alt="carte" on:click={() => moveCardToBoard(i)}>
                </div></td>
                {/if}
              {/each}

              
            </tr>
          </table>
        </div>
        
        
      </div>
    </div>
  </div>

 

</body>

