import {call,put,takeEvery} from 'redux-saga/effects';
import axios from 'axios';

function* fetchUsers() { /* this is the worker */
	// execute the asynchronous function and wait for the response
	const users = yield call([axios, axios.get], "https://api.giphy.com/v1/gifs/search?rating=G&api_key=OeysNVGPI2ZuT9hLJJEKOabKOEZWNCki&q=hello");
	yield put({type: 'FETCH_USERS_SUCCESS', payload: users.data}); // we dispatch an action from redux-saga
}

export function* fetchUsersWatcher() { /* this is the watcher */
	yield takeEvery('FETCH_USERS', fetchUsers);
}