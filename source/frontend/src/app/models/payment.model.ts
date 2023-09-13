export class Payment {
    id: string;
	quantity: string;
	month: string;
	year: string;
	concept: string;

	constructor(id: string = "", quantity: string = "", month: string = "", year: string = "", concept: string = "") {
		this.id = id;
		this.quantity = quantity;
		this.month = month;
		this.year = year;
		this.concept = concept;
	}
}