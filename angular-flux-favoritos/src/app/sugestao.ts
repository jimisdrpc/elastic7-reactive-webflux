export class Sugestao {
    id: string;
    name: string;
    phone: string;
    account: string;

    constructor(id: string, name: string, phone: string, account: string) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.account = account;
    }
}