import {Component} from 'react'
import { getCookieValue, getUserData } from '../../Functions'

class MainPage extends Component {

    constructor(props) {
        super(props)
        this.state = {
            token: getCookieValue('token'),
            user: null
        }
    }

    async componentDidMount() {
        if(this.state.token !== '') {
            let ud = await getUserData(this.state.token)
            this.setState({user: ud})
        }
        else {
            console.log('cdm: not logged in')
        }
    }
    
    render() {
        return(
            <h1>Hello, {this.state.token === ""  ? "Please sign in." : (this.state.user===null ? "You are logged in, please wait for your data to load" : JSON.stringify(this.state.user)) }</h1>
        )
    }
}

export default MainPage;