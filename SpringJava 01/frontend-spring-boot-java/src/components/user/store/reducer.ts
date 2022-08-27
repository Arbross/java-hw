import { User } from "./../user";
import { UserActionType, UserState } from "./types";

const initialState: UserState = {
  dataTable: new Array<User>(),
};

export const userReducer = (state = initialState, action: any): UserState => {
  switch (action.type) {
    case UserActionType.GET_DATA:
      return {
        ...state,
        dataTable: action.payload,
      };
    case UserActionType.ADD_USER:
      return { ...state, dataTable: [...state.dataTable, action.payload] };
    case UserActionType.EDIT_USER:
      return {
        ...(state = {
          dataTable: state.dataTable.filter((item) => item.id !== action.payload),
        }),
      };
    case UserActionType.DELETE_USER:
      return {
        ...(state = {
          dataTable: state.dataTable.filter((item) => item.id !== action.payload.id),
        }),
      };
  }
  return state;
};
