<script lang="ts">
  export let scoreBot: number;
  export let scorePerson: number;
  export let wide: boolean;
</script>

<main>
  {#if wide}
    <div class="score wide">
      <p class="title">Score final</p>
      <p class="line">Moi + Lapinou</p>
      <p class="amount">{scorePerson}</p>
      <p class="line">Chacha + Titi</p>
      <p class="amount">{scoreBot}</p>
    </div>
  {:else}
    <div class="score">
      <p class="title">Score</p>
      <p class="line">Moi + Lapinou</p>
      <p class="amount">{scorePerson}</p>
      <p class="line">Chacha + Titi</p>
      <p class="amount">{scoreBot}</p>
    </div>
  {/if}
</main>

<style>
  .score {
    border-radius: 0.8em;
    background-color: white;
    margin: auto;
    padding: 10px;
    font-size: 1em;
    font-weight: bold;
    width: 10em;
  }

  .wide {
    font-size: 2em;
    width: 30%;
  }

  .score .title {
    font-size: 1.5em;
    font-style: italic;
  }

  .score .line {
    background-color: #fee100;
    border-radius: 1em;
    padding: 1em;
  }

  .score .amount {
    font-weight: bolder;
    font-style: italic;
    padding-top: 0;
    margin-top: 0;
  }
</style>
