<script lang="ts">
    import { page } from "../stores";

    import homeCard from "../assets/homeCards.webp";

    function handleStart() {
        page.update(value => value + 1);
    }
</script>

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
</style>
