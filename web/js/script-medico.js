import {getPacientes} from './api-paciente'
import {getRemedios} from './remedios'

const pacientesTableBody = document.querySelector('#pacientesTableBody');

function handleDoctorRegistrationInputs() {
  let senha = document.getElementById('senha').value;
  let crm = document.getElementById('crm').value;
  let uf = document.getElementById('uf').value;
  
  let dadosDoctor = {
    "senha": senha,
    "crm": crm,
    "uf": uf
  };
  console.log(dadosDoctor);
  fetch('http://localhost:8080/medicos', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: dadosDoctor.json
  })
  .then(response => response.json())
  .then(data => console.log(data))
  .catch((error) => {
    console.error('Error:', error);
  });
}

function loadPacientes(idMedico) {
  let selectPacientes = document.getElementById('selectPacientes');
  let idPaciente;
  getPacientes(idMedico)
  .then((pacientes) => {
    pacientes.forEach((paciente) => {
      let option = document.createElement('option');
      option.text = paciente.nome;
      option.value = paciente.id;
      selectPacientes.add(option);
    });
  })
  selectPacientes.addEventListener('change', function() {
    idPaciente = this.value;
    getRemedios(this.value);
  });

  getPacientes(idMedico)
  .then((pacientes) => {
    console.log(pacientes);
    pacientesTableBody.innerHTML = '';
    response.data.array.forEach(paciente => {
    pacientesTableBody.appendChild(paciente);
  });
})
.catch((error) => { console.error('Falha ao carregar remédios:', error); });
}

function adicionarMedicamento(event) {
    event.preventDefault();
    const nomeremedio = document.getElementById('nomeremedio').value;
    const miligramagem = document.getElementById('miligramagem').value;
    const frequencia = document.getElementById('frequencia').value;
    const intervalo = document.getElementById('intervalo').value;
  
    const novoMedicamento = {
      "nome": nomeremedio,
      "miligramagem": miligramagem,
      "frequencia": frequencia,
      "intervalo": intervalo,
      "pacienteId": idPaciente.value 
    };
  
    // Aqui você adicionaria o código para fazer uma requisição POST para o endpoint adequado
    axios.post(`/remedios`, novoMedicamento);
    // Resetar o formulário após o envio
    document.getElementById('nomeremedio').value = '';
    document.getElementById('miligramagem').value = '';
    document.getElementById('frequencia').value = '';
    document.getElementById('intervalo').value = '';
  }
  
  // Evento para o botão de adicionar novo medicamento
  document.querySelector('.button-blue2').addEventListener('click', adicionarMedicamento);
  
  // Inicia a função de listar pacientes quando a página carrega
  window.onload = getRemedios(idPaciente);


  const idMedico = null;
  function acessarTelaMedico() {
    document.querySelector('.acesso').addEventListener('click', function(event) {
        event.preventDefault();
      
        var crm = document.getElementById('crm').value;
        var senha = document.getElementById('senha').value;
      
        fetch('/verificarCredenciais', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ crm, senha })
        })
        .then(response => response.json())
        .then(data => {
          if (data.sucesso) {
            console.log('ID do médico: ' + data.id_medico);
            idMedico = data.id_medico;
          } else {
            console.log('Credenciais inválidas');
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });
      });
  }

  function loadPaciente(idPaciente) {
    getRemedios(idPaciente)
      .then(response => response.json())
      .then(remedios => {
        const tableBody = document.getElementById('remediosTableBody');
        tableBody.innerHTML = '';
        remedios.forEach(remedio => {
          const row = document.createElement('tr');
          row.innerHTML = `
            <td>${remedio.nome}</td>
            <td>${remedio.miligramagem}</td>
            <td>${remedio.frequencia}</td>
            <td>${remedio.intervalo}</td>
          `;
          tableBody.appendChild(row);
        });
      })
      .catch(error => console.error('Error:', error));
  }

