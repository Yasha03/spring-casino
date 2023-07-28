import { makeAutoObservable } from "mobx";

class AuthResponse {
    email = '';
    token = '';

    constructor(data = {}) {
        makeAutoObservable(this);
        Object.assign(this, data);
    }
}

export {AuthResponse};
