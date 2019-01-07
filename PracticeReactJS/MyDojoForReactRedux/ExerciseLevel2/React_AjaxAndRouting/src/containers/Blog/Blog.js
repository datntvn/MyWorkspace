import React, { Component } from 'react';

import './Blog.css';
import axios from 'axios';
import Posts from './Posts/Posts';
import {Route, NavLink, Switch, Redirect} from 'react-router-dom';
import Post from './FullPost/FullPost';
// import NewPost from './NewPost/NewPost'; // DATNT: commented out because we want to use lazy load, to reduce the size of bundle.js
import asyncComponent from '../../hoc/asyncComponent';
const AynscNewPost = asyncComponent(() => {
    return import('./NewPost/NewPost');
});


class Blog extends Component {
    
    state = {
        auth: true
    }

    render () {
        
        return (
            <div className="Blog">
                <header>
                    <nav>
                        <ul>
                            <li><NavLink 
                                to="/posts/" 
                                exact
                                activeClassName="active"
                                >Posts</NavLink></li>
                            <li><NavLink to={{
                                pathname: "/new-post",
                                hash: '#submit',
                                search: '?quick-submit=true'
                            }}>New Post</NavLink></li>
                        </ul>
                    </nav>
                </header>
                <Switch>
                    { this.state.auth? <Route path='/new-post' component={AynscNewPost}/> : null}
                    <Route path='/posts' component={Posts}/>
                    <Route render = {() => <h1>Page not found</h1> }/>
                    {/* <Redirect path='/' to="/posts"/> */}
                    {/* <Route path='/' component={Posts}/> */}
                </Switch>
                
                
                {/* <section>
                    <FullPost id={this.state.selectedPostId}/>
                </section>
                <section>
                    <NewPost /> 
                </section> */}
            </div>
        );
    }
}

export default Blog;