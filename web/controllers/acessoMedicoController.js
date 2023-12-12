const BASE_URL_MEDICOS = 'http://localhost:8080/medicos';
const BASE_URL_REMEDIOS = 'http://localhost:8080/remedios';
const BASE_URL_PACIENTES = 'http://localhost:8080/pacientes';

function loadPacientes() {
  const idMedico = localStorage.getItem('medicoLogado')
  let selectPacientes = document.getElementById('selectPacientes');
  axios.get(`${BASE_URL_MEDICOS}/${idMedico}/pacientes`)
  .then((pacientes) => {
    pacientes.data.forEach((paciente, index) => {
      if (index === 0) {
        const option = new Option(paciente.nome, paciente.id, true);
        selectPacientes.appendChild(option);
        getRemedios(paciente.id);
        localStorage.setItem('selectedPaciente', JSON.stringify(paciente.id))
        return;
      }
      const option = new Option(paciente.nome, paciente.id, false);
      selectPacientes.appendChild(option);
    });
  })

  selectPacientes.addEventListener('change', function() {
    getRemedios(this.value);
    localStorage.setItem('selectedPaciente', JSON.stringify(this.value))
  });
}

async function getRemedios(idPaciente) {
  const { data } = await axios.get(`${BASE_URL_PACIENTES}/${idPaciente}/remedios`)
  const remediosEmUso = data.filter(remedio => remedio.emUso);
  const remediosVencidos = data.filter(remedio => !remedio.emUso);
  const remediosTableBody = document.getElementById("remediosTableBody")
  const remediosAnterioresTableBody = document.getElementById("remediosAnterioresTableBody")
  remediosTableBody.innerHTML = ""
  remediosAnterioresTableBody.innerHTML = ""
  remediosEmUso.forEach(remedio => {
    const tableRow = remediosTableBody.insertRow();
    const name = tableRow.insertCell();
    const mg = tableRow.insertCell();
    const frequencia = tableRow.insertCell();
    const intervalo = tableRow.insertCell();
    const action = tableRow.insertCell();
    name.appendChild(document.createTextNode(remedio.name));
    mg.appendChild(document.createTextNode(remedio.miligramagem));
    frequencia.appendChild(document.createTextNode(remedio.frequencia));
    intervalo.appendChild(document.createTextNode(remedio.intervalo));
    action.innerHTML = `<i onclick="handleEditRemedio('${remedio.id}', '${idPaciente}')" style="cursor: pointer;" title="Encerrar tratamento" class="fa-solid fa-chevron-down"></i>`
  })
  remediosVencidos.forEach(remedio => {
    const tableRow = remediosAnterioresTableBody.insertRow()
    const name = tableRow.insertCell();
    const mg = tableRow.insertCell();
    const frequencia = tableRow.insertCell();
    const intervalo = tableRow.insertCell();
    const action = tableRow.insertCell();
    name.appendChild(document.createTextNode(remedio.name));
    mg.appendChild(document.createTextNode(remedio.miligramagem));
    frequencia.appendChild(document.createTextNode(remedio.frequencia));
    intervalo.appendChild(document.createTextNode(remedio.intervalo));
    action.innerHTML = `<i onclick="handleEditRemedio('${remedio.id}', '${idPaciente}', 'true')" style="cursor: pointer;" title="Retomar tratamento" class="fa-solid fa-chevron-up"></i>`
  })
}

async function handleRegisterRemedio(event) {
  event.preventDefault();
  const idPaciente = JSON.parse(localStorage.getItem('selectedPaciente'))

  if (!idPaciente) {
    alert('Por favor, selecione um paciente!')
    return;
  }

  let nomeremedio = document.getElementById('nomeremedio').value
  let miligramagem = document.getElementById('miligramagem').value;
  let frequencia = document.getElementById('frequencia').value;
  let intervalo = document.getElementById('intervalo').value;

  if (hasInvalidValues(nomeremedio, miligramagem, frequencia, intervalo)) {
    alert('Por favor, preencha todos os campos!')
    return;
  }
  
  let dadosRemedio = {
    name: nomeremedio,
    miligramagem,
    frequencia,
    intervalo,
    pacienteId: idPaciente
  };

  try {
    await axios.post(BASE_URL_REMEDIOS, dadosRemedio)
    getRemedios(idPaciente)
  } catch (err) {
    console.log(err)
  }

}

function logoutMedico() {
  localStorage.removeItem('medicoLogado')
  localStorage.removeItem('selectedPaciente')
  window.location.replace('/web/html/index.html')
}

async function handleEditRemedio(idRemedio, idPaciente, emUso) {
  const { data } = await axios.get(`${BASE_URL_REMEDIOS}/${idRemedio}`);
  if (!data) {
    return;
  }
  await axios.put(`${BASE_URL_REMEDIOS}/${idRemedio}`, {
    ...data,
    emUso: Boolean(emUso),
  })
  getRemedios(idPaciente);
}

function hasInvalidValues(...args) {
  return args.some(value => !value);
}

window.onload = loadPacientes;