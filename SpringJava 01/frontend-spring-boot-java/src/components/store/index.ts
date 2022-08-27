import { rootReducer } from './reducers';
import { configureStore } from '@reduxjs/toolkit'

export const store = configureStore({
    middleware: (getDefaultMiddleware) => getDefaultMiddleware(),
    reducer: rootReducer,
    devTools: true,
});
