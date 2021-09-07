import { Component } from "react";
import './ReviewerInvitation.css'

class ReviewerInvitation extends Component {


    constructor(props) {
        super(props);
        this.inviteReviewer = this.inviteReviewer.bind(this)
        this.removeInvitation = this.removeInvitation.bind(this)
        this.state = {
            isInvited: false
        }
    }

    inviteReviewer() {
        //put some fetch here ...
        this.setState({isInvited: true})
        this.props.addInvitedToList(this.props.user)
    }

    removeInvitation() {
        //put some fetch here
        this.setState({isInvited: false})
        this.props.removeInvitedFromList(this.props.user)
    }

    render() {
        return(
            <div className="ri-container">
                <div className="ri-image-container">
                    <a href="/"><img src="https://i.pravatar.cc/150?img=54" /></a>
                </div>
                <a href="/" className="ri-username">{this.props.user.username}</a>
                <i className="bi bi-link"></i>
                <span className="ri-userJob">{this.props.user.job}</span>
                <button onClick={this.state.isInvited ? ()=>this.removeInvitation() : ()=>this.inviteReviewer() } className={this.state.isInvited ? " invited" : ""}>{this.state.isInvited ? 'Supprimer' : 'Inviter'}</button>
            </div>
        )
    }
}


export default ReviewerInvitation;