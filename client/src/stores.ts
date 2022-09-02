import { writable, type Writable } from "svelte/store";
export { nextPage };

export const page = writable(0);

function nextPage(page: Writable<number>) {
    page.update((value) => value + 1);
}
