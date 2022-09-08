<script lang="ts">
  import Score from "./utils/Score.svelte";

  import { resetPage, page } from "../utils/stores";

  export let scoreBot: number;
  export let scorePerson: number;

  let winning = scorePerson > scoreBot;

  function getEndText(winning: boolean): string {
    return winning ? "Tu as gagné!" : "Tu n'es pas à la hauteur...";
  }

  function handleNext() {
    resetPage(page);
  }
</script>

<main>
  <Score {scoreBot} {scorePerson} wide={true} />
  <h1 class="wintext">{getEndText(winning)}</h1>
  <button on:click={handleNext}>Quitter</button>
</main>

<style>
  .wintext {
    font-size: 3em;
  }
</style>
