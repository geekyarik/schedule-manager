import React, { Component } from "react";
import Button from "./Button";

export default class Registration extends Component {
    
    makeRequest = (username, password) => {
        // Simple POST request with a JSON body using fetch
        console.log('about to make a request');
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: JSON.stringify({ username: username, password: password })
        };
        fetch('https://localhost::8080/login', requestOptions)
            .then(response => response.json());
    }

    render() {
        return (
            <form>
                <h3>Sign Up</h3>
                <br/>
                <div className="form-group">
                    <label>First name</label>
                    <input type="text" className='form-control' placeholder="First name"  />
                </div>
                <br/>
                <div className="form-group">
                    <label>Middle name</label>
                    <input type="text" className='form-control' placeholder="Middle name" />
                </div>
                <br/>
                <div className="form-group">
                    <label>Last name</label>
                    <input type="text" className='form-control' placeholder="Last name" />
                </div>
                <br/>
                <div className="form-group">
                    <label>Email address</label>
                    <input type="email" className='form-control' placeholder="Enter email" />
                </div>
                <br/>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className='form-control'  placeholder="Enter password" />
                </div>
                <br/>   
                <Button type="submit" text='Sign Up'/>
                <br/>
                <p className="forgot-password text-right">
                    Already registered <a href="localhost::8080/login">sign in?</a>
                </p>
            </form>
        );
    }
}

