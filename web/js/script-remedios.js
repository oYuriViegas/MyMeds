import { getRemedios } from "./api-paciente";

const remediosTableBody = document.querySelector('#pacientesTableBody');

function handlePatientRegistrationInputs() {
  let nome = document.getElementById('nome').value;
  let email = document.getElementById('email').value;
  let senha = document.getElementById('senha').value;
  
  let dadosPaciente = {
    nome: nome,
    email: email,
    senha: senha
  };
  console.log(dadosPaciente);
  fetch('http://localhost:8080/pacientes', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dadosPaciente)
  })
  .then(response => response.json())
  .then(data => console.log(data))
  .catch((error) => {
    console.error('Error:', error);
  });
}

function loadRemedios(id) {
  getRemedios(id)
  .then((remedios) => {
      console.log(remedios);
      remediosTableBody.innerHTML = '';
      /* response.data.array.forEach(remedio => {
        remediosTableBody.appendChild(remedio);
      }); */
      remedios.forEach(remedio => {
        let row = remediosTableBody.insertRow();
        let cellNome = row.insertCell();
        let cellMiligramagem = row.insertCell();
        let cellIntervalo = row.insertCell();
        let cellFrequencia = row.insertCell();
        cellNome.textContent = remedio.nome;
        cellMiligramagem.textContent = remedio.miligramagem;
        cellIntervalo.textContent = remedio.intervalo;
        cellFrequencia.textContent = remedio.frequencia;
      });
    })
    .catch((error) => { console.error('Falha ao carregar remédios:', error); });
}