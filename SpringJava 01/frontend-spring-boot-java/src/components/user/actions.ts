import axios from "axios";

import {
  IAddUserSuccessAction,
  IDeleteUserSuccessAction,
  IEditUserSuccessAction,
  IGetDataSuccessAction,
} from "./types";
import { Dispatch } from "react";
import { errorMessage } from "../common/SweetAlert2/alerts";
import { UserActionType } from "./store/types";

export const GetData = () => {
  return async (dispatch: Dispatch<IGetDataSuccessAction>) => {
    try {
      const response = await axios.get("http://localhost:8080/user/");
      dispatch({
        type: UserActionType.GET_DATA,
        payload: response.data,
      });

      return Promise.resolve();
    } catch (err: any) {
      errorMessage("Error!", err);

      return Promise.reject();
    }
  };
};

export const DeleteUser = (id: any) => {
  return async (dispatch: Dispatch<IDeleteUserSuccessAction>) => {
    try {
      const response = await axios.delete(
        "http://localhost:8080/user/delete/" + id
      );
      dispatch({
        type: UserActionType.DELETE_USER,
        payload: id,
      });

      return Promise.resolve();
    } catch (err: any) {
      errorMessage("Error!", err);

      return Promise.reject();
    }
  };
};

export const AddUser = (values: any) => {
  return async (dispatch: Dispatch<IAddUserSuccessAction>) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/user/create",
        values
      );
      dispatch({
        type: UserActionType.ADD_USER,
        payload: values,
      });

      return Promise.resolve();
    } catch (err: any) {
      errorMessage("Error!", err);

      return Promise.reject();
    }
  };
};

export const EditUser = (values: any) => {
  return async (dispatch: Dispatch<IEditUserSuccessAction>) => {
    try {
      const response = await axios.put(
        "http://localhost:8080/user/update",
        values
      );
      console.log(response);
      dispatch({
        type: UserActionType.EDIT_USER,
        payload: values,
      });

      return Promise.resolve();
    } catch (err: any) {
      errorMessage("Error!", err);

      return Promise.reject();
    }
  };
};
