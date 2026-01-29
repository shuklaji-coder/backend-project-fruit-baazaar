import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5174,  // fix port
    strictPort: true  // agar 5174 busy ho toh fail kar de
  }
});
