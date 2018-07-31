export class Collection<T> {
id: string;
items: Array<T>;
numberOfItem: number;
totalItems: number;

constructor(id, items, numberOfItem, totalItems) {
	this.id = encodeURIComponent(id);
	this.items = items;
	this.numberOfItem = numberOfItem;
	this.totalItems = totalItems;
}
}