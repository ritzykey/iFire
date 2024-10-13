import { signUp } from "@/pages/SignUp/api";
import { useContext } from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { AuthContext } from "../state/context";

const NavBar = () => {


    const { t } = useTranslation();

    const authState = useContext(AuthContext);

    return <nav className="navbar navbar-expand-sm bg-body-tertiary" data-bs-theme="dark" >
        <div className="container-fluid">
            <Link className="navbar-brand" to="/">
                <img className="d-inline-block align-top me-2" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="" />
                Bootstrap
            </Link>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">

                    {
                        authState.id === 0 && <>

                            <li className="nav-item">
                                <Link className="nav-link" to="/login" >{t("login")}</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/signup" >{t("signUp")}</Link>
                            </li>
                            <a className="nav-item nav-link" href="#">Pricing</a>
                            <a className="nav-item nav-link disabled" href="#">Disabled</a>

                        </>
                    }

                    {
                        authState.id > 0 && <>
                            <li className="nav-item">
                                <Link className="nav-link" to={`/user/${authState.id}`}>My Profile</Link>
                            </li>
                            <li className="nav-item">
                                <span className="nav-link" role="button" >Logout</span>
                            </li>
                        </>
                    }

                </ul>
            </div>

        </div>
    </nav >;
}

export default NavBar;