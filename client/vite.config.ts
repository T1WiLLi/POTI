import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  build: {
    outDir: '../server/src/main/resources/static',
    assetsDir: '.',
    emptyOutDir: true,
  },
})
