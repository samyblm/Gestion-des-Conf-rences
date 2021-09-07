import { Component } from "react";
import './ConfirmMail.css'

class ConfirmMail extends Component {
    render() {
        return(
            <div className="cm-container">
                <img src="/images/confirm mail.png" className="cm-img" />
                <h2 className="cm-title">Thanks for joining us</h2>
                <div className="cm-paragraph-container">
                    <p className="cm-paragraph">We have sent you a confirmation link to your personal email address: <u>yacinejnano@gmail.com</u></p>
                    <span className="cm-paragraph">Enter the link to confirm your email</span>
                    <span className="cm-paragraph">If you haven't received a message, please click <b>here</b> to resend</span>
                </div>
                <div className="cm-footer">
                    <a href="/">
                        <img src="/logos/Groupe2.png" className="cm-footer-logo" />
                    </a>
                    <span>Copyright Confy&copy; 2021, plate-forme et toutes ses actions, tout droit réservé </span>
                </div>
            </div>
        )
    }
}


export default ConfirmMail;