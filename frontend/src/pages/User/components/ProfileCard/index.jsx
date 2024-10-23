
import { useAuthDispatch, useAuthState } from '@/shared/state/context';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { ProfileImage } from '@/shared/components/ProfileImage';
import UserEditForm from './UserEditForm';


const ProfileCard = ({ user }) => {

    const authState = useAuthState();

    const [editMode, setEditMode] = useState(false);
    const [tempImage, setTempImage] = useState();


    const dispatch = useAuthDispatch();


    const isEditButtonVisable = !editMode && authState.id === user.id;

    const visibleUsername = authState.id === user.id ? authState.username : user.username;



    return <>
        <div className="card">
            <div className="card-header text-center">
                <ProfileImage
                    width={200}
                    tempImage={tempImage}
                    image={user.image}
                />
                {isEditButtonVisable && <div className='d-flex justify-content-end'>
                    <div className="btn btn-outline-secondary " onClick={() => setEditMode(true)} >Edit</div>
                </div>}
            </div>
            <div className="card-body text-center">
                {!editMode && <span className="fs-3">{visibleUsername}</span>}
                {editMode && <UserEditForm setEditMode={setEditMode} setTempImage={setTempImage} />}
            </div>
        </div>
    </>;
}

export default ProfileCard;