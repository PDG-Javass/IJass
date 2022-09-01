<script lang="ts">
    const ruleUrl =
        "https://www.swisslos.ch/fr/jass/informations/les-regles-du-jass/bases-du-jass.html";
</script>

<main>
    <p>Si tu souhaites relire les règles du jeu:</p>
    <a href={ruleUrl}>➡Clique sur ce lien⬅</a>
    <br />
    <button>Continuer</button>
</main>

<style>
    p {
        font-size: 3em;
        font-weight: 700;
        line-height: 1em;
    }

    button {
        border-radius: 1em;
        padding: 2rem;
        background-color: lightsalmon;
        font-weight: bold;
        margin-top: 1em;
        margin-bottom: 1em;
        max-width: 80%;
        font-size: 3em;
        text-align: center;
    }

    button:hover {
        background-color: lightcoral;
        transition: background-color 0.5s ease;
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
