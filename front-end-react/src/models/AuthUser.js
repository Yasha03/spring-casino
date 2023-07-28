import { observable, makeAutoObservable } from "mobx";

class AuthUser {
    email = '';
    role = '';
    balance = 0;

    constructor(data = {}) {
        makeAutoObservable(this, {
            email: observable,
            role: observable,
            balance: observable
          });
        Object.assign(this, data);
    }
}

export {AuthUser};
