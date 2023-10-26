import express from "express"; //Configurar el servidor para  una correcta autenticacion
import morgan from "morgan"; 
import router from "./routes.js";
import cors from "cors";

const app = express();

app.use(
    cors({
      origin:"*", 
      credentials: true,
    })
  ); 

app.use(express.json()); 
app.use(morgan('dev')); 
app.use(express.urlencoded({ extended: false }));

app.use(router);

export default app;