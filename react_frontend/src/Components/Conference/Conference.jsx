import {Component} from 'react'
import $clamp from 'clamp-js'
import './Conference.css'

class Conference extends Component {


    componentDidMount() {
        for(let k of document.getElementsByClassName('c-info-text-details'))
            $clamp(k, {clamp: 3});
    }

    render() {
        return(
            <div className="c-container">
                <hr className="divider"></hr>
                <div className="c-info">
                    <div className="c-info-image-container">
                        <img src="https://kmeducationhub.de/wp-content/uploads/2014/08/wc-knowledgemanagement-conferences.jpg" className="c-info-image-img" />
                    </div>
                    <div className="c-info-text">
                        <span className="c-info-text-daysLeft">18 Days left</span>
                        <span className="c-info-text-title">Lorem ipsum dolor sit amet sonic theubsdijfbsdfbdibiu</span>
                        <ul className="c-info-text-tags">
                            <li className="c-info-text-tags-item">
                                <a href="/">Informatique</a>
                            </li>
                            <li className="c-info-text-tags-item">
                                <a href="/">Securité</a>
                            </li>
                            <li className="c-info-text-tags-item">
                                <a href="/">Informatique</a>
                            </li>
                            <li className="c-info-text-tags-item">
                                <a href="/">Informatique</a>
                            </li>
                            <li className="c-info-text-tags-item">
                                <a href="/">Securité</a>
                            </li>
                            <li className="c-info-text-tags-item">
                                <a href="/">Informatique</a>
                            </li>
                            {/* <li className="c-info-text-tags-item">Sécurité</li>
                            <li className="c-info-text-tags-item">Mathématique</li> */}
                        </ul>
                        <p className="c-info-text-details">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. At quam vero dolorem blanditiis atque eius animi facere, 
                            neque magnam. Facilis sint veniam fugiat iure amet saepe dicta dolorem, esse nihil.
                        </p>
                        <div className="c-info-text-extra">
                            <span>
                                <i className="bi bi-geo-alt"></i>
                                Cité 200 logements Ben Badis, Oran, Algérie
                            </span>
                            <span>
                                <i className="bi bi-person"></i>
                                187 vont participer
                            </span>
                        </div>
                    </div>
                </div>
                <div className="c-plus">
                    <a href="/conference/5" className="c-plus-link-btn">Détails</a>
                    <div className="c-plus-icons">
                        <a href="/" className="c-plus-icons-share">
                            <i className="bi bi-share"></i>
                        </a>
                        <a href="/" className="c-plus-icons-save">
                            <i className="bi bi-bookmark"></i>
                        </a>
                    </div>
                </div>
            </div>
        )
    }
}

export default Conference;