import http from "@/lib/http"


const login = ({email, password}) => http.post('/api/v1/auth', { email, password })
    

export default login