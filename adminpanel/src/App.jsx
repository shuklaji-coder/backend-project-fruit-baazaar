import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Addfruit from './Pages/Add fruit/Addfruit';
import Listfruit from './Pages/Listfruit/Listfruit';
import Order from './Pages/order/order';

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
            <Route path="/add" element={<Addfruit />} />
            <Route path="/list" element={<Listfruit />} />
            <Route path="/order" element={<Order />} />
            <Route path="/" element={<Listfruit />} />
            <Route path="/Order" element={<Order />} />

            
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default App;
