import { Router } from "express";
import {descifrado} from "./cipher.js";

const router=Router();

router.post('/descifrar',descifrado);
export default router;