import app from "./app.js";
import { Server as SocketServer } from "socket.io";
import http from "http";


const PORT=process.env.PORT||3000;
const server = http.createServer(app);
const io = new SocketServer(server, {
  cors: {
    origin: "http://localhost:5173",
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
  //

  socket.on("disconnect", () => {
    console.log(`Client disconnected: ${socket.id}`);
  });
});

// Inicio del server
server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
