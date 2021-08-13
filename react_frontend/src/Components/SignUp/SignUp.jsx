import { Component } from 'react';
import './SignUp.css'
import 'C:\\Users\\Yacine\\Documents\\react front\\confy\\src\\index.css'


class SignUp extends Component {
    render() {
        return (
            <div className="flex-container">
                <div className="decoration">
                    {/* <svg height="500" width="500">
                        <polygon points="250,60 100,400 400,400" class="triangle" />
		                Sorry, your browser does not support inline SVG.
	                </svg> */}
                    {/* <div className="triangle triangle-2"></div>
                    <div className="triangle triangle-3"></div> */}
                    <div className="circle circle-1"></div>
                    {/* <div className="circle circle-2"></div>
                    <div className="circle circle-3"></div> */}
                    <div className="square square-1"></div>
                    <div className="square square-2"></div>
                    <a href="/" className="decoration__logo__container">
                        <img src="/logos/Groupe 1.png" className="decoration__logo__img" />
                    </a>
                    <div className="decoration__main">
                        <span>Welcome back!</span>
                        <p>Talk, create and share conferences and explore more</p>
                        <button>Sign up</button>
                    </div>
                    <div className="decoration__footer">
                        <a href="/">
                            <i className="bi bi-facebook"></i>
                        </a>
                        <a href="/">
                            <i className="bi bi-linkedin"></i>
                        </a>
                        <a href="/">
                            <i className="bi bi-instagram"></i>
                        </a>
                    </div>
                </div>
                <div className="form__main">
                    <div className="form__main__logo__container flex-container hcenter">
                        <a href="/">
                            <img src="/logos/Groupe2.png" className="form__main__logo__img" />
                        </a>
                    </div>
                    <form className="flex-container col hcenter">
                        <p>Enter your email and password to login.</p>
                        <div className="input-field">
                            <input type="email" className="input-field__text" name="email" id="email" autoComplete="off" required placeholder="Email" style={{ backgroundImage: 'url("/svgs/envelope.svg")' }} />
                        </div>
                        <div className="input-field">
                            <input type="password" className="input-field__text" name="password" id="password" required placeholder="Password" style={{ backgroundImage: 'url("/svgs/lock.svg")' }} />
                        </div>
                        <div className="forgot-password">
                            <p className="forgot-password__text">Forgot password? <a href="/">Click here</a></p>
                        </div>
                        <button type="submit">
                            <span>
                                Sign In
                            </span>
                        </button>
                    </form>
                </div>
            </div>
        )
    }
}

export default SignUp;