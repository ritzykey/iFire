import { useParams } from "react-router-dom";
import { activate } from "./api";
import { useEffect, useState } from "react";

const Activation = () => {

    const { token } = useParams();

    const [successMessage, setSuccessMessage] = useState();
    const [errorMessage, setErrorMessage] = useState();


    useEffect(() => {
        activate(token).then(
            res => setSuccessMessage(res.data.message)
        ).catch(err => setErrorMessage(err.response.data.message));

    }, []);

    return <div className="h3 text-center">
        Activation Page
        {successMessage && <div className="alert alert-success">{successMessage}</div>}
        {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
    </div>;
}

export default Activation;