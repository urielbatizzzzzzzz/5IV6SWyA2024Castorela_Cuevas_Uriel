
import CryptoJS from 'crypto-js';

export const descifrado = async (req, res) => {
  const clave = req.body.clave;

  if (!clave || clave.length !== 8) {
      return res.status(400).send('La clave es inválida.');
  }

  try {
      const encryptedData = req.body.data;

      // Descifra los datos con CryptoJS
      const bytes  = CryptoJS.DES.decrypt(encryptedData, clave);
      const decryptedData = bytes.toString(CryptoJS.enc.Utf8);
      // Hacer algo con los datos descifrados
      console.log('Datos descifrados:', decryptedData);

      res.send(decryptedData);
  } catch (error) {
      res.status(500).send('Error al descifrar el archivo: ' + error.message);
  }
}
