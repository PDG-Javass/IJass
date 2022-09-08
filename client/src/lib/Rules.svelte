<script lang="ts">
  import { nextPage, page } from "../utils/stores";

  const ruleUrl = "https://www.jassbielbienne.ch/dokus/le_chibre.pdf";

  function handleNext() {
    nextPage(page);
  }
</script>

<main>
  <p>Si tu souhaites relire ou apprendre les règles du jeu:</p>
  <a href={ruleUrl} target="_blank">➡Clique sur ce lien⬅</a>
  <br />
  <button on:click={handleNext}>Continuer</button>
</main>

<style>
  p {
    font-size: 3em;
    font-style: italic;
    font-weight: 700;
    line-height: 1em;
  }

  @keyframes grow {
    from {
      font-size: 1em;
    }
    to {
      font-size: 3em;
    }
  }

  a {
    animation-name: grow;
    animation-duration: 10s;
    animation-direction: alternate;
    animation-iteration-count: infinite;
    color: black;
    font-weight: bold;
    text-decoration: underline;
    font-style: italic;
  }
</style>
