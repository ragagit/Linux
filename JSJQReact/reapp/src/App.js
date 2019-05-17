import React, { Component } from 'react';
import axios from 'axios'
import loading, { Loading } from './Loading'
//import Loading from './Loading'

//Stateful component
class App extends Component {

  constructor(props){
    super(props)
    //state
    this.state = {
      users: [],
      loading: false
    }
    //bin new function
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  getUsers() {
    this.setState({
      loading: true
    })
    //axios('https://api.randomuser.me/?nat=US&results=5').then(response => console.log(response));
    axios('https://api.randomuser.me/?nat=US&results=5')
      .then(response => this.setState({
        users: [...this.state.users, ...response.data.results],
        loading: false
      })
      );
  }
  //React method. If you want to make API cll before the component mounts
  componentWillMount(){
    this.getUsers();
   }

   handleSubmit(e){
     e.preventDefault();
     this.getUsers();
     console.log('more users loaded')
   }
  render() {

    //this is distructing inline
    const { loading, users } = this.state;
    return <div className="App">

    <form onSubmit={this.handleSubmit}>
    <input type="submit" value="load user" />
    </form>
     
    {!loading ? users.map((user) => (
    <div key={user.id.value}>

    <h3 style={{color: 'blue'}}>{user.name.first}</h3>
    <p>{user.email}</p>
    <hr />

   
    
    </div>
    //Using the component
    )) : (<Loading message="be patient"/>)}</div>
  }
}
// function App() {
//   return (
//  return <div className="App">We will be back!</div>
//   );
// }

export default App;
