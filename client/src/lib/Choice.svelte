<script lang="ts">
  import { nextPage, page } from "../utils/stores";

  function handleNext() {
    nextPage(page);
  }
</script>

<main>
  <button on:click={handleNext}><p>Partie solo</p></button>
  <br />
  <button class="uninplemented">
    <p>Partie entre amis</p>
    <p id="subtext">Pas encore disponible</p>
  </button>
</main>

<style>
  button {
    min-width: 70%;
  }

  .uninplemented {
    background-color: lightgray;
  }

  .uninplemented #subtext {
    font-size: 0.7em;
    font-style: italic;
  }
</style>
