<script lang="ts">
    import { nextPage, page } from "../stores";
    const ruleUrl =
        "https://www.swisslos.ch/fr/jass/informations/les-regles-du-jass/bases-du-jass.html";

    function handleNext() {
        nextPage(page);
    }
</script>

<main>
    <p>Si tu souhaites relire les règles du jeu:</p>
    <a href={ruleUrl}>➡Clique sur ce lien⬅</a>
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

    a {
        color: salmon;
        font-size: 2.5em;
        font-weight: bold;
        text-decoration: underline;
        text-shadow: 10px;
    }

    a:hover {
        color: coral;
        transition: color 1s ease;
    }
</style>
