<script lang="ts">
  import { page } from "./stores";

  import Board from "./lib/Board.svelte";
  import Home from "./lib/Home.svelte";
  import Rules from "./lib/Rules.svelte";
  import Choice from "./lib/Choice.svelte";

  let pageStatus: number;

  page.subscribe((value) => {
    pageStatus = value;
  });

  const pages = [
    { name: "home", component: Home },
    { name: "rules", component: Rules },
    { name: "choice", component: Choice },
    { name: "board", component: Board },
  ];

  $: selected = pages[pageStatus];
</script>

<main>
  <svelte:component this={selected.component} />
</main>

<style>
</style>
