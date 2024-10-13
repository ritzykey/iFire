import http from "../../lib/http";


const getUsersList = (page = 0) => http.get("/api/v1/users", {
    params: {
        page, size: 3
    }
});

export default getUsersList;