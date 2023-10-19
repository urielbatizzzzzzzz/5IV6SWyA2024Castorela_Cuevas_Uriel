const desplazamiento2 = document.getElementById("desplazamiento");
const textoCifrado = document.getElementById("cifrado");
const texto = document.getElementById("txt");
const txtClaveInput2 = document.getElementById("txtClave");
const cesarRadio = document.getElementById("cesar");
const longCif2 = document.getElementById('logcifrar');
const longDecif2 = document.getElementById('logdescifrar');

cesarRadio.addEventListener("change", function () {
  desplazamiento2.disabled = false;
  longCif2.disabled=true;
    longDecif2.disabled=true;
});
// Función para cifrar el texto

const cifrado = () => {
  const textoIngresado = texto.value;
  const clave = txtClaveInput2.value;

  if (cesarRadio.checked) {
    if (desplazamiento2.value.length !== 1 || !(/^[a-zA-Z0-9]$/.test(clave))) {
      textoCifrado.value = "La clave debe ser un solo carácter alfabético o numérico.";
      return;
    }

    let valorDesplazamiento = isNaN(clave) ? clave.toUpperCase().charCodeAt(0) - 65 : parseInt(clave);

    textoCifrado.value = textoIngresado
      .split("")
      .map((c) => {
        let mayus = c === c.toUpperCase();
        let valorEntero = c.toLowerCase().charCodeAt(0);

        if (valorEntero >= 97 && valorEntero <= 122) {
          valorEntero = ((valorEntero - 97 + valorDesplazamiento) % 26 + 26) % 26 + 97 + desplazamiento2.value;
        }

        let cifrado = String.fromCharCode(valorEntero);
        return mayus ? cifrado.toUpperCase() : cifrado;
      })
      .join("");
  }
};

// Escuchar cambios en los campos de texto y clave
texto.addEventListener("input", cifrado);
txtClaveInput2.addEventListener("input", cifrado);
desplazamiento2.addEventListener("input", cifrado);
