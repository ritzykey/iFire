import { useCallback, useEffect, useState } from "react";
import getUsersList from "./api";
import UserList from "./components/UserList";

const Home = () => {

    const [userPage, setUserPage] = useState({
        content: [],
        last: false,
        first: false,
        number: 0
    });

    const getUsers = useCallback(
        async (page) => {
            await getUsersList(page).then(res => setUserPage(res.data));
        }, []
    )

    useEffect(() => {
        getUsers();
    }, []);

    return (<>
        <h1 className="text-white">HOME PAGE</h1>
        <div className="card m-4">
            <div className="card-header text-center fs-4">User List</div>
            <div className="card-body">
                <UserList userPage={userPage.content} />
            </div>
            <div className="card-footer">
                {!userPage.first && <button className="btn btn-outline-secondary btn-sm" onClick={() => getUsers(userPage.number - 1)}>Previous</button>}
                {!userPage.last && <button className="btn btn-outline-secondary btn-sm float-end" onClick={() => getUsers(userPage.number + 1)}>Next</button>}
            </div>
        </div>


    </>);
}

export default Home;