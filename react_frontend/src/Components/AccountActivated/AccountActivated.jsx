import {Component} from 'react'


class AccountActivated extends Component {

    constructor(props) {
        super(props)
        fetch('http://localhost:8090/api/v1/registration/confirm/' + this.props.match.params.token, {
            mode: 'cors',
            method: 'GET'
        })
    }

    render() {
        return(
            <div>
                <h1> Congratulations, your account has been activated, now you can log in with no problem!</h1>
                <a href="/signin">return to signin</a>
            </div>
        )
    }
}


export default AccountActivated;