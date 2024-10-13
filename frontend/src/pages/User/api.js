import http from "@/lib/http";

function getUser(id) {
    return http.get(`/api/v1/users/${id}`);
}

export default getUser;