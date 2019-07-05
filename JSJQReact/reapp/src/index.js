import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter as Router, Route, Link, NavLink} from 'react-router-dom'
import { Navbar, Nav, NavItem } from 'react-bootstrap'

const Root = () => 
    <Router>
        <div>
            <Navbar>
                <Navbar.Header>
                    <Navbar.Brand>
                        <NavLink to="/">Users</NavLink>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                <Nav>
                    <NavItem>
                        <NavLink to="/" activeClassName="active">Home</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink to="/about" activeClassName="active">About</NavLink>
                    </NavItem>
                </Nav>
                </Navbar.Collapse>
            </Navbar>
            <Route exact path="/" component={App} />
            <Route exact path="/about" component={About} />
        </div>
    </Router>

const About = () => 
<div>
    <h1>This is about page</h1>
</div>

// We can rite a compnent anywhere
// const msg = "Hi there!" //this can't be changed
// const para = {
//     text: "Howdy"
// }
// class Hello extends React.Component{
//     render(){
//         para.newMsg = "New Message added"
//         return(
//             <div>
//             <h1>{msg}</h1>
//             <h1>{para.text}</h1>
//             </div>
//         )
//     }
// }
ReactDOM.render(<Root />, document.getElementById('root'));
//ReactDOM.render(<App />, document.getElementById('root'));
//ReactDOM.render(<Hello />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
