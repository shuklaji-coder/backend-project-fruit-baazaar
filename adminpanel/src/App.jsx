import React from 'react';
import { Routes, Route } from 'react-router-dom';

// Page components (paths and casing must match exactly on Netlify/Linux)
import AddFruit from './Pages/Add fruit/AddFruit.jsx';
import ListFruit from './Pages/Listfruit/ListFruit.jsx';
import Order from './Pages/order/Order.jsx';

import SIdebar from './Sidebar/SIdebar';
import Menubar from './Menubar/Menubar';

const App = () => {
  return (
    <div className="d-flex" id="wrapper">
      <SIdebar />
      <div id="page-content-wrapper">
        <Menubar />
        <div className="container-fluid">
          <Routes>
            <Route path="/add" element={<AddFruit />} />
            <Route path="/list" element={<ListFruit />} />
            <Route path="/order" element={<Order />} />
            <Route path="/" element={<ListFruit />} />
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default App;
