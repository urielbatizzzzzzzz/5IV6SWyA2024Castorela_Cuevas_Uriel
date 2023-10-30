import { Router } from "express";
import {descifrado,upload} from "./cipher.js";
import multer from "multer";

const router=Router();
const uploads = multer({ dest: "uploads/" });
router.post('/descifrar',descifrado);
router.post("/upload",uploads.single("txtFile"),descifrado)

export default router;