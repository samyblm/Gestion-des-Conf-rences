import React from 'react'
import { Navbar, Nav } from 'react-bootstrap'
// import logo from 'images/logo.png';
import 'bootstrap/dist/css/bootstrap.css'
import './navbar.css'

// console.log(logo);


function MyNavbar() {
  return (
    <Navbar style={{backgroundColor: "rgb(1,255,160)"}} expand="lg">
     <div className="container-fluid">
      <Navbar.Brand  >
        <img src={'images/logo.png'} alt="Logos" href="#home" />
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse className="justify-content-end" >
        <Nav className="" >
          <Nav.Link href="#acceuil" className="nav-btn">Acceuil</Nav.Link>
          <Nav.Link href="#news" className="nav-btn">News</Nav.Link>
          <Nav.Link href="#conferences" className="nav-btn">Conferences</Nav.Link>
          <Nav.Link href="#articles" className="nav-btn">Articles</Nav.Link>
          <Nav.Link href="#j'organise" className="nav-btn">J'organise</Nav.Link>
          <Nav.Link href="#s'identifier" className="nav-btn s-identifier">S'identifier</Nav.Link>
        </Nav>
      </Navbar.Collapse>
      </div>
    </Navbar>
  )
}

export default MyNavbar;
