import { UserActionType } from './store/types';
import { User } from './user';

export interface IGetDataSuccessAction {
    type: UserActionType.GET_DATA,
    payload: Array<User>;
}

export interface IDeleteUserSuccessAction {
    type: UserActionType.DELETE_USER,
    payload: string;
}

export interface IEditUserSuccessAction {
    type: UserActionType.EDIT_USER,
    payload: User;
}

export interface IAddUserSuccessAction {
    type: UserActionType.ADD_USER,
    payload: User;
}
