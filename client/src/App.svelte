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
  <div class="main">
    <div class="table">
      <div class="board">

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
              <td id="card_me" class="card-small"><img src="cards/card_1_5_160.png" alt="carte"></td>
              <td></td>
            </tr>
          </table>
        </div>

        <div>
          <table class="tab_tapis">
            <tr>
              <td id="card_0"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_1"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_2"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_3"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_4"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_5"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_6"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              <td id="card_7"><div class="card-small">
                <img src="cards/card_0_0_160.png" alt="carte">
              </div></td>
              
            </tr>
          </table>
      </div>
        
        
      </div>
    </div>
  </div>

 

</body>

<style>
</style>
