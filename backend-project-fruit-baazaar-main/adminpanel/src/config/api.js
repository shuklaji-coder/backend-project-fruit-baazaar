import axios from 'axios';

// Production Backend URL
const API_BASE_URL = 'https://backend-project-fruit-baazaar-15.onrender.com';

// Create axios instance with production URL
export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 30000, // Increased from 10000 to 30000
});

// For file uploads (multipart/form-data)
export const uploadApi = axios.create({
  baseURL: API_BASE_URL,
  timeout: 60000, // Increased for file uploads
});

// Export the base URL for direct usage
export { API_BASE_URL };

console.log('🔗 API Base URL:', API_BASE_URL);
console.log('🚀 Using Production Backend:', API_BASE_URL);

// Test the API immediately
api.get('/api/health/mongodb').catch(() => {
  console.log('❌ API Test Failed - but this is expected if backend is down');
});
