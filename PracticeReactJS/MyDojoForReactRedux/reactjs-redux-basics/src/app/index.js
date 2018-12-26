import React from "react";
import {render} from "react-dom";

import { createStore, combineReducers, applyMiddleware } from "redux";
import logger from "redux-logger";
import App from "./containers/App";
import { Provider } from "react-redux";
import store from "./store";





render(
    <Provider store={store}>
        <App />
    </Provider>
    , window.document.getElementById('app'));



// store.dispatch({
//     type: "ADD",
//     payload: 1
// });

// store.dispatch({
//     type: "ADD",
//     payload: 1
// });

// store.dispatch({
//     type: "SET_NAME",
//     payload: "Bary"
// });

