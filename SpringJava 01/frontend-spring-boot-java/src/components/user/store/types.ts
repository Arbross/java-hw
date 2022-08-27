import { User } from './../user';

export interface UserState {
    dataTable: Array<User>;
}

export enum UserActionType {
    GET_DATA = "USER/GET_DATA",
    ADD_USER = "USER/ADD_USER",
    DELETE_USER = "USER/DELETE_USER",
    EDIT_USER = "USER/EDIT_USER",
}
