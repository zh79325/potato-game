import React, {Component} from 'react';
import {Button} from 'react-bootstrap';
import logo from './logo.svg';
import './App.css';

import axios from 'axios'

import Websocket from 'react-websocket';

var FontAwesome = require('react-fontawesome');

class App extends Component {
    constructor() {
        super();
        this.state = {
            isLoading: false
        };
    }
    handleClick() {
        this.setState({isLoading: true});
        var self = this;
        axios.get('getServerConfig').then(function(response) {
            self.setState({isLoading: false, websocket: response.webSocketUrl});
        }).catch(function() {
            self.setState({isLoading: false});
        })
    }

    handleData(data) {
        let result = JSON.parse(data);
        this.setState({
            count: this.state.count + result.movement
        });
    }

    render() {
        var websocket = this.state.websocket;
        if (websocket) {
            websocket = 'ws:' + websocket + '/live/product/12345/';
        }
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
                    {(this.state.isLoading) && (<FontAwesome name='spinner' spin/>)}
                    LogIn
                </Button>
                {
                 websocket &&
                  <Websocket
                  url={websocket}
                  onMessage={this.handleData.bind(this)}/>
                 }

            </div>
        );
    }
}

export default App;
