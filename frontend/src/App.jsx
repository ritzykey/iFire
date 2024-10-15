import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'
import { Outlet } from 'react-router-dom'
import LanguageSelector from './shared/components/LanguageSelector'
import Login from './pages/Login'
import NavBar from './shared/components/Navbar'
import { AuthenticationContext } from './shared/state/context'


function App() {



  const style = {
    height: '100vh'
  }

  return (
    <AuthenticationContext>
      <div className='bg-dark' style={style} >
        <NavBar />
        <div className="m-3" data-bs-theme="dark" >
          {/* <Login /> */}
          <Outlet />
          <LanguageSelector />
        </div>
      </div>
    </AuthenticationContext>
  )
}

export default App
