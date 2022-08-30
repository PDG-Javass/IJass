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

<style>
</style>
