import {Component} from 'react'


class ForgotPassword extends Component {

    async forgotPassword() {
        console.log('now running the forgot funct')
        window.event.preventDefault()
        let data = {email: document.getElementsByName('email')[0].value}
        let res = await fetch('http://localhost:8090/api/v1/registration/reset', {
            method: 'POST',
            mode: 'cors',
            body: JSON.stringify(data),
            headers: new Headers({'Content-Type':'application/json'})
        })
        console.log('finished fetch request')
    }

    render() {
        return(
            <form onSubmit={()=>this.forgotPassword()}>
                <h1>Enter your email</h1>
                <br/>
                <br/>
                <br/>
                <input type="email" name="email" required/>
                <br/>
                <input type="submit"/>
            </form>
        )
    }

}

export default ForgotPassword;