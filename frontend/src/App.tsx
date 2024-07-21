import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import NavBar from './layout/navbar/NavBar';
import { ConfigProvider, theme } from 'antd';

function App() {
  const { darkAlgorithm, compactAlgorithm } = theme;

  return (
    <div className="App">
        <ConfigProvider
            theme={
              {
                token: {
                    // Seed Token
                    colorPrimary: '#f56a00',
                    borderRadius: 5,

                    // Alias Token
                    colorBgContainer: '#f9f0e9c0',
                },
                // components: {
                //   Input: {
                //     colorPrimary: '#00b96b',
                //     lineHeight: 5,
                //     algorithm: true, // Enable algorithm
                //   },
                //   Image: {
                //     colorPrimary: '#f56a00',
                //     algorithm: true, // Enable algorithm
                //   }
                // }
              }
            }
        >
          <Router>
              <NavBar/>
              <div className="background-layout"></div>
              {/* <Background></Background> */}
              <Routes>
                {/* <Route path="/candidate_answers/:userId/:testTemplateId" element={<ViewUserAnswers/>}></Route> */}
              </Routes>
          </Router>
        </ConfigProvider>
    </div>
  );
}

export default App;
