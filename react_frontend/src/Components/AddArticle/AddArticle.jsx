import {Component} from 'react'


class AddArticle extends Component {

    sendFile() {
        
    }

    render() {
        return(
            <div>
                <h1>Hello, this is the add article page</h1>
                <form>
                    <input type='file' name='articleFile'/>
                    <input type="submit"/>
                </form>
            </div>
        )
    }
}

export default AddArticle;