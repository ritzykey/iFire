import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import "./styles.scss"
import "./locales"
import { RouterProvider } from "react-router-dom";
import router from "./router"

import '../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js';





createRoot(document.getElementById('root')).render(
    <RouterProvider router={router} />
)
