/*
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
*/


import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import EmployeeList from "./components/EmployeeList";
import UploadEmployees from "./components/UploadEmployees";
import SecretSantaApp from "./components/SecretSantaApp";

const App = () => {
  const [refresh, setRefresh] = useState(false);

  return (
     <div className="container min-vh-100 d-flex flex-column align-items-center justify-content-center bg-light py-5">
       <h1 className="text-center mb-4">Secret Santa Employee Management</h1>
       <UploadEmployees onUploadSuccess={() => setRefresh(!refresh)} />
       <div className="mt-4 w-100">
         <EmployeeList key={refresh} />
       </div>
       <div className="mt-4">
         <SecretSantaApp />
       </div>
     </div>
   );
 };

export default App;

