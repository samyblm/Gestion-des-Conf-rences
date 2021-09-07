import { Component } from "react";
import Conference from "../Conference/Conference";
import './ConferencesPage.css'

class ConferencesPage extends Component {
    render() {
        return (
            <div>
                <div style={{width: '100vw', height: '60px', backgroundColor: "green"}}>
                    <h1 style={{color: 'white'}}>Hello this is a div</h1>
                </div>
                <div className="cp-image-container">
                    <span className="cp-image-text">Conférences</span>
                    <img className="cp-image" src="/images/conf-img-2.jpg" />
                </div>
                <div className="cp-form">
                    <form>
                        <div className="input-field" id="subject-input">
                            <input type="text" id="subject" name="subject" placeholder="Rechercher Sujet"/>
                            <label for="subject"><i className="bi bi-search"></i></label>
                        </div>
                        <button type="submit">Rechercher</button>
                        <div className="input-field">
                            <input type="text" id="location" name="location" placeholder="Location"/>
                            <label for="location"><i className="bi bi-geo-alt"></i></label>
                        </div>
                        <div className="input-field">
                            <input type="text" id="dueDate" name="dueDate" placeholder="Due Date" onFocus={function(){document.getElementById('dueDate').setAttribute('type','date')}} onBlur={function(){document.getElementById('dueDate').setAttribute('type','text')}} />
                            <label for="dueDate"><i className="bi bi-calendar-event"></i></label>
                        </div>
                        <div className="input-field">
                            <input type="text" id="tag" name="tag" placeholder="Tags"/>
                            <label for="tag"><i className="bi bi-tag"></i></label>
                        </div>
                        {/* <div style={{position: 'relative', width: 'fit-content', gridArea: 't', width: '100%', padding: '10px'}}>
                            <select name="location" id="location">
                                <option>Sonic</option>
                                <option>Mario</option>
                                <option>Luigi</option>
                                <option>Peach</option>
                            </select>
                            <label for="location">
                                <i className="bi bi-geo-alt"></i>
                            </label>
                        </div> */}
                        {/* <div className="input-field">
                            <select placeholder="select">
                                <option>Sonic</option>
                                <option>Mario</option>
                                <option>Luigi</option>
                            </select>
                        </div>
                        <div className="input-field">
                            <input type="text" id="subject"/>
                            <label for="subject"></label>
                        </div> */}
                    </form>
                </div>
                <div className="cp-conferences-container">
                    <Conference/>
                    <Conference/>
                    <Conference/>
                </div>
                <div className="cp-np-btns">
                    <button className="cp-np-btns-p">Précédent</button>
                    <span className="cp-np-btns-counter">Page 2/6</span>
                    <button className="cp-np-btns-n">Suivant</button>
                </div>
            </div>
        )
    }
}

export default ConferencesPage;