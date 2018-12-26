const userReducer = (state = {
        age: 1,
        name: "Nil"
    }, action) => {
    switch(action.type) {
        case "SET_NAME":
            state = {
                ...state,
                name: action.payload
            }
            break;
    }
    return state;
}

export default userReducer;