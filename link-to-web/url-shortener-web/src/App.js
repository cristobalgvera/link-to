import React from 'react';
import './App.css';
import {BrowserRouter} from "react-router-dom";
import Routes from "./components/Routes";
import Header from "./components/Header";

function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <Header/>
                <Routes/>
            </div>
        </BrowserRouter>
    );
}

export default App;
