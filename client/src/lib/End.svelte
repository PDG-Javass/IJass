<script lang="ts">
    import { resetPage, page } from "../stores";

    export let winning:boolean = false;
    function handleNext() {
        resetPage(page);
    }

    function getEndText(winning: boolean): string {
        this.winning;
        return winning ? "Tu as gagné!" : "Tu n'est pas à la hauteur...";
    }
</script>

<main>
    
</main>

<style>
</style>
