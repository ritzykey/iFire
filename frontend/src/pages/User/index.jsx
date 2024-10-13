import { useLocation, useParams } from "react-router-dom";
import getUser from "./api";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import ProfileCard from "./components/ProfileCard";

const User = () => {

    const { id } = useParams();

    const { t } = useTranslation();

    const [user, setUser] = useState({
        id: 1,
        username: '',
        email: '',
        image: '',
        fullName: ''
    });

    const [error, setError] = useState('');

    const location = useLocation();

    useEffect(() => {
        console.log(location)

        const fetch = async () => {

            try {
                const { data: res } = await getUser(id);
                setUser(res);
            } catch (err) {
                console.error(err);
                setError(t('userNotFound'))
            }


        }
        fetch()

    }, [location]);

    return <><div className="h2" >  User Page</div>
        <span>
            {user?.username && <div className="h2">{user?.username}</div>}

            <ProfileCard user={user} />

            {error && <div className="alert alert-danger m-3">{error}</div>}

        </span>
    </>;
}

export default User;