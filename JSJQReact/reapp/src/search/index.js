import React, { Component } from 'react'
import { Grid, Row, Col, FormGroup } from 'react-bootstrap';


export class Search extends Component {
    componentDidMount(){
      this.input.focus();
    }
    render() {
      const { onChange, value, children } = this.props
      return (
        <form>
          <FormGroup>
            {children}
            <div className="input-group text-center">
              <input
                className="form-control width100"
                type="text"
                onChange={onChange}
                value={value}
                ref={(node) => { this.input = node }}
              />
              <span className="input-group-btn">
                <button
                  className="btn btn-primary"
                  type="submit"
                >
                  Go
                </button>
  
              </span>
            </div>
          </FormGroup>
        </form>
      )
    }
  }

  