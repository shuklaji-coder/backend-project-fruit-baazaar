import React from 'react'
import { Link } from 'react-router-dom'
import { assets } from '../assets/assets';

const SIdebar = () => {
  return (
    <div className="border-end bg-white" id="sidebar-wrapper" style={{ width: '250px', minHeight: '100vh' }}>
      <div className="sidebar-heading border-bottom bg-light p-3">
        <img src={assets.logo} alt="Logo" style={{ width: '100px' }} />
      </div>
      <div className="list-group list-group-flush">
        <Link className="list-group-item list-group-item-action list-group-item-light p-3" to="/add">
       <i className ="bi bi-plus-circle"></i> Add Fruit</Link>
        <Link className="list-group-item list-group-item-action list-group-item-light p-3" to="/list">
        <i className ="bi bi-list-ul"></i> List Fruits</Link>
        <Link className="list-group-item list-group-item-action list-group-item-light p-3" to="/order">
        <i className ="bi bi-cart3 me-2"></i> Order</Link>
        
      </div>
    </div>
  )
}

export default SIdebar
