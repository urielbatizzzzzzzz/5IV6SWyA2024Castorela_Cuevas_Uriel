import React, { useState, useEffect } from "react";
import CryptoJS from "crypto-js";
import axios from "axios";
import io from "socket.io-client";
import ReactMarkdown from "react-markdown";
import { IP } from "../../../src/getPublicIP";

const socket = io(`http://${IP}:3000/`);
axios.defaults.baseURL = `http://${IP}:3000`;
export default function Cifrado() {
  //Estados
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState("");
  const [downloadLink, setDownloadLink] = useState(null);
  const [down, setDown] = useState(null);
  const [downFile, setDownFile] = useState(null);

  useEffect(() => {
    socket.on("message", receiveMessage);
    socket.on("fileUploaded", handleFileUploaded);

    return () => {
      socket.off("message", receiveMessage);
      socket.off("fileUploaded", handleFileUploaded);
    };
  }, []);

  const handleFileUploaded = (fileContent) => {
    const blob = new Blob([fileContent], {
      type: "text/plain;charset=utf-8",
    });
    const downloadUrl = URL.createObjectURL(blob);
    setDownFile(downloadUrl);
  };

  const receiveMessage = (message) =>
    setMessages((state) => [...state, message]);

  const handleSubmitMessage = (event) => {
    event.preventDefault();
    const newMessage = {
      body: message,
      from: "Me",
    };
    setMessages((state) => [...state, newMessage]);
    setMessage("");
    socket.emit("message", newMessage.body);
  };

  //ENCRIPTADO
  const handleEncrypt = () => {
    const fileInput = document.getElementById("fileInput");
    const keyInput = document.getElementById("keyInput");

    if (fileInput.files.length === 0 || keyInput.value.length !== 8) {
      alert(
        "Por favor, seleccione un archivo y una clave válida (máximo 8 caracteres)."
      );
      return;
    }

    const file = fileInput.files[0];
    const key = keyInput.value;

    const reader = new FileReader();

    reader.onload = function (event) {
      const fileContent = event.target.result;

      const encryptedText = CryptoJS.DES.encrypt(fileContent, key).toString();

      // Crear un enlace de descarga para el archivo cifrado
      const blob = new Blob([encryptedText], {
        type: "text/plain;charset=utf-8",
      });
      const downloadUrl = URL.createObjectURL(blob);
      setDownloadLink(downloadUrl);
      socket.emit("fileUpload", encryptedText);
    };
    reader.readAsText(file, "UTF-8");
  };

  //Densecriptar
  const handleDesencrypt = () => {
    const fileInput = document.getElementById("fileInputDesencrypt");
    const keyInput = document.getElementById("keyInputDesencrypt");

    if (fileInput.files.length === 0 || keyInput.value.length !== 8) {
      alert(
        "Por favor, seleccione un archivo y una clave válida (máximo 8 caracteres)."
      );
      return;
    }

    const file = fileInput.files[0];
    const key = keyInput.value;
    const reader = new FileReader();

    reader.onload = function (event) {
      const fileContent = event.target.result;
      axios
        .post("/descifrar", { clave: key, data: fileContent })
        .then((response) => {
          const blob = new Blob([response.data], {
            type: "text/plain;charset=utf-8",
          });
          const downloadUrlDes = URL.createObjectURL(blob);
          setDown(downloadUrlDes);
          console.log("Respuesta del servidor:", response.data);
        })
        .catch((error) => {
          console.error("Error en la solicitud al servidor:", error);
        });
    };

    reader.readAsText(file, "UTF-8");
  };

  return (
    <>
      <div className="bg-slate-400 p-10">
        <div className="container mx-auto max-w-lg p-4 bg-gray-100">
          <h1 className="text-2xl font-semibold mb-4 text-black">
            Cifrado DES en el navegador
          </h1>
          <div className="mb-4">
            <input
              type="file"
              id="fileInput"
              className="w-full py-2 px-4 border border-gray-400 rounded"
              accept=".txt"
            />
          </div>
          <div className="mb-4">
            <input
              type="text"
              id="keyInput"
              className="w-full py-2 px-4 border border-gray-400 rounded"
              placeholder="Clave secreta (máximo 8 caracteres)"
            />
          </div>
          <button
            id="encryptButton"
            className="w-full bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
            onClick={handleEncrypt}
          >
            Encryptar
          </button>
          {downloadLink && (
            <>
              <a
                download="archivo_cifrado.txt"
                href={downloadLink}
                className="w-full bg-green-500 text-white py-2 px-4 rounded mt-4 block hover:bg-green-600"
              >
                Descargar archivo cifrado
              </a>
            </>
          )}
        </div>
      </div>
      <div className="bg-slate-400 p-10">
        <div className="container mx-auto max-w-lg p-4 bg-gray-100">
          <h1 className="text-2xl font-semibold mb-4 text-black">
            Descifrado DES en el navegador
          </h1>
          <div className="mb-4">
            <input
              type="file"
              id="fileInputDesencrypt"
              className="w-full py-2 px-4 border border-gray-400 rounded"
              accept=".txt"
            />
          </div>
          <div className="mb-4">
            <input
              type="text"
              id="keyInputDesencrypt"
              className="w-full py-2 px-4 border border-gray-400 rounded"
              placeholder="Clave secreta (máximo 8 caracteres)"
            />
          </div>

          <button
            id="encryptButton"
            className="w-full bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
            onClick={handleDesencrypt}
          >
            Descifrar
          </button>
          {down && (
            <>
              <a
                download="archivo_descifrado.txt"
                href={down}
                className="w-full bg-green-500 text-white py-2 px-4 rounded mt-4 block hover:bg-green-600"
              >
                Descargar archivo Descifrado
              </a>
            </>
          )}
        </div>
      </div>
      <div className="h-screen bg-slate-400 text-black flex items-center justify-center m-0 p-0">
        <form
          onSubmit={handleSubmitMessage}
          className="bg-white p-6 rounded shadow-md w-96"
        >
          <h1 className="text-2xl font-bold my-2 text-black">Chat</h1>

          <ul className="h-64 overflow-y-auto mt-4">
            {messages.map((message, index) => (
              <li
                key={index}
                className={`my-2 p-2 rounded-md ${
                  message.from === "Me" ? "bg-blue-200" : "bg-gray-300"
                }`}
              >
                <b>{message.from}</b>
                <br />
                <ReactMarkdown
                  components={{
                    a: ({ node, ...props }) => (
                      <a {...props} download={true}>
                        {props.children}
                      </a>
                    ),
                  }}
                >
                  {message.body}
                </ReactMarkdown>
              </li>
            ))}
          </ul>
          <input
            name="message"
            type="text"
            placeholder="Escribe tu mensaje..."
            onChange={(e) => setMessage(e.target.value)}
            className="border border-gray-400 p-2 w-full"
            value={message}
            autoFocus
          />
          <p></p>
          {downFile && (
            <a
              download="archivo_encryptado.txt"
              href={downFile}
              className="w-full bg-blue-500 text-white py-2 px-4 rounded mt-2 hover:bg-blue-600"
            >
              Descargar Archivo
            </a>
          )}
        </form>
      </div>
    </>
  );
}
