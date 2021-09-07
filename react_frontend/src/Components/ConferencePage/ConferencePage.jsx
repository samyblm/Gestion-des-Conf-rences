import {Component} from 'react'
import Conference from "../Conference/Conference"
import './ConferencePage.css'


class ConferencePage extends Component {
    render() {
        return (
            <div style={{backgroundColor: "rgb(225,225,225)"}}>
                <div style={{backgroundColor: "tomato", width: '100vw', height: '80px'}}></div>
                <div className="confp-container">
                    <div className="confp-image-container">
                        <img src="/images/conf-img.jpg" className="confp-image" />
                    </div>
                    <div className="confp-info-container">
                        <span className="confp-info-daysLeft">18 Days left</span>
                        <span className="confp-info-title">Nom de conférence</span>
                        <p className="confp-info-details">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Atque eum, expedita repudiandae nemo est asperiores fugit, 
                            iure aliquam numquam tempore maiores eos hic omnis consequuntur eaque explicabo, 
                            consequatur sapiente recusandae!Lorem ipsum dolor sit amet consectetur adipisicing elit. Atque eum, expedita repudiandae nemo est asperiores fugit, 
                            iure aliquam numquam tempore maiores eos hic omnis consequuntur eaque explicabo, 
                            consequatur sapiente recusandae!
                        </p>
                        <div className="confp-info-extra">
                            <div className="confp-info-extra-details">
                                <span>Conférencier: <b><a href="http://facebook.com">John doe</a></b></span>
                                <span>Lieu: <b>Salle des fetes Yasmine, Hai el Badr, Oran, ALgérie</b></span>
                                <span>Due Date: <b>15/12/2021 20:00:00 UTC</b></span>
                            </div>
                            <a href="http://facebook.com" target="_blank">
                                <img className="confp-info-extra-chairman" src="https://i.pravatar.cc/150?img=58" />
                            </a>
                        </div>
                        <ul className="confp-info-tags">
                            <li className="confp-info-tags-item">
                                <a href="/">Informatique</a>
                            </li>
                            <li className="confp-info-tags-item">
                                <a href="/">Securité</a>
                            </li>
                            <li className="confp-info-tags-item">
                                <a href="/">Mathématique</a>
                            </li>
                        </ul>
                        <hr/>
                        <span className="confp-info-small-title">Présentateurs: </span>
                        <ul className="confp-info-talkers">
                            <li className="confp-info-talkers-item">
                                <a href="http://facebook.com" target="_blank">
                                    <img src="https://i.pravatar.cc/150?img=58" alt="" />
                                </a>
                            </li>
                            <li className="confp-info-talkers-item main">
                                <a href="http://facebook.com" target="_blank">
                                    <img src="https://i.pravatar.cc/150?img=14" alt="" />
                                </a>
                            </li>
                            <li className="confp-info-talkers-item">
                                <a href="http://facebook.com" target="_blank">
                                    <img src="https://i.pravatar.cc/150?img=15" alt="" />
                                </a>
                            </li>
                            <li className="confp-info-talkers-item">
                                <a href="http://facebook.com" target="_blank">
                                    <img src="https://i.pravatar.cc/150?img=63" alt="" />
                                </a>
                            </li>
                            <li className="confp-info-talkers-item">
                                <a href="http://facebook.com" target="_blank">
                                    <img src="https://i.pravatar.cc/150?img=64" alt="" />
                                </a>
                            </li>
                        </ul>
                        <a className="confp-info-submit" href="/">
                            Déposer article
                        </a>  
                    </div>
                </div>
                <div className="confp-plan">
                    <span className="confp-plan-title">Planning</span>
                    <ul className="confp-plan-details">
                        <li className="confp-plan-details-item">
                            <span>10h - 11h30 AM</span>
                            <p>Lorem ipseum doolor sit amet</p>
                            <img src='https://i.pravatar.cc/150?img=47' className="main-avatar"/>
                        </li>
                        <li className="confp-plan-details-item">
                            <span>10h - 11h30 AM</span>
                            <p>Lorem ipseum doolor sit amet</p>
                            <img src='https://i.pravatar.cc/150?img=32' />
                        </li>
                        <li className="confp-plan-details-item">
                            <span>10h - 11h30 AM</span>
                            <p>Lorem ipseum doolor sit amet</p>
                            <img src='https://i.pravatar.cc/150?img=11' />
                        </li>
                        <li className="confp-plan-details-item">
                            <span>10h - 11h30 AM</span>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. 
                                Excepturi possimus quasi porro ullam fugit odio veniam 
                                maiores vel, aut reiciendis ipsum! 
                                Exercitationem porro corrupti nobis harum, 
                                cum optio ipsam quos.
                            </p>
                            <img src='https://i.pravatar.cc/150?img=2' />
                        </li>
                        <li className="confp-plan-details-item">
                            <span>10h - 11h30 AM</span>
                            <p>Lorem ipseum doolor sit amet</p>
                            <img src='https://i.pravatar.cc/150?img=5' />
                        </li>
                        <li className="confp-plan-details-item">
                            <span>10h - 11h30 AM</span>
                            <p>Lorem ipseum doolor sit amet</p>
                            <img src='https://i.pravatar.cc/150?img=68' />
                        </li>
                    </ul>
                </div>
                <div className="extra-conferences">
                    <span className="extra-conferences-title">Plus comme ça</span>
                    <Conference/>
                    <Conference/>
                </div>
            </div>
            
        )
    }
}

export default ConferencePage;