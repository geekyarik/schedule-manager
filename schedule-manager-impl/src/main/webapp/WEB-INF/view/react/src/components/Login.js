import React, { useState } from "react";
import Button from "./Button";
import axios from 'axios'

const Login = () => {

    const [usname, setUsname] = useState('');
    const [passwd, setPasswd] = useState('');

    const makeRequest = (username, password) => {
        const requestBody = {
            username: username, password: password
        };
        const requestHeaders = {
            'Content-Type': 'application/json'
        };
        console.log('about to make a request');
        axios.post('http://localhost:8080/auth', requestBody, {requestHeaders})
            .then(response => {
                console.log(response.data);
            })
    }

    return (
        <form>
            <h3>Sign In</h3>
            <br/>
            <div className="form-group">
                <label>Email address</label>
                <input type="email" className="form-control" placeholder="Enter email" onChange={event => setUsname(event.target.value)} />
            </div>
            <br/>
            <div className="form-group">
                <label>Password</label>
                <input type="password" className="form-control" placeholder="Enter password" onChange={event => setPasswd(event.target.value)} />
            </div>
            <br/>
            <div className="form-group">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="customCheck1" />
                    <label className="custom-control-label" htmlFor="customCheck1"> Remember me</label>
                </div>
            </div>
            <br/>
            <Button text='Sign In' type='submit' onClick={makeRequest(usname, passwd)}/>
        </form>
    )
}

export default Login