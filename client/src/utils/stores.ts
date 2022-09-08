import { writable, type Writable } from "svelte/store";
export { page, nextPage, resetPage };

const page = writable(0);

function nextPage(page: Writable<number>): void {
  page.update((value) => value + 1);
}

function resetPage(page: Writable<number>): void {
  page.update((value) => 0);
}
