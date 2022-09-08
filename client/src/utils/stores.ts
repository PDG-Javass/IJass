import { writable, type Writable } from "svelte/store";
export { page, nextPage, resetPage };

// Store contenant le numéro de la page courante
// afin d'implémenter une logique pseudo-multipage
const page = writable(0);

function nextPage(page: Writable<number>): void {
  page.update((value) => value + 1);
}

function resetPage(page: Writable<number>): void {
  page.update((_value) => 0);
}
