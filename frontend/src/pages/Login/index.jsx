import http from "@/lib/http";
import { useContext, useEffect, useState } from "react";
import login from "./api";
import { useTranslation } from "react-i18next";
import { AuthContext } from "@/shared/state/context";
import { useNavigate } from "react-router-dom";

const Login = () => {


    const authState = useContext(AuthContext);

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [generalError, setGeneralError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const { t } = useTranslation();

    const navigate = useNavigate();


    const isFormValid = () => {
        return email.trim() !== '' && password.trim() !== '';
    };

    const handleSubmit = (event) => {
        event.preventDefault(); // Sayfanın yenilenmesini engeller
        console.log('Form Submitted:', event);
        login({ email, password })
            .then(({ data }) => {
                authState.onLoginSuccess(data.user)
                navigate("/")
            })
            .catch(
                ({ response }) => setGeneralError(response.data.message)
            )
    };


    return <>
        <div className="h2" >Login Page</div>
        <div className="container-sm"  >
            <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
                <div className="card mb-2" >
                    <div className="card-header text-center h1">{t("login")}</div>
                    <div className="card-body ">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
                                <input type="email" className="form-control" onChange={e => setEmail(e.target.value)} id="exampleInputEmail1" aria-describedby="emailHelp" />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
                                <input type="password" className="form-control" onChange={e => setPassword(e.target.value)} id="exampleInputPassword1" />
                            </div>
                            <div className="text-center">
                                <button type="submit" className="btn btn-primary" disabled={!isFormValid()}>{t("login")}</button>
                            </div>
                        </form>
                    </div>
                </div>
                {generalError && <div className="alert alert-danger">{generalError}</div>}
                {successMessage && <div className="alert alert-success">{successMessage}</div>}
            </div>

        </div>

    </>;
}

export default Login;