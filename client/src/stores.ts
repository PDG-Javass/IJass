import { writable, type Writable } from "svelte/store";
export { nextPage, resetPage };

export const page = writable(0);

function nextPage(page: Writable<number>) {
    page.update((value) => value + 1);
}

function resetPage(page: Writable<number>) {
    page.update((value) => 0);
}
