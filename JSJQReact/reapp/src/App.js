import React, { Component } from 'react';
import axios from 'axios';
//import loading, { Loading } from './Loading'
import Loading from './Loading';
import { Grid, Row, Col, FormGroup } from 'react-bootstrap';
import PropTypes from 'prop-types';
import { sortBy } from 'lodash';
import { Search } from './search/index';
import {
  DEFAULT_QUERY, PATH_BASE, PARAM_SEARCH, SORTS
} from './constants/index';
// default parameters to fetch data from the api
// const DEFAULT_QUERY = 'react';
// const PATH_BASE = 'https://hnalgolia.com/api/v1';
// const PATH_BASE = 'https://api.randomuser.me';
// const PARAM_SEARCH = '/?nat=US&results=5';
const url = `${PATH_BASE}${PARAM_SEARCH}`;

// const SORTS = {
//   NONE: list => list,
//   LNAME: list => sortBy(list, 'name.last'),
//   EMAIL: list => sortBy(list, 'email'),
// }

function isSearch(searchTerm){
  return function(item){
    return !searchTerm || item.name.first.toLowerCase().includes(searchTerm.toLowerCase());
  }
}

// higher order component
//const withLoading = (Component) => (props) =>
const withLoading = (Component) => ({ isLoading, ...rest}) =>
isLoading ? <Loading/> : <Component {...rest} />
//props.isLoading ? <Loading/> : <Component {...props} />



//Stateful component
class App extends Component {

  constructor(props){
    super(props)
    //state
    this.state = {
      users: [],
      loading: false,
      searchItem: '',
      //sortKey: 'NONE',
      //isSortReverse: false,
    }
    //bind new function
    this.handleSubmit = this.handleSubmit.bind(this)
    this.removeUser = this.removeUser.bind(this)
    this.searchValue = this.searchValue.bind(this)
    //this.onSort = this.onSort.bind(this)
  }

  // sorting function
  // onSort(sortKey){
  //   const isSortReverse = this.state.sortKey === sortKey && !this.state.isSortReverse;
  //   this.setState({ sortKey, isSortReverse });
  // }
  setTopUsers(results){
    console.log(results);
  }
  getUsers() {
    this.setState({
      loading: true
    })

    //We can also use fetch() or XMLHttpRequest()
    //axios('https://api.randomuser.me/?nat=US&results=5').then(response => console.log(response));
    axios('https://api.randomuser.me/?nat=US&results=5')
      .then(response => this.setState({
        users: [...this.state.users, ...response.data.results],
        loading: false
      })
      );

      fetch(url)
      .then(response => response.json())
      .then(results => this.setTopUsers(results))
      .catch( e => e);

  }
  //React method. If you want to make API call before the component mounts
  componentWillMount(){
    this.getUsers();
   }

   handleSubmit(e){
     e.preventDefault();// so the browser doesn't reload
     this.getUsers();
     console.log('more users loaded')
   }

   removeUser(id){
    //  function isNotId(user){
    //    return user.id.value !== id;
    //  }
     const updatedUsers = this.state.users.filter(user=>user.id.value !== id);
     //const updatedUsers = this.state.users.filter(isNotId);
     this.setState({users: updatedUsers})
     //this.setState({users: Object.assign({}, this.state.users, {users: updatedList})})
     console.log('User removed ' + id )
   }

   searchValue(event){ 
      this.setState({ searchItem: event.target.value })
      console.log(event.target)
   }

  render() {

    //this is distructing inline
    //const { loading, users, searchItem } = this.state;
    //const { loading, users } = this.state;
    return <div className="App">

    {/* <form onSubmit={this.handleSubmit}>
    <input type="submit" value="load user" />
    </form> */}
    

    {/* <form>
      <input type="text" onChange={this.searchValue} value={this.searchItem}/>
    </form> */}

    <Grid fluid>
      <Row className="jumbotron text-center">
        <div >       
          <Col md={6} mdPush={6}>
          <Search 
            onChange={this.searchValue}
            value={this.state.searchItem}
          >Search User: </Search> 
          </Col>
          <Col md={6} mdPull={6}>
          <MoreUsers 
            onSubmit={this.handleSubmit} 
          />   
          </Col>  
        </div>
      </Row>
    </Grid>

    {/* {!loading ? users.filter(isSearch(this.state.searchItem)).map((user) => (

    <div key={user.id.value}>

    <h3 style={{color: 'blue'}}>{user.name.first} {user.id.value}</h3>
    <p>{user.email}</p>
    <button type="button" onClick={ () => this.removeUser(user.id.value)}>Remove</button>
    <hr />
    </div>
    //Using the component
    )) : (<Loading message="be patient"/>)}</div> */}

    <ShowUserss
      loading={this.state.loading}
      searchItem={this.state.searchItem}
      users={this.state.users}
      removeUser={this.removeUser}
      //sortKey={this.state.sortKey}
      //onSort={this.onSort}
      //isSortReverse={this.state.isSortReverse}
    />

    </div>
  }
}

// class Button extends Component{
//   render(){
//     const { onClick, children } = this.props
//     return(
//       <button
//       onClick={onClick}>
//       {children}
//       </button>
//     )
//   }
// }

//Stateless components
// function Button({onClick, children}){
//   return(
//     <button
//     onClick={onClick}>
//     {children}
//     </button>
//   )
// }

const Button = ({onClick, children}) => 
  <button
  onClick={onClick}>
  {children}
  </button>

  Button.propTypes = {
    onClick: PropTypes.func.isRequired,
    children: PropTypes.node.isRequired
  }

  Button.defaultProps = {
    className: ''
  }

  //Sorting button
const Sort = ({ sortKey, onSort, children, className, activeSortKey }) => {
  const sortClass = ['btn default'];

  if (sortKey === activeSortKey) {
    sortClass.push('btn btn-primary');
  }
  return (
    <Button
      className={sortClass.join(' ')}
      onClick={() => onSort(sortKey)}>
      {children}
    </Button>)
}

class ShowUserss extends Component {

  constructor(props){
    super(props)
    this.state = {
      sortKey: 'NONE',
      isSortReverse: false,
    }
    this.onSort = this.onSort.bind(this)
  }

  onSort(sortKey){
    const isSortReverse = this.state.sortKey === sortKey && !this.state.isSortReverse;
    this.setState({ sortKey, isSortReverse });
  }

  render(){
    const { loading, searchItem, users, removeUser, onSort } = this.props;
    const { sortKey, isSortReverse} = this.state

    const sortedList = SORTS[sortKey](users);
    const reversSortedList = isSortReverse ? sortedList.reverse() : sortedList
  
    return (
      <div>
  
        <div className="text-center">
          <Sort 
          className="btn btn-xs btn-default sortBtn"
          sortKey={'EMAIL'}
          onSort={ onSort }
          activeSortKey={ sortKey }
          >Sort by email</Sort>
  
          <Sort 
          className="btn btn-xs btn-default sortBtn"
          sortKey={'LNAME'}
          onSort={ onSort }
          activeSortKey={ sortKey }
          >Sort by Last Name</Sort>
  
        </div>
        {
          /*!loading ? users.filter(isSearch(searchItem)).map(user => */
          /*!loading ? SORTS[sortKey](users).filter(isSearch(searchItem)).map(user => */
            !loading ? reversSortedList.filter(isSearch(searchItem)).map(user =>
          <div key={user.id.value}>
            <h3 style={{ color: 'blue' }}>{user.name.first} {user.name.last} {user.id.value}</h3>
            <p>{user.email}</p>
            {/* <button type="button" onClick={() => removeUser(user.id.value)}>Remove</button> */}
            <Button onClick={() => removeUser(user.id.value)}>Remove</Button>
            <hr />
          </div>
          //Using the component
         ) : <Loading message="be patient" />
        }
      </div>
    )
  }
}
// const ShowUsers = ({ loading, searchItem, users, removeUser, sortKey, onSort, isSortReverse }) => {
//   const sortedList = SORTS[sortKey](users);
//   const reversSortedList = isSortReverse ? sortedList.reverse() : sortedList

//   return (
//     <div>

//       <div className="text-center">
//         <Sort 
//         className="btn btn-xs btn-default sortBtn"
//         sortKey={'EMAIL'}
//         onSort={ onSort }
//         activeSortKey={ sortKey }
//         >Sort by email</Sort>

//         <Sort 
//         className="btn btn-xs btn-default sortBtn"
//         sortKey={'LNAME'}
//         onSort={ onSort }
//         activeSortKey={ sortKey }
//         >Sort by Last Name</Sort>

//       </div>
//       {
//         /*!loading ? users.filter(isSearch(searchItem)).map(user => */
//         /*!loading ? SORTS[sortKey](users).filter(isSearch(searchItem)).map(user => */
//           !loading ? reversSortedList.filter(isSearch(searchItem)).map(user =>
//         <div key={user.id.value}>
//           <h3 style={{ color: 'blue' }}>{user.name.first} {user.name.last} {user.id.value}</h3>
//           <p>{user.email}</p>
//           {/* <button type="button" onClick={() => removeUser(user.id.value)}>Remove</button> */}
//           <Button onClick={() => removeUser(user.id.value)}>Remove</Button>
//           <hr />
//         </div>
//         //Using the component
//        ) : <Loading message="be patient" />
//       }
//     </div>
//   )
// }

ShowUserss.propTypes = {
  users: PropTypes.arrayOf(
    PropTypes.shape({
      first: PropTypes.string,
      email: PropTypes.string,
    })
  ).isRequired
}
// class ShowUsers extends Component {
//   render() {
//     const { loading, searchItem, users, removeUser } = this.props
//     return (
//       <div>
//         {
//           !loading ? users.filter(isSearch(searchItem)).map(user => 
//           <div key={user.id.value}>
//             <h3 style={{ color: 'blue' }}>{user.name.first} {user.id.value}</h3>
//             <p>{user.email}</p>
//             {/* <button type="button" onClick={() => removeUser(user.id.value)}>Remove</button> */}
//             <Button onClick={() => removeUser(user.id.value)}>Remove</Button>
//             <hr />
//           </div>
//           //Using the component
//          ) : <Loading message="be patient" />
//         }
//       </div>
//     )
//   }
// }

class MoreUsers extends Component{
  render(){
const { onSubmit } =this.props
    return(
      <div>
      <form 
        onSubmit={onSubmit}>
        <input 
        type="submit" 
        value="load user" />
      </form>
      </div>
    )
  }
}
// class Search extends Component {
//   componentDidMount(){
//     this.input.focus();
//   }
//   render() {
//     const { onChange, value, children } = this.props
//     return (
//       <form>
//         <FormGroup>
//           {children}
//           <div className="input-group text-center">
//             <input
//               className="form-control width100"
//               type="text"
//               onChange={onChange}
//               value={value}
//               ref={(node) => { this.input = node }}
//             />
//             <span className="input-group-btn">
//               <button
//                 className="btn btn-primary"
//                 type="submit"
//               >
//                 Go
//               </button>

//             </span>
//           </div>
//         </FormGroup>
//       </form>
//     )
//   }
// }
// function App() {
//   return (
//  return <div className="App">We will be back!</div>
//   );
// }

export default App;
