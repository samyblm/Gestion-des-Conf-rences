import {Component} from 'react'
import './MiniConference.css'

class MiniConference extends Component {
    render() {
        return (
            <div className="mc-container">
                <a href="/conference/5" className="mc-full-link">
                    <img src="https://st.depositphotos.com/1056393/4859/i/600/depositphotos_48597043-stock-photo-speaker-at-business-conference-and.jpg" />
                    <span>Nom de la conférence</span>
                </a>
            </div>
        )
    }
}

export default MiniConference;