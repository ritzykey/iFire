import axios from "axios";


export const signUp = (body) => {
    return axios.post('api/v1/users', body,{headers: {
        "Accept-Language": "tr"
    }});
}