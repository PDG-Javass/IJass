<script lang="ts">
  import { nextPage, page } from "../utils/stores";

  import homeCard from "../assets/homeCards.webp";

  function handleStart() {
    nextPage(page);
  }
</script>

<!--
@component
Composant qui affiche la page d'accueil.
-->
<main>
  <h1>Bienvenue sur Javass</h1>
  <img src={homeCard} alt="Cartes de Jass" />

  <button on:click={handleStart}>Commencer une partie</button>
</main>

<style>
  h1 {
    font-size: 5em;
  }

  img {
    display: block;
    margin: auto;
    width: 50%;
  }
</style>
