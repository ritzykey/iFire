import { useEffect, useMemo, useState } from "react"
import { signUp } from "./api"
import Input from "./components/Input"
import { useTranslation } from "react-i18next"
import LanguageSelector from "../../shared/components/LanguageSelector"

export function SignUp() {

    const [username, setUsername] = useState()
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [passwordRepeat, setPasswordRepeat] = useState()
    const [apiProgress, setApiProgress] = useState(false)
    const [successMessage, setSuccessMessage] = useState();
    const [errorMessage, setErrorMessage] = useState({});
    const [generalError, setGeneralError] = useState();
    const { t } = useTranslation();

    useEffect(() => {
        setErrorMessage((lastErrors) => {
            return {
                ...lastErrors,
                username: undefined
            }
        })
    }, [username]);

    useEffect(() => {
        setErrorMessage((lastErrors) => {
            return {
                ...lastErrors,
                email: undefined
            }
        })
    }, [email]);

    useEffect(() => {
        setErrorMessage(
            (lastErrors) => {
                return {
                    ...lastErrors,
                    password: undefined
                }
            }
        )
    }, [password]);

    useEffect(() => {
        setErrorMessage(
            (lastErrors) => {
                return {
                    ...lastErrors,
                    password: undefined
                }
            }
        )
    }, [passwordRepeat]);




    const onSubmit = async (event) => {
        event.preventDefault()
        setSuccessMessage()
        setApiProgress(true)
        setErrorMessage({})
        setGeneralError()

        try {
            const response = await signUp({
                username,
                email,
                password
            })
            setSuccessMessage(response.data.message)
            console.log(response);


        } catch (error) {
            if (error.response?.data) {
                if (error.response?.data.status === 400)
                    return setErrorMessage(error.response?.data.validationErrors)

                return setGeneralError(error.response?.data.message)
            }
            setGeneralError(t("genericError"))
        } finally {
            setApiProgress(false)
        }
    }

    const passwordRepeatError = useMemo(
        () => {
            if (password && password !== passwordRepeat) {
                console.log("always running");
                return t("passwordMismatch")
            }
            return ''
        }, [password, passwordRepeat]
    )

    return <div className="container mt-3">
        <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
            <form className="card" onSubmit={onSubmit}>
                <h1 className="text-center card-header">{t("signUp")}</h1>
                <div className="card-header">
                    <Input id="username" label={t('username')} error={errorMessage.username} onChange={(event) => setUsername(event.target.value)} />
                    <Input id="email" label={t('email')} error={errorMessage.email} onChange={(event) => setEmail(event.target.value)} />
                    <Input id="password" label={t('password')} type="password" error={errorMessage.password} onChange={(event) => setPassword(event.target.value)} />
                    <Input id="passwordRepeat" label={t('passwordRepeat')} type="password" error={passwordRepeatError} onChange={(event) => setPasswordRepeat(event.target.value)} />
                    {successMessage && <div className="alert alert-success">{successMessage}</div>}
                    {generalError && <div className="alert alert-danger">{generalError}</div>}
                    <div className="text-center">
                        <button className="btn btn-primary" disabled={apiProgress || (!password || password !== passwordRepeat)}>
                            {apiProgress && <span className="spinner-border spinner-border-sm" aria-hidden="true"></span>}
                            {t("signUp")}</button>
                    </div>
                </div>
            </form>
            
        </div>

    </div>
}