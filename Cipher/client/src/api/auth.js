import config from "./axios.js";

export const descifrar=(des)=>config.post('/descifrar',des);

