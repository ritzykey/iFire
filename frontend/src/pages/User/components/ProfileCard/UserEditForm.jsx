import { useState } from "react";
import { updateUser } from "./api";
import Input from '@/pages/SignUp/components/Input';
import { useAuthDispatch, useAuthState } from "@/shared/state/context";
import { useTranslation } from "react-i18next";


const UserEditForm = ({ setEditMode, setTempImage }) => {

    const authState = useAuthState();
    const dispatch = useAuthDispatch();

    const { t } = useTranslation();



    const [newUsername, setNewUsername] = useState(authState.username);
    const [apiProgress, setApiProgress] = useState(false);
    const [generalError, setGeneralError] = useState('');
    const [errors, setErrors] = useState();
    const [newImage, setNewImage] = useState();



    const onChangeUsername = (event) => {
        setNewUsername(event.target.value)
        setErrors({})
    }

    const onClickCancel = () => {
        setEditMode(false);
        setNewUsername(authState.username);
        setNewImage();
        setTempImage();
    }

    const onSelectImage = (event) => {

        if (event.target.files.length < 1) return;

        const file = event.target.files[0];
        const fileReader = new FileReader();

        fileReader.onloadend = () => {
            const data = fileReader.result;
            setNewImage(data);
            setTempImage(data);
        }

        fileReader.readAsDataURL(file);

    }

    const onSubmit = (event) => {
        event.preventDefault()
        setApiProgress(true)
        setErrors({})
        setGeneralError()
        updateUser(authState?.id, { username: newUsername, image: newImage }).then(
            ({data}) => {
                dispatch({ type: "user-update-success", data: { username: newUsername, image: data.image } })
                setEditMode(false)
            }
        )
            .catch(e => {
                if (e.response.data) {
                    if (e.response.data.status === 400) {
                        setErrors(e.response.data.validationErrors)
                    } else {
                        setGeneralError(e.response.data.message)
                    }
                } else {
                    setGeneralError(t("genericError"))
                }
            })
            .finally(() => setApiProgress(false))
    }




    return (
        <form onSubmit={onSubmit} className="was-validated">
            <Input label={t('username')} defaultValue={authState.username} onChange={onChangeUsername} error={errors?.username} />
            <Input label="Profile Image" type="file" onChange={onSelectImage} />
            {generalError && <div className="alert alert-danger">{generalError}</div>}
            <button className="btn btn-secondary me-2" type="submit" >Save</button>
            <div className="btn btn-outline-secondary" onClick={onClickCancel} >Cancel</div>
        </form>
    );
}

export default UserEditForm;