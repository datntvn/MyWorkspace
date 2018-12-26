import React from "react";
import {render} from "react-dom";

import { createStore, combineReducers, applyMiddleware } from "redux";
import logger from "redux-logger";
import App from "./containers/App";
import {SagaApp} from "./containers/SagaApp";
import { Provider } from "react-redux";
// import store from "./store"; // This ons is for App.js
import axios from "axios";
import { fetchUsersWatcher } from "./sagas";
import createSagaMiddleware from 'redux-saga';

/* BEGIN for app.js */
/* 
render(
    <Provider store={store}>
        <App />
        <SagaApp />
    </Provider>
    , window.document.getElementById('app'));
*/
/* END for app.js */

/* BEGIN - ONLY FOR SAGAAPP */
let sagaMiddleware = createSagaMiddleware();


let middleware = applyMiddleware(logger, sagaMiddleware);
let initialState = {
	counter: 0,
	users: [],
	image: ""
}

function state(state = initialState, action) {
	switch(action.type) {
		case 'INCREMENT':
			return Object.assign( {}, state, {counter: state.counter + 1});
		case 'DECREMENT':
			return Object.assign( {}, state, {counter: state.counter - 1});
		case 'FETCH_USERS_SUCCESS':
			console.log("Result:: ", action.payload);
			return Object.assign( {}, state, {
				users: action.payload,
				image: action.payload.data[0]["images"]["fixed_height_still"]["url"]
			});

		default:
			return state;
	}
}

let store = createStore(state, middleware);

sagaMiddleware.run(fetchUsersWatcher);

window.store = store;

window.dispatcher = {
	dispatchIncrement: () => {
		store.dispatch({type: 'INCREMENT'});
	},
	dispatchDecrement: () => {
		store.dispatch({type: 'DECREMENT'});
	},
	fetchUsers: () => {
		store.dispatch({type: 'FETCH_USERS'});
	},

}

render(
        <SagaApp />
    , window.document.getElementById('app'));
/* END - ONLY FOR SAGAAPP */


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

