import React from 'react';
import logo from './logo.svg';
import './App.css';


export default class App extends React.Component{
  render(){
  return (
<div>
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Dropdown
        </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#">Disabled</a>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"></input>
              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>

      
    <div class="container">
      <div class="row">
        <div class="col-sm">
          One of three columns
        </div>       
        <div class="col-sm">
          One of three columns
        </div>
        <div class="col-sm">
          One of three columns
        </div>
      </div>
          <div class="row">
            <div class="col-sm">
              Two of three columns
        </div>
            <div class="col-sm">
              Two of three columns
        </div>
            <div class="col-sm">
              <p>Let's put some text here to know if the text wrappes around.
                Because it is important if you want to develop a nice website.
              </p>
        </div>
          </div>
    </div>
    <div>
        <h1>h1. Bootstrap heading</h1>
        <h2>h2. Bootstrap heading</h2>
        <h3>h3. Bootstrap heading</h3>
        <h4>h4. Bootstrap heading</h4>
        <h5>h5. Bootstrap heading</h5>
        <h6>h6. Bootstrap heading</h6>
    </div>
    <div>
        <code>&lt;section&gt;</code> should be wrapped as inline.
        <var>y</var> = <var>m</var><var>x</var> + <var>b</var><br/>
        To switch directories, type <kbd>cd</kbd> followed by the name of the directory.
        
    </div>
      <div class="text-center">
        <img src={logo} class="App-logo" alt="logo"/>
      </div>
      <div>
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">First</th>
              <th scope="col">Last</th>
              <th scope="col">Handle</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Larry</td>
              <td>the Bird</td>
              <td>@twitter</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="alert alert-primary" role="alert">
        A simple primary alert—check it out!
</div>
      <div class="alert alert-secondary" role="alert">
        A simple secondary alert—check it out!
</div>
      <div class="alert alert-success" role="alert">
        A simple success alert—check it out!
</div>
      <div class="alert alert-danger" role="alert">
        A simple danger alert—check it out!
</div>
      <div class="alert alert-warning" role="alert">
        A simple warning alert—check it out!
</div>
      <div class="alert alert-info" role="alert">
        A simple info alert—check it out!
</div>
      <div class="alert alert-light" role="alert">
        A simple light alert—check it out!
</div>
      <div class="alert alert-dark" role="alert">
        A simple dark alert—check it out!
</div>
<div>
        <span class="badge badge-primary">Primary</span>
        <span class="badge badge-secondary">Secondary</span>
        <span class="badge badge-success">Success</span>
        <span class="badge badge-danger">Danger</span>
        <span class="badge badge-warning">Warning</span>
        <span class="badge badge-info">Info</span>
        <span class="badge badge-light">Light</span>
        <span class="badge badge-dark">Dark</span>
</div>
<div>
        <button type="button" class="btn btn-primary">Primary</button>
        <button type="button" class="btn btn-secondary">Secondary</button>
        <button type="button" class="btn btn-success">Success</button>
        <button type="button" class="btn btn-danger">Danger</button>
        <button type="button" class="btn btn-warning">Warning</button>
        <button type="button" class="btn btn-info">Info</button>
        <button type="button" class="btn btn-light">Light</button>
        <button type="button" class="btn btn-dark btn-lg">Dark</button>

        <button type="button" class="btn btn-link">Link</button>
</div>
<div>
        <br />
</div>
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown button
  </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </div>
<div>
        <form>
          <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"/>
              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Password</label>
              <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
  </div>
              <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1"/>
                  <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div>
                <button type="submit" class="btn btn-primary">Submit</button>
</form>
  </div>          
    </div>
  );
  }
}
