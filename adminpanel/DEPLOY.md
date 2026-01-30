# Netlify Deployment Guide

## Prerequisites
- Node.js installed
- Netlify account

## Deployment Steps

### Method 1: Using Netlify Dashboard (Recommended)

1. **Push your code to GitHub/GitLab/Bitbucket**
   ```bash
   git add .
   git commit -m "Prepare for Netlify deployment"
   git push origin main
   ```

2. **Go to Netlify Dashboard**
   - Visit https://app.netlify.com
   - Click "Add new site" → "Import an existing project"
   - Connect your Git provider
   - Select your repository

3. **Configure Build Settings**
   - Base directory: `adminpanel`
   - Build command: `npm run build`
   - Publish directory: `adminpanel/dist`

4. **Deploy**
   - Click "Deploy site"
   - Wait for build to complete

### Method 2: Using Netlify CLI

1. **Install Netlify CLI** (if not installed)
   ```bash
   npm install -g netlify-cli
   ```

2. **Login to Netlify**
   ```bash
   netlify login
   ```

3. **Navigate to adminpanel directory**
   ```bash
   cd adminpanel
   ```

4. **Initialize and Deploy**
   ```bash
   netlify init
   netlify deploy --prod
   ```

## Configuration Files

- `netlify.toml` - Netlify build configuration
- `public/_redirects` - SPA routing support

## Environment Variables (if needed)

If you need to set environment variables:
1. Go to Site settings → Environment variables
2. Add any required variables

## Notes

- The admin panel is configured to use production backend: `https://backend-project-fruit-baazaar-15.onrender.com`
- SPA routing is configured to redirect all routes to `index.html`
- Build uses Node.js 18

