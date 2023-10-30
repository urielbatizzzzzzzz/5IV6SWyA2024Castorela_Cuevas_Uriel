import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { IP } from '../src/getPublicIP'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server:{
    host:IP
  }
})
