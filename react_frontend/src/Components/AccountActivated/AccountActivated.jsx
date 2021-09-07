import {Component} from 'react'
import './AccountActivated.css'

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
            <div className="ac-container">
                <img src="/images/account activated.png" className="ac-img" />
                <h2>Your email has been confirmed</h2>
                <a className="ac-link" href="/signin">Log in</a>
                <div className="ac-footer">
                    <a href="/">
                        <img src="/logos/Groupe2.png" alt="" className="ac-footer-logo" />
                    </a>
                    <span>Copyright Confy&copy; 2021, plate-forme et toutes ses actions, tout droit réservé </span>
                </div>
            </div>
        )
    }
}


export default AccountActivated;