import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { useAuthDispatch, useAuthState } from "../state/context";
import { ProfileImage } from "./ProfileImage";

const NavBar = () => {


    const { t } = useTranslation();

    const authState = useAuthState();
    const dispatch = useAuthDispatch();



    return <nav className="navbar navbar-expand-sm bg-body-tertiary" data-bs-theme="dark" >
        <div className="container-fluid">
            <Link className="navbar-brand" to="/">
                <img className="d-inline-block align-top me-2" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="" />
                DBM
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
                            <li className="nav-item">
                                <Link className="nav-link" to='#' >Pricing</Link>
                            </li>
                            <li className="nav-item nav-link disabled" href="#">Disabled</li>

                        </>
                    }

                    {
                        authState.id && <>
                            <li className="nav-item">
                                <Link className="nav-link" to={`/user/${authState.id}`}>
                                    <ProfileImage width={25} image={authState.image} />
                                    <span>{authState.username}</span>
                                </Link>
                            </li>
                            <li className="nav-item">
                                <button className="nav-link" onClick={() => dispatch({ type: 'logout-success' })}>Logout</button>
                            </li>
                        </>
                    }

                </ul>
            </div>

        </div>
    </nav >;
}

export default NavBar;