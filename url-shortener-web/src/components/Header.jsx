import React from "react";
import {Nav, Navbar} from 'react-bootstrap';
import {Link, useHistory} from "react-router-dom";

const Header = () => {
    const history = useHistory();

    const redirectHome = () => {
        history.push("/");
    };

    return (
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand>
                <Nav.Link>
                    <Link to={"/"}>
                        <img
                            alt=""
                            src="/public/logo512.png"
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                        />{' '}
                        Url.Shortener
                    </Link>
                </Nav.Link>
            </Navbar.Brand>
        </Navbar>
    );
}

export default Header;