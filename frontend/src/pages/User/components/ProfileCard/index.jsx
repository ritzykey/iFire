
import defaultProfileImage from '@/assets/defaultProfileImage.jpg';
import Input from '@/pages/SignUp/components/Input';
import { useAuthState } from '@/shared/state/context';
import { useState } from 'react';
import { useTranslation } from 'react-i18next';
import { updateUser } from './api';


const ProfileCard = ({ user }) => {

    const authState = useAuthState();

    const [editMode, setEditMode] = useState(false);
    const [newUsername, setNewUsername] = useState();
    const [apiProgress, setApiProgress] = useState(false);

    const { t } = useTranslation();

    const isEditButtonVisable = !editMode && authState.id === user.id;

    const onChangeUsername = (event) => {
        setNewUsername(event.target.value)
    }

    const onClickSave = () => {
        setApiProgress(true)
        updateUser(user?.id, { username: newUsername })
            .catch()
            .finally(() => setApiProgress(false))
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
                {!editMode && <span className="fs-3">{user?.username}</span>}
                {editMode &&
                    <>
                        <Input label={t('username')} defaultValue={user?.username} onChange={onChangeUsername} ></Input>
                        <div className="btn btn-secondary me-2" onClick={onClickSave} >Save</div>
                        <div className="btn btn-outline-secondary" onClick={() => setEditMode(false)} >Cancel</div>
                    </>
                }
            </div>
        </div>
    </>;
}

export default ProfileCard;