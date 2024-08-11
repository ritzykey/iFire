import { useState } from "react"
import { signUp } from "./api"

export function SignUp() {

    const [username, setUsername] = useState()
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [passwordRepeat, setPasswordRepeat] = useState()
    const [apiProgress, setApiProgress] = useState(false)
    const [successMessage, setSuccessMessage] = useState();



    const onSubmit = async (event) => {
        event.preventDefault()
        setSuccessMessage()
        setApiProgress(true)
        try {
            const response = await signUp({
                username,
                email,
                password
            })
            setSuccessMessage(response.data.message)

        } catch (error) {

        } finally {
            setApiProgress(false)
        }
    }


    return <div className="container">
        <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
            <form className="card" onSubmit={onSubmit}>
                <h1 className="text-center card-header">Sign UP</h1>
                <div className="card-header">
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Username</label>
                        <input className="form-control" type="text" id="username" onChange={(event) => setUsername(event.target.value)} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">E-mail</label>
                        <input className="form-control" id="email" onChange={(event) => setEmail(event.target.value)} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password</label>
                        <input className="form-control" type="password" id="password" onChange={(event) => setPassword(event.target.value)} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="passwordRepeat" className="form-label">Password Repeat</label>
                        <input className="form-control" type="password" id="passwordRepeat" onChange={(event) => setPasswordRepeat(event.target.value)} />
                    </div>
                    {successMessage && <div className="alert alert-success">{successMessage}</div>}

                    <div className="text-center">
                        <button className="btn btn-primary" disabled={apiProgress || (!password || password !== passwordRepeat)}>
                            {apiProgress && <span class="spinner-border spinner-border-sm" aria-hidden="true"></span>}
                            Sign UP</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
}