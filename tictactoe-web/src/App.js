import React, { Component } from 'react';
import axios from 'axios';

import './App.css';

class App extends Component {
  
  constructor(props) {
    super(props);
    this.state = {
      baseurl: 'http://localhost:4444', 
      displayGameInit: true,
      width:  0,
      height: 0,
      contentWidth: 0,
      gameId: "",
      showBoard: false,
      move: "",
      playerX: "",
      playerO: "",
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

    this.handleGameInit = this.handleGameInit.bind(this);
    this.handleGameMove = this.handleGameMove.bind(this);
    this.updatePlayerXInput = this.updatePlayerXInput.bind(this);
    this.updatePlayerOInput = this.updatePlayerOInput.bind(this);
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
    var self = this;
    axios.post(this.state.baseurl + '/gamestate/init', {
      playera: this.state.playerX,
      playerb: this.state.playerO,
    })
    .then(function (response) {
      if(response.data.error){
        alert(response.data.error)
        return;
      }
 
      self.setState(state => ({
        gameId: response.data.gameId,
        playerX: response.data.playerA,
        playerO: response.data.playerB,
        displayGameInit: false,
        move: response.data.move,
        a0: self.getXorO(response.data.board.board.a0),
        a1: self.getXorO(response.data.board.board.a1),
        a2: self.getXorO(response.data.board.board.a2),
        b0: self.getXorO(response.data.board.board.b0),
        b1: self.getXorO(response.data.board.board.b1),
        b2: self.getXorO(response.data.board.board.b2),
        c0: self.getXorO(response.data.board.board.c0),
        c1: self.getXorO(response.data.board.board.c1),
        c2: self.getXorO(response.data.board.board.c2),
      }));
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  getXorO(playerName){
    if(playerName === this.state.playerX){
      return "X";
    } else if(playerName === this.state.playerO){
      return "O";
    }

    return null;
  }

  handleGameMove = (position) => {
    var currentMove = this.state.playerX;
    if(this.state.move){
      currentMove = this.state.move;
    }
    var self = this;
    axios.post(this.state.baseurl + '/gamestate/move', { 
      id: this.state.gameId,
      player: currentMove,
      position: position,
    })
    .then(function (response) {
      if(response.data.error){
        alert(response.data.error);
        return;
      }

      self.setState(state => ({
        playerX: response.data.playerA,
        playerO: response.data.playerB,
        move: response.data.move,
        a0: self.getXorO(response.data.board.board.a0),
        a1: self.getXorO(response.data.board.board.a1),
        a2: self.getXorO(response.data.board.board.a2),
        b0: self.getXorO(response.data.board.board.b0),
        b1: self.getXorO(response.data.board.board.b1),
        b2: self.getXorO(response.data.board.board.b2),
        c0: self.getXorO(response.data.board.board.c0),
        c1: self.getXorO(response.data.board.board.c1),
        c2: self.getXorO(response.data.board.board.c2), 
      }));

      if(response.data.winner){ 
        if(response.data.winner === 'Tie Game!'){ 
          alert(response.data.winner); 
        }else{
          alert(response.data.winner + ' is the WINNER!'); 
        }
        return;
      }  
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  updatePlayerXInput(evt){
    let value = evt.target.value; 
    this.setState(state => ({
      playerX: value
    }));  
  }

  updatePlayerOInput(evt){
    let value = evt.target.value;
    this.setState(state => ({
      playerO: value
    })); 
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
          <div className="subTitle">Stephen Hiehn technical assignment</div>

          <div id="playerTurnMsg" className={!this.state.displayGameInit ? '' : 'hidden'}>
              Its <b>{this.state.move}</b>'s turn
          </div>

          <div id="playerNameInputPanel" className={this.state.displayGameInit ? '' : 'hidden'}>
            <span className="inputTitle">Enter player's Names:</span><br />
            <form>
              <label className="inputLabels">X: </label><input name="gender" id="xplayer" className="inputFields" onChange={this.updatePlayerXInput} /><br/>
              <label className="inputLabels">O: </label><input name="gender" id="oplayer" className="inputFields" onChange={this.updatePlayerOInput} /><br/>
            </form>
            <div className="inputButtonWrapper">
              <button onClick={this.handleGameInit} className="inputButton">
              START
            </button>
            </div>
          </div>
          
            <table id="board" className={this.props.showBoard ? 'hidden' : ''} style={{height : this.state.contentWidth, width: this.state.contentWidth}}>
              <tbody>
                <tr>
                  <td onClick={() => this.handleGameMove('a0')}>{this.state.a0}</td>
                  <td onClick={() => this.handleGameMove('a1')}>{this.state.a1}</td>
                  <td onClick={() => this.handleGameMove('a2')}>{this.state.a2}</td>
                </tr>
                <tr>
                  <td onClick={() => this.handleGameMove('b0')}>{this.state.b0}</td>
                  <td onClick={() => this.handleGameMove('b1')}>{this.state.b1}</td>
                  <td onClick={() => this.handleGameMove('b2')}>{this.state.b2}</td>
                </tr>
                <tr>
                  <td onClick={() => this.handleGameMove('c0')}>{this.state.c0}</td>
                  <td onClick={() => this.handleGameMove('c1')}>{this.state.c1}</td>
                  <td onClick={() => this.handleGameMove('c2')}>{this.state.c2}</td>
                </tr>
              </tbody>
            </table>  
        </div> 
      </div>
    );
  }
}
  
export default App;
