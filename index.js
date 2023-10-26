import app from "./app.js";
import { Server as SocketServer } from "socket.io";
import http from "http";
import getLocalIPv4Address from "./ipServer.js"

const localIPv4Address = getLocalIPv4Address();
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
  //

  socket.on("disconnect", () => {
    console.log(`Client disconnected: ${socket.id}`);
  });
});

// Inicio del server
server.listen(PORT, localIPv4Address,() => {
  console.log(`Server is running on port ${PORT} ip ${localIPv4Address}`);
});
