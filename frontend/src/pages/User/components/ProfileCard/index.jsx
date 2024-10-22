
import defaultProfileImage from '@/assets/defaultProfileImage.jpg';
import Input from '@/pages/SignUp/components/Input';
import { useAuthDispatch, useAuthState } from '@/shared/state/context';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { updateUser } from './api';


const ProfileCard = ({ user }) => {

    const authState = useAuthState();

    const [editMode, setEditMode] = useState(false);
    const [newUsername, setNewUsername] = useState(authState.username);
    const [apiProgress, setApiProgress] = useState(false);
    const [generalError, setGeneralError] = useState('');
    const [errors, setErrors] = useState();

    const dispatch = useAuthDispatch();

    const { t } = useTranslation();

    const isEditButtonVisable = !editMode && authState.id === user.id;

    const visibleUsername = authState.id === user.id ? authState.username : user.username;

    const onChangeUsername = (event) => {
        setNewUsername(event.target.value)
        setErrors({})
    }

    const onClickSave = () => {
        setApiProgress(true)
        setErrors({})
        setGeneralError()
        updateUser(user?.id, { username: newUsername }).then(
            () => {
                dispatch({type: "user-update-success", data: {username: newUsername}})
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

    const onClickCancel = () => {
        setEditMode(false);
        setNewUsername(authState.username);
    }

    return <>
        <div className="card">
            <div className="card-header text-center">
                <img className='img-fluid rounded-circle shadow-sm'
                    src={defaultProfileImage}
                    width={200}
                    alt=""
                />
                {isEditButtonVisable && <div className='d-flex justify-content-end'>
                    <div className="btn btn-outline-secondary " onClick={() => setEditMode(true)} >Edit</div>
                </div>}
            </div>
            <div className="card-body text-center">
                {!editMode && <span className="fs-3">{visibleUsername}</span>}
                {editMode &&
                    <>
                        <Input label={t('username')} defaultValue={visibleUsername} onChange={onChangeUsername} error={errors?.username} ></Input>
                        {generalError && <div className="alert alert-danger">{generalError}</div>}
                        <div className="btn btn-secondary me-2" onClick={onClickSave} >Save</div>
                        <div className="btn btn-outline-secondary" onClick={onClickCancel} >Cancel</div>
                    </>
                }
            </div>
        </div>
    </>;
}

export default ProfileCard;