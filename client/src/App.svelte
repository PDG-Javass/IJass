<script lang="ts">
  import Board from "./lib/Board.svelte";
  import Home from "./lib/Home.svelte";
  import Rules from "./lib/Rules.svelte";

  const pages = [
    { name: "board", component: Board },
    { name: "home", component: Home },
    { name: "rules", component: Rules },
  ];

  let selected = pages[1];
</script>

<main>
  <svelte:component this={selected.component} />
</main>

<style>
</style>
