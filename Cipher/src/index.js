import app from "./app.js";
import { Server as SocketServer } from "socket.io";
import http from "http";
import {IP} from "../src/getPublicIP.js"

const PORT=process.env.PORT||3000;
const server = http.createServer(app);
const io = new SocketServer(server, {
  cors: {
    origin: "*",
  },
});
// Socket.io escucha
io.on("connection", (socket) => {
  console.log("client ", socket.id);

  //Mensaje nuevo
  socket.on("message", (body) => {
    socket.broadcast.emit("message", {
      body,
      from: socket.id.slice(8),
    });
  });
  socket.on("fileUpload", (fileData) => {
    
    console.log("Archivo .txt recibido:", fileData);
    // Realiza el procesamiento necesario aquí.

    socket.broadcast.emit("fileUploaded", fileData);
  });
  
  socket.on("disconnect", () => {
    console.log(`Client disconnected: ${socket.id}`);
  });
});

// Inicio del server
server.listen(PORT,IP, () => {
  console.log(`Server is running on port ${PORT} ${IP}`);
});
