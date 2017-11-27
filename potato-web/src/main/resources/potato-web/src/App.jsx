import React, {Component} from 'react';
import {Button,FormControl,ControlLabel} from 'react-bootstrap';
import logo from './logo.svg';
import './App.css';

import axios from 'axios'

import Websocket from 'react-websocket';

var FontAwesome = require('react-fontawesome');

class App extends Component {
    constructor() {
        super();
        this.state = {
            isLoading: false,
            logs: ['等待连接']
        };
    }
    handleClick() {
        this.setState({isLoading: true});
        var self = this;

        this.addLog("请求服务器信息");
        axios.get('api/getServerConfig').then(function(response) {
            var url = response.data.websocket;
            self.addLog("请求成功=>" + url);
            self.connection = new WebSocket('ws://' + url+"?uid="+self.state.username);
            self.connection.onmessage = e => self.onMessage(e);
            self.connection.onclose = e => self.onClose(e);
            self.connection.onopen = e => self.onOpen(e);
            self.connection.onerror = e => self.onError(e);
        }).catch(function() {
            self.addLog("请求失败");
            self.setState({isLoading: false});
        })
    }

    addLog(log) {
        var logs = this.state.logs;
        logs.push(log);
        this.setState({logs: logs});
    }

    onMessage(data) {
        let result = JSON.parse(data);
        this.setState({
            count: this.state.count + result.movement
        });
    }

    onOpen(e) {
        this.addLog("websocket connected");
        this.setState({isLoading: false});
    }

    onClose(e) {
        this.addLog("websocket closed");
        this.setState({isLoading: false});
    }

    onError(e) {
        this.addLog("websocket error " + e);
        this.setState({isLoading: false});
    }

    render() {
        var logs = this.state.logs;
        const listItems = logs.map((log, i) => <li key={i}>
            {log}
        </li>);
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
                <ControlLabel>UserName</ControlLabel>
                <FormControl type="text" value={this.state.username} placeholder="Enter User Name"/>
                <Button bsStyle="primary" onClick={() => this.handleClick()}>
                    {(this.state.isLoading) && (<FontAwesome name='spinner' spin/>)}
                    LogIn
                </Button>
                <ul>
                    {listItems}
                </ul>
            </div>
        );
    }
}

export default App;
