const mathReducer = (state = {
    total: 0,
    history: []
}, action) => {
    switch(action.type) {
        case "ADD":
            state = {
                ...state,
                total: state.total + action.payload,
                history: [...state.history, action.payload]
            }
            break;
    }
    return state;
}

export default mathReducer;
