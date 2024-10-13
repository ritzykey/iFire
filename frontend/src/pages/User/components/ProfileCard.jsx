
import defaultProfileImage from '@/assets/defaultProfileImage.jpg';
import { AuthContext } from '@/shared/state/context';
import { useContext } from 'react';


const ProfileCard = ({ user }) => {

    const authState = useContext(AuthContext);

    return <>
        <div className="card">
            <div className="card-header text-center">
                <img className='img-fluid rounded-circle shadow-sm'
                    src={defaultProfileImage}
                    width={200}
                    alt=""
                />
                {authState.id === user.id &&  <div className='d-flex justify-content-end'>
                    <div className="btn btn-outline-secondary ">Edit</div>
                </div>}
            </div>
            <div className="card-body text-center">
                <span className="fs-3">{user?.username}</span>
            </div>
        </div>
    </>;
}

export default ProfileCard;