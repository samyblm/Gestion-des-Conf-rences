import { Component } from "react";
import Conference from '../Conference/Conference'
import CountriesInput from "../CountriesInput/CountriesInput";
import Footer from "../footer/footer";
import MyNavbar from '../Navbar/navbar'
import ReviewerInvitation from "../ReviewerInvitation/ReviewerInvitation";
import './UserPage.css'
import $clamp from 'clamp-js'
import * as JsSearch from 'js-search'
import MiniConference from '../MiniConference/MiniConference';
import {isLoggedIn, getCookieValue} from "../../Functions"
import ReactLoading from 'react-loading'

class UserPage extends Component {

    constructor(props) {
        super(props);
        this.editContactInfo = this.editContactInfo.bind(this);
        this.cancelContactEdit = this.cancelContactEdit.bind(this);
        this.confirmContactEdit = this.confirmContactEdit.bind(this);
        this.editMainInfo = this.editMainInfo.bind(this);
        this.confirmMainInfoEdit = this.confirmMainInfoEdit.bind(this);
        this.removeKeywordFromInput = this.removeKeywordFromInput.bind(this);
        this.updateTabIndex = this.updateTabIndex.bind(this);
        this.submitSecondForm = this.submitSecondForm.bind(this);
        this.getCountry = this.getCountry.bind(this);
        this.searchResult = this.searchResult.bind(this);
        this.addInvitedToList = this.addInvitedToList.bind(this);
        this.removeInvitedFromList = this.removeInvitedFromList.bind(this);
        this.state = {
            displayMainInfo: true,
            formTabIndex: 1,
            userData: null,
            experience: [
                {
                    company: 'Lorem ipsum',
                    from: '2017',
                    to: '2019',
                    job: "data scientist"
                },
                {
                    company: 'Lorem ipsum dolor sit amet',
                    from: '2014',
                    to: '2017',
                    job: "data scientist"
                },
                {
                    company: 'Lorem ipsum',
                    from: '2019',
                    to: '2020',
                    job: "data scientist"
                },
            ],
            competences: [
                'Machine learning',
                "Securité",
                "Mathématique",
            ],
            contact: {
                phone: "+213 (0) 541 75 67 88",
                address: "Cité ben badis 1000 logements Alger centre, Algérie",
                email: "johndoe@gmail.com"
            },
            addConferenceData: {
                keyWords: []
            },
            users: [
                {
                    id: 5,
                    username: 'sonic',
                    job: "administrateur en securite informatique rjgoignfgndflgdnfglfdngldnfgldfnglkn"
                },
                {
                    id: 53,
                    username: 'luigi',
                    job: "developpeur des jeux videos"
                },
                {
                    id: 574,
                    username: 'mario',
                    job: "Ingenieur ReactJs"
                },
                {
                    id: 598,
                    username: 'peach',
                    job: "Data scientist"
                },
                {
                    id: 572,
                    username: 'daisy',
                    job: "Data analysist"
                },
                {
                    id: 500,
                    username: 'wario',
                    job: "Acteur en films de drama"
                },
                {
                    username: 'wario mario',
                    job: "Enemi de mario"
                },
                {
                    id: 525,
                    username: 'wario luigi mario sonic',
                    job: "Je ne sais pas son travail"
                },
                {
                    id: 5,
                    username: 'sonic',
                    job: "administrateur en securite informatique rjgoignfgndflgdnfglfdngldnfgldfnglkn"
                },
                {
                    id: 53,
                    username: 'luigi',
                    job: "developpeur des jeux videos"
                },
                {
                    id: 574,
                    username: 'mario',
                    job: "Ingenieur ReactJs"
                },
                {
                    id: 598,
                    username: 'peach',
                    job: "Data scientist"
                },
                {
                    id: 572,
                    username: 'daisy',
                    job: "Data analysist"
                },
                {
                    id: 500,
                    username: 'wario',
                    job: "Acteur en films de drama"
                },
                {
                    username: 'wario mario',
                    job: "Enemi de mario"
                },
                {
                    id: 525,
                    username: 'wario luigi mario sonic',
                    job: "Je ne sais pas son travail"
                },
            ],
            invited: [],
            searchResult: [],
            editableContact: false,
            editableMainData: false,
            username: "John Doe",
            currentJob: 'Administrateur de base de données Oracle',
            showOldConferences: true,
            addConferenceDoneForms: [1]
        }
    }

    async componentDidMount() {
        if(isLoggedIn()) {
            let token = getCookieValue('token');
            console.log('token: ', token);
            // let res = await fetch('http://localhost:8090/api/v1/Appuser/getUser', {
            //     method: 'GET',
            //     mode: 'cors',
            //     headers: new Headers({'authorization' : "Bearer " + token})
            // })
            let res = await fetch('http://localhost:8090/api/v1/Appuser/getUser', {
                method: 'GET',
                mode: 'cors',
                headers: new Headers({'authorization' : "Bearer " + token})
            })
            let json = await res.json()
            console.log(json);
            let search = new JsSearch.Search('id')
            search.addIndex('username')
            search.addIndex('job')
            search.addDocuments([...this.state.users])
            this.setState({search, userData: json})
            document.getElementById('keyWords').style.paddingLeft = (document.getElementById('keywords-list').offsetWidth + (this.state.addConferenceData.keyWords.length === 0 ? 10 : 15)) + "px"
            for (let k of document.getElementsByClassName('ri-userJob')) {
                $clamp(k, { clamp: 2 })
            }
        }
        else {
            window.location.assign("/signin")
        }
    }

    componentDidUpdate() {
        document.getElementById('keyWords').style.paddingLeft = (document.getElementById('keywords-list').offsetWidth + (this.state.addConferenceData.keyWords.length === 0 ? 10 : 15)) + "px"
    }

    updateKeyWordsInputPadding() {
        document.getElementById('keyWords').style.paddingLeft = (document.getElementById('keywords-list').offsetWidth + (this.state.addConferenceData.keyWords.length === 0 ? 10 : 15)) + "px"
    }

    removeCompetence() {
        let arr = this.state.competences.filter(value => value !== window.event.target.parentElement.previousSibling.innerText)
        this.setState({
            competences: arr
        })
    }

    removeExperience() {
        let li = window.event.target.parentElement.parentElement;
        let obj = {
            company: li.children[0].innerText,
            from: li.children[1].innerText.split(' - ')[0],
            to: li.children[1].innerText.split(' - ')[1],
            job: li.children[2].innerText
        }
        let arr = this.state.experience.filter(e => JSON.stringify(e) !== JSON.stringify(obj));
        this.setState({ experience: arr })
    }


    showCompetenceInput() {
        window.event.target.previousSibling.lastChild.style.display = "";
        window.event.target.style.display = "none"
    }


    confirmAdd() {
        if (window.event.target.parentElement.previousSibling.value !== "") {
            this.setState((oldState) => ({
                competences: [...oldState.competences, window.event.target.parentElement.previousSibling.value]
            }), function () {
                window.event.target.parentElement.previousSibling.value = "";
                window.event.target.parentElement.parentElement.style.display = 'none';
                document.getElementById('addCompetence').style.display = "block"
            })
        }
    }

    cancelAdd() {
        window.event.target.parentElement.previousSibling.previousSibling.value = "";
        window.event.target.parentElement.parentElement.style.display = 'none';
        document.getElementById("addCompetence").style.display = "block"
    }

    showExperienceForm() {
        document.getElementsByTagName('form')[0].parentElement.style.display = "block";
        window.event.target.style.display = "none"
    }

    submitExperienceForm(boolean) {
        window.event.preventDefault()
        if (boolean) {
            let obj = {
                company: document.getElementById('companyName').value,
                from: document.getElementById('startYear').value,
                to: document.getElementById('endYear').value,
                job: document.getElementById('jobName').value
            }
            this.setState((oldState) => ({ experience: [...oldState.experience, obj] }))
        }
        document.getElementsByTagName('form')[0].reset();
        document.getElementsByTagName('form')[0].parentElement.style.display = "none"
        document.getElementById('addExperienceBtn').style.display = "block"
    }

    editContactInfo() {
        window.event.target.style.display = 'none'
        this.setState({
            editableContact: true
        })
    }

    cancelContactEdit() {
        this.setState({ editableContact: false });
        for (let k of Object.keys(this.state.contact)) {
            document.getElementsByClassName(k)[0].value = this.state.contact[k];
        }
    }

    confirmContactEdit() {
        window.event.preventDefault();
        let obj = {}
        for (let k of Object.keys(this.state.contact)) {
            obj[k] = document.getElementsByClassName(k)[0].value;
        }
        this.setState({ contact: { ...obj }, editableContact: false })
    }

    editMainInfo() {
        window.event.target.style.display = "none"
        this.setState({ editableMainData: true });
    }

    confirmMainInfoEdit() {
        this.setState({
            username: document.getElementById('username-input').value,
            currentJob: document.getElementById('currentJob-input').value,
            editableMainData: false
        })
    }

    cancelMainInfoEdit() {
        document.getElementById('username-input').value = this.state.username;
        document.getElementById('currentJob-input').value = this.state.currentJob;
        this.setState({ editableMainData: false });
    }

    removeKeywordFromInput() {
        let keyword;
        if (window.event.target.localName === "li") {
            keyword = window.event.target.innerText;
        }
        else {
            keyword = window.event.target.parentElement.innerText
        }
        let arr = this.state.addConferenceData.keyWords.filter(e => e !== keyword);
        this.setState(oldstate => ({ addConferenceData: { ...oldstate.addConferenceData, keyWords: arr } }))
    }

    addKeywordToInput() {
        let keyword = window.event.target.value.trim();
        if ((window.event.keyCode === 13 || window.event.type === "focusout") && keyword.length > 0) {
            this.setState(oldState => ({ addConferenceData: { keyWords: [...oldState.addConferenceData.keyWords, keyword] } }),
                function () {
                    window.event.target.value = "";
                }
            )
        }
    }

    updateTabIndex(index) {
        this.setState({ formTabIndex: index })
    }

    submitFirstForm() {
        window.event.preventDefault()
        //run some fetch etc
        this.setState((oldState) => ({ formTabIndex: 2, addConferenceDoneForms: [...oldState.addConferenceDoneForms, 2] }))
    }
    submitSecondForm() {
        window.event.preventDefault()
        //run some fetch etc
        this.setState((oldState) => ({ formTabIndex: 3, addConferenceDoneForms: [...oldState.addConferenceDoneForms, 3] }))
    }

    disableSubmitOnEnterKey() {
        if (window.event.keyCode === 13) window.event.preventDefault()
    }

    getCountry(c) {
        this.setState(oldState => ({ ...oldState, addConferenceData: { ...oldState.addConferenceData, country: c } }))
    }

    searchResult() {
        let arr = this.state.search.search(window.event.target.value);
        this.setState({searchResult: arr})
    }

    addInvitedToList(user) {
        this.setState((oldState)=>({invited: [...oldState.invited, user]}))
    }

    removeInvitedFromList(user) {
        let arr = this.state.invited.filter(e=>e.id !== user.id);
        this.setState({invited: arr})
    }
    
    cancelAddConference() {
        for(let f of document.getElementsByClassName('ac')) {
            f.reset()
        }
        document.getElementById('searchInvitations').value = "";
        this.setState({formTabIndex: 1, displayMainInfo: true, searchResult: []})
    }

    // checkConferenceFormValidity() {
    //     let b = true;
    //     for(let i of document.getElementsByClassName('addConference-input')) {
    //         b = (b && i.value.length!==0)
    //     }
    //     b = b && (this.state.addConferenceData.keyWords.length !== 0) && (document.getElementById('country').value.length!==0);
    //     return b;
    // }

    render() {
        console.log(this.state);
        let loading = <div style={{width: '100vw', height: '100vh'}}>
            <ReactLoading/>
        </div>
        let userInfo = this.state.userData===null ? loading : <div style={{ display: this.state.displayMainInfo ? "block" : "none" }}>
            <div className="up-right-r2">
                <span className="up-right-r2-title">À propos</span>
                <hr />
                <i onClick={() => this.editContactInfo()} className="bi bi-pencil-square" style={{ display: this.state.editableContact ? "none" : "block" }}></i>
                <form id="contact-form" className="up-right-r2-s1">
                    <i className="bi bi-diamond-fill"></i>
                    <span className="up-right-r2-s1-title">Informations de contact</span>
                    <div className="editable-data">
                        <span className="editable-data-title">Telephone</span>
                        <input required disabled={!this.state.editableContact} type="tel" className="editable-data-input phone" defaultValue={this.state.userData.Telf.length===0 ? "Vide" : this.state.userData.Telf} />
                    </div>
                    <div className="editable-data">
                        <span className="editable-data-title">Adresse</span>
                        <input required disabled={!this.state.editableContact} type="text" className="editable-data-input address" defaultValue={this.state.contact.address} />
                    </div>
                    <div className="editable-data">
                        <span className="editable-data-title">Email</span>
                        <input required disabled={!this.state.editableContact} type="email" className="editable-data-input email" defaultValue={this.state.userData.Email} />
                    </div>
                    <div className="s1-buttons" style={{ display: this.state.editableContact ? "block" : "none" }}>
                        <button type="submit" className="save-btn">
                            Enregistrer
                        </button>
                        <button type="button" className="cancel-btn" onClick={() => this.cancelContactEdit()}>
                            Annuler
                        </button>
                    </div>
                </form>
                <div className="up-right-r2-s2">
                    <i className="bi bi-diamond-fill"></i>
                    <span className="up-right-r2-s2-title">Information de base</span>
                    <div className="data">
                        <span className="data-title">Date de naissance</span>
                        <span className="data-info">{this.state.userData['Date de naissance'].length === 0 ? "Vide" : this.state.userData['Date de naissance']}</span>
                    </div>
                    <div className="data">
                        <span className="data-title">Sexe</span>
                        <span className="data-info">{this.state.userData.Sexe==="M" ? "Mâle" : "Femelle"}</span>
                    </div>
                </div>
            </div>
            <div className="up-right-r3">
                <span className="up-right-r3-title">Conférences</span>
                <hr />
                <button onClick={() => this.setState({ displayMainInfo: false })} className="add-btn">Créer</button>
                <div className="conferences">
                   <MiniConference/>
                </div>
            </div>
            <div className="up-right-r4">
                <span className="up-right-r3-title">Articles</span>
                <hr />
                <button className="add-btn">Créer</button>
                <div className="articles">
                    Rien à afficher
                </div>
            </div>
        </div>
        return (
            this.state.userData===null ? loading : <div>
                <MyNavbar />
                <div className="up-container">
                    <div className="up-left-container">
                        <div className="up-left-image-container">
                            <img src="https://i.pravatar.cc/400?img=68" />
                            <div className="up-left-image-placeholder">
                                Changer
                            </div>
                        </div>
                        <div className="up-left-experience">
                            <div className="up-left-experience-title">
                                <hr />
                                <span>Experience</span>
                            </div>
                            {this.state.userData.Experience.length===0 ? <span>Vide</span> : 
                                <ul className="up-left-experience-list">
                                    {
                                        this.state.userData.Experience.map((e, i) =>
                                        <li key={i} className="up-left-experience-list-item">
                                                <h6>{e.company}</h6>
                                                <span className="period">{e.from} - {e.to}</span>
                                                <span className="job">{e.job}</span>
                                                <button onClick={() => this.removeExperience()}>
                                                    <i className="bi bi-x"></i>
                                                </button>
                                            </li>
                                        )
                                    }
                                    <li className="up-left-experience-list-item" style={{ display: 'none' }}>
                                        <form style={{ display: this.state.formTabIndex === 1 ? "block" : "none" }} onSubmit={() => this.submitExperienceForm(true)}>
                                            <input id="companyName" required type="text" placeholder="Nom de l'entreprise" />
                                            <input id="startYear" min={1970} max={new Date().getFullYear()} required type="number" placeholder="année début" />
                                            <input id="endYear" min={1970} max={new Date().getFullYear()} required type="number" placeholder="année fin" />
                                            <input id="jobName" required type="text" placeholder="Travail" />
                                            <button id="submitExperience" type="submit">
                                                Confirmer
                                            </button>
                                            <button onClick={() => this.submitExperienceForm()} id="cancelSubmitExperience">
                                                Annuler
                                            </button>
                                        </form>
                                    </li>
                                </ul>
                            }
                            <button id="addExperienceBtn" onClick={() => this.showExperienceForm()} className="up-left-experience-add">Ajouter expérience</button>
                        </div>
                        <div className="up-left-competences">
                            <div className="up-left-competences-title">
                                <hr />
                                <span>Compétences</span>
                            </div>
                            {this.state.userData["Compétences"].length===0 ? <span>Vide</span> :
                                <ul className="up-left-competences-list">
                                    {
                                        this.state.userData['Compétences'].map((eachCompetence, index) =>
                                        <li key={index} className="up-left-competences-list-item">
                                                <span>{eachCompetence}</span>
                                                <button onClick={() => this.removeCompetence()}>
                                                    <i className="bi bi-x"></i>
                                                </button>
                                            </li>
                                        )
                                    }
                                    <li style={{ display: 'none' }} className="up-left-competences-list-item">
                                        <input type='text' id="newCompetence" />
                                        <button id="confirmAdd" onClick={() => this.confirmAdd()}>
                                            <i className='bi bi-check'></i>
                                        </button>
                                        <button onClick={() => this.cancelAdd()}>
                                            <i className="bi bi-x"></i>
                                        </button>
                                    </li>
                                </ul>
                            }
                            <button id="addCompetence" onClick={() => this.showCompetenceInput()} className="up-left-competences-add">Ajouter compétence</button>
                        </div>
                    </div>
                    <div className="up-right-container">
                        <div className="up-right-r1">
                            <input id="username-input" disabled={!this.state.editableMainData} type="text" size={this.state.userData.userName.length - 2} className="up-right-username" defaultValue={this.state.userData.userName} />
                            <h3 style={{ display: this.state.displayMainInfo ? "none" : "inline-block" }}>Créer conference</h3>
                            <span className="up-right-location">
                                <i className="bi bi-geo-alt-fill"></i>{this.state.userData.Location}
                            </span>
                            <i onClick={() => this.editMainInfo()} className="bi bi-pencil-square" style={{ display: (this.state.editableMainData || !this.state.displayMainInfo) ? "none" : "block" }}></i>
                            <div className="up-right-job">
                                <i className="bi bi-link"></i>
                                <input id="currentJob-input" disabled={!this.state.editableMainData} type="text" defaultValue={this.state.userData.Job} size={this.state.userData.Job.length - 2} />
                            </div>
                            <div className="up-right-r1-buttons" style={{ display: this.state.editableMainData ? "block" : "none" }}>
                                <button onClick={() => this.confirmMainInfoEdit()} className="confirm-btn">
                                    Enregistrer
                                </button>
                                <button onClick={() => this.cancelMainInfoEdit()} className="cancel-btn">
                                    Annuler
                                </button>
                            </div>
                        </div>
                        {userInfo}
                        <div className="addConference-container" style={{display: this.state.displayMainInfo ? 'none' : 'block'}}>
                            <div className="addConference-sideInfos">
                                <button onClick={()=>this.cancelAddConference()}>Annuler</button>
                                {/* <span className="addConference-status incomplete">incomplet</span> */}
                            </div>
                            <ul className="addConference-tabs">
                                <li onClick={() => this.updateTabIndex(1)} className={"addConference-tabs-item" + (this.state.formTabIndex === 1 ? " selected" : "") + (this.state.addConferenceDoneForms.includes(1) ? "" : " disabled")}>Informations de base</li>
                                <li onClick={() => this.updateTabIndex(2)} className={"addConference-tabs-item" + (this.state.formTabIndex === 2 ? " selected" : "") + (this.state.addConferenceDoneForms.includes(2) ? "" : " disabled")}>Informations avancées</li>
                                <li onClick={() => this.updateTabIndex(3)} className={"addConference-tabs-item" + (this.state.formTabIndex === 3 ? " selected" : "") + (this.state.addConferenceDoneForms.includes(3) ? "" : " disabled")}>Ajouter les reviewers</li>
                                <hr />
                            </ul>
                            <div className="addConference-forms">
                                <form onKeyDown={() => this.disableSubmitOnEnterKey()} style={{ display: this.state.formTabIndex === 1 ? "block" : "none" }} onSubmit={() => this.confirmContactEdit()} className="f1 ac" onSubmit={() => this.submitFirstForm()}>
                                    <div className="f1-input-field">
                                        <label for="confName">Nom de conférence</label>
                                        <input className="addConference-input" required placeholder="Importance du vaccin contre le covid-19" type='text' id="confName" name="confName" />
                                    </div>
                                    <div className="f1-input-field">
                                        <label for="keyWords">Mots clés(Maximum 5)</label>
                                        <input onBlur={() => this.addKeywordToInput()} required={this.state.addConferenceData.keyWords.length === 0} onKeyUp={() => this.addKeywordToInput()} placeholder="covid-19, vaccins, virus" disabled={this.state.addConferenceData.keyWords.length >= 5} type='text' id="keyWords" name="keyWords" />
                                        <ul className="keywords-list" id="keywords-list">
                                            {
                                                this.state.addConferenceData.keyWords.map((e, i) =>
                                                    <li onClick={() => this.removeKeywordFromInput()} key={i}>
                                                        {e}
                                                        <i onClick={() => this.removeKeywordFromInput()} className="bi bi-x"></i>
                                                    </li>
                                                )
                                            }
                                        </ul>
                                    </div>
                                    <div className="f1-input-field">
                                        <label for="confType">Type</label>
                                        <input className="addConference-input" required placeholder="Science" type='text' id="confType" name="confType" />
                                    </div>
                                    <div className="f1-input-field">
                                        <label for="description">Description</label>
                                        <textarea className="addConference-input" required placeholder="Description de votre conference" rows={10} type='text' id="description" name="description" />
                                    </div>
                                    <button type="submit">
                                        Suivant
                                        <i className="bi bi-arrow-right"></i>
                                    </button>
                                </form>
                                <form onSubmit={() => this.submitSecondForm()} onKeyDown={() => this.disableSubmitOnEnterKey()} className="f2 ac" style={{ display: this.state.formTabIndex === 2 ? "block" : "none" }}>
                                    <div className="f2-input-field">
                                        <label for="country">Pays</label>
                                        <CountriesInput sendCountry={this.getCountry} />
                                    </div>
                                    <div className="f2-input-field">
                                        <label for="address">Adresse</label>
                                        <input className="addConference-input" type="text" id="address" name="address" placeholder="Cité Ben Badis Amphithéâtre 2, Alger" required />
                                    </div>
                                    <div className="f2-input-field">
                                        <label for="startDate">Date du début</label>
                                        <input className="addConference-input" type="date" id="startDate" name="startDate" required />
                                    </div>
                                    <div className="f2-input-field">
                                        <label for="endDate">Date du fin</label>
                                        <input className="addConference-input" type="date" id="endDate" name="endDate" required />
                                    </div>
                                    <div className="f2-input-field">
                                        <label for="image">Image</label>
                                        <input className="addConference-input" type="file" id="image" name="image" accept="image/*" required />
                                    </div>
                                    <button type="submit">
                                        Suivant
                                        <i className="bi bi-arrow-right"></i>
                                    </button>
                                </form>
                                <div className="thirdTab-container" style={{ display: this.state.formTabIndex === 3 ? "block" : 'none' }}>
                                    <div className="searchBar">
                                        <input type="search" id="searchInvitations" placeholder="Chercher par noms, métiers, mots-clés, ... " onChange={()=>this.searchResult()} />
                                        <i className="bi bi-search"></i>
                                    </div>
                                    <div className="invitations-container" style={{ display: this.state.formTabIndex === 3 ? "flex" : "none" }} >
                                        {
                                            this.state.searchResult.length===0 ? this.state.users.map((e, i) =>
                                            <ReviewerInvitation removeInvitedFromList={this.removeInvitedFromList} addInvitedToList={this.addInvitedToList}  key={i} user={e} />) :  this.state.searchResult.map((e, i) =>
                                                <ReviewerInvitation removeInvitedFromList={this.removeInvitedFromList}  addInvitedToList={this.addInvitedToList} key={i} user={e} />
                                            )
                                        }
                                    </div>
                                    <div className="invited-container">
                                        <span>Liste des invités: </span>
                                        <ul>
                                            {
                                                this.state.invited.length===0 ? <span>Personne n'à été invité</span> : this.state.invited.map((e,i)=>
                                                    <li>{e.username}</li>
                                                )
                                            }
                                        </ul>
                                    </div>
                                    <span style={{display: (this.state.invited.length!==0) ? "none" : "block"}} id="ac-err">Un problème est survenu, vérifiez que tous les champs ont été bien remplis</span>
                                    <button id="confirmAddConference" disabled={this.state.invited.length===0}>Ajouter conférence</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="participations-container">
                        <hr />
                        <span>Participations</span>
                        <div className="participations-tabs">
                            <span onClick={() => this.setState({ showOldConferences: true })} className={this.state.showOldConferences ? "selected-tab" : ""}>Anciens</span>
                            <span onClick={() => this.setState({ showOldConferences: false })} className={!this.state.showOldConferences ? "selected-tab" : ""}>À venir</span>
                        </div>
                        <div className="participations-conferences old" style={{ display: this.state.showOldConferences ? "block" : "none" }}>
                            <Conference />
                        </div>
                        <div className="participations-conferences new" style={{ display: !this.state.showOldConferences ? "block" : "none" }}>
                            <Conference />
                            <Conference />
                            <Conference />
                        </div>
                    </div>
                </div>
                <Footer />
            </div>

        )
    }
}

export default UserPage;