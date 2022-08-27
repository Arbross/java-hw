import { useSelector, TypedUseSelectorHook } from "react-redux";
import { RootState } from "../components/store/reducers";

export const useTypedSelector : TypedUseSelectorHook<RootState> = useSelector;