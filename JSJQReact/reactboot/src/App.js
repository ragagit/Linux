import React from 'react';
import logo from './logo.svg';
import './App.css';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Form from 'react-bootstrap/Form';
import FormControl from 'react-bootstrap/FormControl';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Button from 'react-bootstrap/Button'
import Alert from 'react-bootstrap/Alert'

export default class App extends React.Component {
  constructor(props){
    super(props)
    this.state = {
    styles : [
    'primary',
    'secondary',
    'success',
    'danger',
    'warning',
    'info',
    'light',
    'dark',
    ]
  }
}
  render(){
  return (
    <div className="App">
      <Navbar bg="dark" variant="dark">
        <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#link">Link</Nav.Link>
            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Form inline>
            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Navbar>  
    <div>
      <Alert variant='primary'>
          This is an alert—check it out!
      </Alert>
    </div>
        <MyStyles styles={this.state.styles}></MyStyles>
    <div>

    </div>
      
      </div>
  );
  }
}

class MyStyles extends React.Component{
  constructor(props) {
    super(props)
  }
  render(){
    const { styles } = this.props;  
    return(
      styles.map((variant, idx) => (
        <Alert key={idx} variant={variant}>
          This is a {variant} alert—check it out!
        </Alert>
      ))   
    );
  }
}
