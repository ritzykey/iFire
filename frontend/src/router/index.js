import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import { SignUp } from "../pages/SignUp";
import App from "../App";
import Photos from "../pages/Photos";
import Activation from "../pages/Activation";
import User from "@/pages/User";
import Login from "@/pages/Login";





const router = createBrowserRouter([
    {
        path: '/',
        Component: App,
        children: [
            {
                path: "/",
                index: true,
                Component: Home
            },
            {
                path: "/signup",
                Component: SignUp
            },
            {
                path: "/login",
                Component: Login
            },
            {
                path: "/activation/:token",
                Component: Activation
            },
            {
                path: "/photos/:id",
                Component: Photos
            },
            {
                path: "/user/:id",
                Component: User
            }
        ]
    }
])

export default router;