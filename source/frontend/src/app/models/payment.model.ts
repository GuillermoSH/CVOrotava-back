import { Player } from "./player.model";

export class Payment {
    id: string;
	quantity: string;
	month: string;
	year: string;
	concept: string;
	players: Player[];

	constructor(id: string = "", quantity: string = "", month: string = "", year: string = "", concept: string = "", players: Player[] = []) {
		this.id = id;
		this.quantity = quantity;
		this.month = month;
		this.year = year;
		this.concept = concept;
		this.players = players;
	}
}