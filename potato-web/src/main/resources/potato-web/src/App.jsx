import React, {Component} from 'react';
import {Button} from 'react-bootstrap';
import logo from './logo.svg';
import './App.css';

var FontAwesome = require('react-fontawesome');

class App extends Component {
    constructor() {
        super();
        this.state = {
            isLoading: true
        };
    }
    handleClick() {
        this.setState({isLoading: true});

        // This probably where you would have an `ajax` call
        setTimeout(() => {
            // Completed of async action, set loading state back
            this.setState({isLoading: false});
        }, 2000);
    }
    render() {
        const status = this.state.isLoading
            ? 'Loading... '
            : 'Loading state ';
        return (
            <div className="App">
                <div className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h2>Welcome to React</h2>
                </div>
                <p className="App-intro">
                    To get started, edit
                    <code>src/App.js</code>
                    and save to reload.
                </p>
                <Button bsStyle="primary" onClick={() => this.handleClick()}>
                    <FontAwesome name='rocket' size='2x' spin/> {status}
                    Click
                </Button>
            </div>
        );
    }
}

export default App;
