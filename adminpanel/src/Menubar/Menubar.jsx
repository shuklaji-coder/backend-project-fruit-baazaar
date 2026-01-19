import React from 'react';

const Menubar = () => {
  const toggleSidebar = () => {
    const sidebar = document.getElementById('sidebar-wrapper');
    if (sidebar) {
      sidebar.style.display = sidebar.style.display === 'none' ? 'block' : 'none';
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <div className="container-fluid">
          {/* Left side - Only Toggle Button */}
          <button 
            className="btn btn-primary me-3" 
            id="sidebarToggle"
            onClick={toggleSidebar}
          >
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-toggle-off" viewBox="0 0 16 16">
              <path d="M11 4a4 4 0 0 1 0 8H8a5 5 0 0 0 2-4 5 5 0 0 0-2-4zm-6 8a4 4 0 1 1 0-8 4 4 0 0 1 0 8M0 8a5 5 0 0 0 5 5h6a5 5 0 0 0 0-10H5a5 5 0 0 0-5 5"/>
            </svg>
            <span className="ms-2">Toggle Menu</span>
          </button>

          <span className="navbar-brand mb-0 h1">Admin panel</span>

          
          <button 
            className="navbar-toggler ms-auto" 
            type="button" 
            data-bs-toggle="collapse" 
            data-bs-target="#navbarSupportedContent"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          
          {/* Empty collapse div for mobile toggler to work */}
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            {/* No menu items here */}
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Menubar;