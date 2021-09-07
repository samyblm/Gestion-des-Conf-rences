import React from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import './footer.css'

function Footer() {
    return (
        <footer style={{borderTop: '3px solid rgb(1,255,160)', paddingTop: "20px"}}>
             
            <a href="/">
                <img className="img-footer " src={'images/logo2.png'}   />
            </a>
            <div className="row footer">

                <div className="col-md-3">
                    <h5 className="footer-title ">contact-us</h5>
                    <ul className="list-unstyled">
                        <li>
                            <a class="link" href="tel:0777969499">+213 777 96 94 99</a>
                        </li>
                        <li>
                            <a class="link" href="tel:0777969499">+213 666 86 94 85</a>
                        </li>
                        <li>
                            <a class="link" href="#!">contact@confy.com</a>
                        </li>
                        <li>
                            <a class="link" href="#!">adress: neighberhood
                                ,algeirs , algeria </a>
                        </li>
                    </ul>

                </div>



                <div className="col-md-3 ">
                    <h5 className="footer-title ">Support</h5>
                    <ul className="list-unstyled">
                        <li>
                            <a class="link" href="#!">help & support</a>
                        </li>
                        <li>
                            <a class="link" href="#!">Trust  &  safety</a>
                        </li>
                        <li>
                            <a class="link" href="#!">Support@confy.com</a>
                        </li>

                    </ul>

                </div>

                <div className="col-md-3 ">
                    <h5 className="footer-title ">more</h5>
                    <ul className="list-unstyled">
                        <li>
                            <a class="link" href="#!">Language</a>
                        </li>
                        <li>
                            <a class="link" href="#!">Privacy</a>
                        </li>
                        <li>
                            <a class="link" href="#!">terms & conditions</a>
                        </li>

                    </ul>

                </div>

                <img className="img-footer2 " src={'images/bg3.png'}   />

                <div className="footer-copyright text-center py-3">© 2020 Copyright:
                    <a class="link" href=""> Confy platform and all it's actions. All rights reserved.</a>
                </div>
            </div>
            

        </footer>
    )
}

export default Footer
