import React, {useState} from "react";
import {RegisterForm} from "../components/RegisterForm";
import {LoginForm} from "../components/LoginForm";

export const Login = (props) => {

    const [currentForm, setCurrentForm] = useState('login');

    const toggleForm = (formName) => {
        setCurrentForm(formName);
    }

    return (
        <div className={"login"}>

            {
                currentForm === 'login' ? <LoginForm onFormSwitch={toggleForm}/> : <RegisterForm onFormSwitch={toggleForm}/>
            }
        </div>
    )

}