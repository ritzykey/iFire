import UserListItem from "./UserListItem";

const UserList = ({ userPage }) => {



    return <>
        <ul className="list-group list-group-flush">
            {

                userPage.map(user =>
                    <UserListItem key={user.id} user={user} />
                )
            }

        </ul>

    </>;
}

export default UserList;