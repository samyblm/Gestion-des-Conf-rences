import { Component } from 'react';
import anime from 'animejs'
import './SignIn.css'
import '../../index.css'
// import { countries } from 'countries-list'

class SignIn extends Component {
    constructor(props) {
        super(props)
        // let arr = [];
        // let names = [];
        // let h = []
        // for (let k of Object.keys(countries)) {
        //     names.push(countries[k].name);
        // }
        // names.sort();
        // for (let j of names) {
        //     let b = false;
        //     let n = 0;
        //     while (!b && n < Object.keys(countries).length) {
        //         if (countries[Object.keys(countries)[n]].name === j) {
        //             h.push(Object.keys(countries)[n])
        //             b = true;
        //         }
        //         else n++;
        //     }
        // }
        // for (let k of h) {
        //     if (k !== "IL") {
        //         let obj = {}
        //         obj[k] = countries[k]
        //         arr.push(obj)
        //     }
        // }
        this.submitSignUp = this.submitSignUp.bind(this)
        this.submitSignIn = this.submitSignIn.bind(this)
        this.writeCountry = this.writeCountry.bind(this)
        // this.state = {
        //     countries: arr,
        //     tags: [],
        //     currentCountry: ""
        // }
    }

    submitSignIn() {
        window.event.preventDefault();
        let formData = new FormData(window.event.target)
        const data = {
            email: formData.get('emailSignin'),
            password: formData.get('passwordSignin')
        }
        console.log(JSON.stringify(data))
        fetch("http://localhost:8090/api/v1/registration/login", {
            method: 'POST',
            body: JSON.stringify(data),
            mode: 'cors',
            headers: new Headers({ 'Content-Type': 'application/json' }),
        })
            .then(res => res.json())
            .then(json => {
                console.log('response is: ', json);
                const dataObj = json
                if (dataObj.isError === "true") {
                    let span_err = document.getElementById('first-form-err')
                    span_err.style.display = 'block'
                    if (dataObj.emailSignin === "") {
                        span_err.innerText = dataObj.passwordSignin;
                        document.getElementById('passwordSignin').value = "";
                    }
                    else {
                        span_err.innerText = dataObj.emailSignin;
                    }
                }
                else {
                    document.cookie = "token=" + json.token;
                    window.location.assign('/')
                }
            })
        // window.event.target.reset();
    }
    //---------------------------------------------------------------------------------------------
    submitSignUp() {
        console.log('now running the submit signup function');
        const trgt = window.event.target
        window.event.preventDefault();

        let fd = new FormData(trgt);

        let str = "";

        for(let s of this.state.tags) {
            str += s + "$"
        }


        let data = {
            lastName: fd.get('fullName'),
            firstName: fd.get('username'),
            location: this.state.currentCountry,
            speciality: str.slice(0, -1),
            email: fd.get('emailSignup'),
            password: fd.get('passwordSignup'),
        }
        console.log(JSON.stringify(data))
        // fetch("http://localhost:8090/api/v1/registration", {
        //     method: 'POST',
        //     body: JSON.stringify(data),
        //     mode: 'cors',
        //     headers: new Headers({ 'Content-Type': 'application/json' }),
        // })
        //     .then(res => res.json())
        //     .then(json => {
        //         trgt.reset()
        //         this.runPageChange()
        //         if (json.isError === "true") {
        //             document.getElementById('emailSignup-err').innerText = json['emailSignup']
        //             document.getElementById('username-err').innerText = json['usernameSignup']
        //         }
        //         else {
        //             window.location.assign('/verify-account')
        //         }
        //     })
        // .catch(err=>window.location.assign('/verify-email'))
    }
    //-----------------------------------------------------------------------------------------
    decorationAnimation() {
        const checkbox = document.getElementById('signUp-trigger');
        if (checkbox.checked) {
            anime({
                targets: '.decoration',
                translateX: '70vw',
                easing: 'easeInOutCubic',
                duration: 800
            })
            anime({
                targets: '.form__main',
                opacity: '0',
                duration: 300,
                easing: 'easeInOutCubic',
                complete: function (anim) {
                    document.getElementsByClassName('form__main')[0].style.display = 'none'
                }
            })
            anime({
                targets: '#signUp',
                begin: function (anim) {
                    document.getElementById('signUp').style.display = "block";
                },
                opacity: [0, 1],
                delay: 500,
                duration: 300,
                easing: 'easeInOutCubic',
            })
            anime({
                targets: '.signInText',
                opacity: [1, 0],
                complete: function (anim) {
                    document.getElementsByClassName('signInText')[0].style.display = 'none';
                    anime({
                        begin: function (anim) {
                            document.getElementsByClassName('signUpText')[0].style.display = 'block'
                        },
                        targets: '.signUpText',
                        duration: 500,
                        opacity: [0, 1],
                    })
                }
            })
        }
        else {
            anime({
                targets: '.decoration',
                translateX: '0',
                easing: 'easeInOutCubic',
                duration: 800
            })
            anime({
                begin: function (anim) {
                    document.getElementsByClassName('form__main')[0].style = "";
                },
                targets: '#signIn',
                opacity: '1',
                delay: 500,
                duration: 300,
                easing: 'easeInOutCubic',
            })
            anime({
                targets: '#signUp',
                opacity: '0',
                duration: 400,
                easing: 'easeInOutCubic',
                complete: function (anim) {
                    document.getElementById('signUp').style = "";
                },
            })
            anime({
                targets: '.signUpText',
                opacity: [1, 0],
                complete: function (anim) {
                    document.getElementsByClassName('signUpText')[0].style.display = 'none';
                    anime({
                        begin: function (anim) {
                            document.getElementsByClassName('signInText')[0].style.display = 'block'
                        },
                        targets: '.signInText',
                        duration: 500,
                        opacity: [0, 1],
                    })
                }
            })
        }
    }
    //start page change function --------------------------------------------------------------------------------------------
    runPageChange() {
        // console.log('now running the page change function!!!')
        let r1 = document.getElementById('r1');
        if (r1.checked) {
            anime({
                targets: '#p2',
                translateX: "200%",
                easing: 'easeInOutCubic',
                opacity: 0,
                duration: 500,
                complete: function (anim) {
                    document.getElementById("p2").style.display = "none"
                    document.getElementById("p1").style.display = "block"
                }
            })
            anime({
                targets: "#p1",
                translateX: "0",
                duration: 1000,
                opacity: 1,
                easing: "easeInOutCubic"
            })
            anime({
                targets: "#submitBtn__sbmt",
                opacity: 0,
                duration: 400
            })
            document.getElementById('submitBtn__sbmt').style.display = "none";
            document.getElementById('submitBtn__cnt').style.display = "inline";
            anime({
                targets: "#submitBtn__cnt",
                opacity: 1,
                delay: 100,
                duration: 400,
                complete: function (anim) {
                    document.getElementById('submitBtn').setAttribute("type", "button");
                }
            })
        }
        else {
            anime({
                targets: '#p1',
                translateX: "-100%",
                easing: 'easeInOutCubic',
                opacity: 0,
                duration: 500,
                complete: function (anim) {
                    document.getElementById("p1").style.display = "none"
                    document.getElementById("p2").style.display = "block"
                }
            })
            anime({
                targets: "#p2",
                translateX: "0%",
                duration: 1000,
                opacity: 1,
                easing: "easeInOutCubic"
            })
            anime({
                targets: "#submitBtn__cnt",
                opacity: 0,
                duration: 400
            })
            document.getElementById('submitBtn__cnt').style.display = "none";
            document.getElementById('submitBtn__sbmt').style.display = "inline";
            anime({
                targets: "#submitBtn__sbmt",
                opacity: 1,
                delay: 100,
                duration: 400,
                complete: function (anim) {
                    document.getElementById('submitBtn').setAttribute("type", "submit");
                }
            })
        }
    }
    //------------------------------------------------------------
    writeCountry(countryObject) {
        let ci = document.getElementById('country');
        ci.style.backgroundImage = `url('https://www.countryflags.io/${Object.keys(countryObject)[0].toLowerCase()}/shiny/32.png')`
        ci.value = countryObject[Object.keys(countryObject)[0]].name;
        document.getElementById('countries-list').style.display = "none";
        this.setState({currentCountry: ci.value})
    }
    //---------------------------------------------------------
    closeList() {
        if (window.event.target.id !== "countries-list" && window.event.target.id !== "country") {
            document.getElementById('countries-list').style.display = "none";
        }
    }
    //---------------------------------------------------------
    removeTag() {
        let tag = window.event.target.className === 'tags-list-item' 
        ? window.event.target : window.event.target.parentNode
        tag.remove();
    }
    //------------------------------------------------------
    handleTagAdd() {
        if (window.event.keyCode === 13 && window.event.target.value!=="") {
            this.setState((oldState) => ({
                tags: [
                    ...oldState.tags,
                    window.event.target.value.trim()
                ]
            }), () => {
                window.event.target.value = "";
            })
        }
    }
    render() {
        return (
            <div className="flex-container" onClick={() => this.closeList()}>
                <div className="decoration">
                    <div className="circle circle-1"></div>
                    <div className="square square-1"></div>
                    <div className="square square-2"></div>
                    <a href="/" className="decoration__logo__container">
                        <img src="/logos/Groupe 1.png" className="decoration__logo__img" />
                    </a>
                    <div className="decoration__main signInText">
                        <span>Welcome back!</span>
                        <p>Talk, create and share conferences and explore more</p>
                        <input type="checkbox" id="signUp-trigger" style={{ display: 'none' }} onChange={() => this.decorationAnimation()} />
                        <label htmlFor="signUp-trigger">Sign up</label>
                    </div>
                    <div className="decoration__main signUpText" style={{ display: 'none' }}>
                        <span>Join our community</span>
                        <p>Talk, create and share conferences and explore more</p>
                        <label htmlFor="signUp-trigger">Log in</label>
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
                <div className="form__main" id="signIn">
                    <div className="form__main__logo__container flex-container hcenter">
                        <a href="/">
                            <img src="/logos/Groupe2.png" className="form__main__logo__img" />
                        </a>
                    </div>
                    <form id="f1" className="flex-container col hcenter" onSubmit={() => this.submitSignIn()} onChange={() => { document.getElementById('first-form-err').innerHTML = "" }}>
                        <p>Enter your email and password to login.</p>
                        <span className="form__err" id="first-form-err" style={{ marginBottom: '20px' }}></span>
                        <div className="input-field">
                            <input type="email" className="input-field__text" name="emailSignin" id="emailSignin"
                                autoComplete="off" required placeholder="Email" style={{ backgroundImage: 'url("/svgs/envelope.svg")' }} />
                        </div>
                        <div className="input-field">
                            <input type="password" className="input-field__text" name="passwordSignin" id="passwordSignin" required placeholder="Password" style={{ backgroundImage: 'url("/svgs/lock.svg")' }} />
                        </div>
                        <div className="forgot-password">
                            <p className="forgot-password__text">Forgot password? <a href="/">Click here</a></p>
                        </div>
                        <button type="submit">
                            Sign in
                        </button>
                    </form>
                </div>
                <div className="form__main" id="signUp">
                    <div className="form__main__logo__container flex-container hcenter">
                        <a href="/">
                            <img src="/logos/Groupe2.png" className="form__main__logo__img" />
                        </a>
                    </div>
                    <form className="flex-container col hcenter" onSubmit={() => this.submitSignUp()}>
                        <p>Please fill in the form below to join us</p>
                        <div className="radios__container flex-container vcenter">
                            <input type="radio" name="page" id="r1" defaultChecked onClick={() => this.runPageChange()} />
                            <label htmlFor="r1"></label>
                            <input type="radio" name="page" id="r2" onClick={() => this.runPageChange()} />
                            <label htmlFor="r2"></label>
                        </div>
                        <div id="p1">
                            <div className="input-field">
                                <input type="text" className="input-field__text" name="fullName" id="fullName" autoComplete="off" required placeholder="Full name" style={{ backgroundImage: 'url("/svgs/person-fill.svg")' }} />
                                <span className="form__err" id="fullName-err"></span>
                            </div>
                            {/* <div className="input-field">
                                <input type="text" onFocus={() => document.getElementById('birthDate').setAttribute('type', 'date')} onBlur={() => document.getElementById('birthDate').setAttribute('type', 'text')} className="input-field__text" name="birthDate" id="birthDate" required placeholder="Date of birth" style={{ backgroundImage: 'url("/svgs/calendar2-date.svg")' }} />
                                <span className="form__err" id="birthDate-err"></span>
                            </div> */}
                            <div className="input-field">
                                <input type="text" className="input-field__text" name="username" id="username" required placeholder="Username" style={{ backgroundImage: 'url("/svgs/person.svg")' }} />
                                <span className="form__err" id="username-err"></span>
                            </div>
                            <div className="input-field">
                                <input onClick={() => document.getElementById('countries-list').style.display = "block"} readOnly type="text" className="input-field__text" name="country" id="country" required placeholder="Country" style={{ backgroundImage: 'url("https://www.countryflags.io/dz/shiny/32.png")', cursor: "pointer" }} />
                                <ul id="countries-list" style={{ display: 'none' }}>
                                    {
                                        this.state.countries.map((e, i) =>
                                            <li onClick={() => this.writeCountry(e)} key={i} style={{ backgroundImage: `url('https://www.countryflags.io/${Object.keys(e)[0].toLowerCase()}/shiny/32.png')` }}>
                                                {e[Object.keys(e)[0]].name}
                                            </li>
                                        )
                                    }
                                </ul>
                                <span className="form__err" id="birthDate-err"></span>
                            </div>
                            <div className="input-field">
                                <input autoComplete="off" multiple onKeyUp={() => this.handleTagAdd()} onClick={() => document.getElementById('countries-list').style.display = "block"} type="text" className="input-field__text" name="tags" id="tags" required={this.state.tags.length===0} placeholder="Tags" style={{ backgroundImage: 'url("svgs/tags.svg")' }} />
                                <ul id="tags-list">
                                    {this.state.tags.map((tag, i) =>
                                        <li key={i} onClick={() => this.removeTag()} className="tags-list-item">
                                            <i className="bi bi-x"></i>
                                            {tag}
                                        </li>
                                    )}
                                    {/* <li className="tags-list-item">
                                        <i className="bi bi-x"></i>
                                        mario
                                    </li>
                                    <li className="tags-list-item">
                                        <i className="bi bi-x"></i>
                                        mario
                                    </li>
                                    <li className="tags-list-item">
                                        <i className="bi bi-x"></i>
                                        mario
                                    </li> */}
                                </ul>
                                <span className="form__err" id="birthDate-err"></span>
                            </div>
                        </div>
                        <div id="p2" style={{ display: "none", transform: "translateX(200%)", opacity: 0 }}>
                            <div className="input-field">
                                <input type="email" className="input-field__text" name="emailSignup" id="emailSignup" autoComplete="off" required placeholder="Email" style={{ backgroundImage: 'url("/svgs/envelope.svg")' }} onChange={() => document.getElementById('emailSignup-err').innerText = ""} />
                                <span className="form__err" id="emailSignup-err"></span>
                            </div>
                            <div className="input-field">
                                <input type="password" className="input-field__text" name="passwordSignup" id="passwordSignup" required placeholder="Password" style={{ backgroundImage: 'url("/svgs/lock.svg")' }} minLength={8} onChange={() => document.getElementById('passwordSignup-err').innerText = ""} />
                                <span className="form__err" id="passwordSignup-err"></span>
                            </div>
                            <div className="input-field">
                                <input type="password" className="input-field__text" name="confirm-passwordSignup" id="confirm-passwordSignup" required placeholder="Confirm Password" style={{ backgroundImage: 'url("/svgs/lock-fill.svg")' }} minLength={8} onChange={() => document.getElementById('passwordSignup-err').innerText = ""} />
                                <span className="form__err" id="passwordSignup-err"></span>
                            </div>
                            {/* <div className="forgot-password">
                                <p className="forgot-password__text">Forgot password? <a href="/">Click here</a></p>
                            </div> */}
                            <p style={{ marginTop: 30, marginBottom: 10, fontSize: ".8em", textAlign: 'center' }}>By clicking on Sign up, you agree to out terms and conditions</p>
                            {/* <button type="submit">
                                This is page 2
                            </button> */}
                        </div>
                        <button id="submitBtn" type="button" style={{ position: "absolute", bottom: -80 }} onClick={() => { document.getElementById("r2").checked = true; this.runPageChange() }}>
                            <span id="submitBtn__cnt" style={{ opacity: 1, transition: 'opacity 100ms linear' }}>Continue</span>
                            <span id="submitBtn__sbmt" style={{ opacity: 0, display: 'none', transition: 'opacity 100ms linear' }}>Submit</span>
                        </button>
                    </form>
                </div>
            </div>
        )
    }
}

export default SignIn;
