import ConfirmMail from './Components/ConfirmMail/ConfirmMail'
import { Switch, BrowserRouter, Route, Router } from 'react-router-dom'
import SignIn from './Components/SignIn/SignIn'
import AccountActivated from './Components/AccountActivated/AccountActivated'
import MainPage from './Components/MainPage/MainPage'
import ForgotPassword from './Components/ForgotPassword/ForgotPassword'
import AddArticle from './Components/AddArticle/AddArticle'
import ProtectedRoute from 'react-protected-route'
import {isLoggedIn} from './Functions'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Switch>
          <Route exact path='/' component={MainPage} />
          <ProtectedRoute isAuthenticated={isLoggedIn()} redirectTo="/signin" path="/add-article" component={AddArticle} />
          <Route exact path='/forgot-password' component={ForgotPassword} />
          <Route exact path="/signin" component={SignIn} />
          <Route path='/verify-account' component={ConfirmMail} />
          <Route path='/confirm/:token' component={AccountActivated} />
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
