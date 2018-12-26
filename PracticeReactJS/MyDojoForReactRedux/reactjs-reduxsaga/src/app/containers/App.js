import React from "react";
import {render} from "react-dom";

import { User } from '../components/User';
import { Main } from '../components/Main';
import {connect} from "react-redux";

class App extends React.Component {
    constructor() {
        super();
    }

    changeUsername(newName) {
    }

    render() {
        return (
            <div className="container">
                <Main changeUsername={() => this.props.changeUsername("Kate")}/>
                <User username={this.props.user.name}/>
            </div>
        );
    }
}

const bridgeStore = (state) => {
	return {
		user: state.user,
		math: state.math
	};
}

const bridgeEvent= (dispatch)=> {
	return {
		changeUsername: (name) => {
			dispatch({
				type: "SET_NAME",
				payload: name
			})
		}
	}
}

export default connect(bridgeStore, bridgeEvent) (App);