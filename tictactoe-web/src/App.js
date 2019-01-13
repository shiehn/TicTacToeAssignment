import React, { Component } from 'react';
import axios from 'axios';

import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      displayGameInit: true,
      width:  0,
      height: 0,
      contentWidth: 0,
      showBoard: false,
      a0: null,
      a1: null,
      a2: null,
      b0: null,
      b1: null,
      b2: null,
      c0: null,
      c1: null,
      c2: null,
    }; 
    // This binding is necessary to make `this` work in the callback
    this.handleGameInit = this.handleGameInit.bind(this);
  }

  updateDimensions() { 
      let update_width  = window.innerWidth;
      let update_height = window.innerHeight;
      let update_content_width = window.innerWidth * 0.7;
      if(update_content_width > 700){
        update_content_width = 700;
      }
      this.setState({ width: update_width, height: update_height, contentWidth: update_content_width });
  }

  handleGameInit() {
    this.setState(state => ({
      displayGameInit: !state.displayGameInit
    }));
asdfasdf
    var self = this;
    axios.post('http://localhost:4444/gamestate/init', {
      playera: 'Fred',
      playerb: 'Flintstone'
    })
    .then(function (response) {
      self.setState(state => ({
        a0: response.data.board.board.a0
      }));
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  componentDidMount() {
    this.updateDimensions();
    window.addEventListener("resize", this.updateDimensions.bind(this));
  }
 
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions.bind(this));
  }

  render() {
    return (
      <div className="page-wrapper" style={{height : this.state.height}}>
        <div className="content" style={{height : this.state.height, width: this.state.contentWidth}}> 
          <h1 className="title">Tic Tac Toe</h1> 

          <div id="playerNameInputPanel" className={this.state.displayGameInit ? '' : 'hidden'}>
            <span className="inputTitle">Enter player's Names:</span><br />
            <label className="inputLabels">X: </label><input name="gender" id="xplayer" className="inputFields" /><br/>
            <label className="inputLabels">O: </label><input name="gender" id="oplayer" className="inputFields"/><br/>
            <div className="inputButtonWrapper">
              <button onClick={this.handleGameInit} className="inputButton">
              START
            </button>
            </div>
          </div>
          
            <table id="board" className={this.props.showBoard ? 'hidden' : ''} style={{height : this.state.contentWidth, width: this.state.contentWidth}}>
              <tbody>
                <tr>
                  <td>{this.state.a0}</td>
                  <td>{this.state.a1}</td>
                  <td>{this.state.a2}</td>
                </tr>
                <tr>
                  <td>{this.state.b0}</td>
                  <td>{this.state.b1}</td>
                  <td>{this.state.b2}</td>
                </tr>
                <tr>
                  <td>{this.state.c0}</td>
                  <td>{this.state.c1}</td>
                  <td>{this.state.c2}</td>
                </tr>
              </tbody>
            </table> 

        </div> 
      </div>
    );
  }
}
 
//http://54.188.130.104:4444/gamestate/15878913-77db-479f-b5af-d89e56c70414
export default App;
