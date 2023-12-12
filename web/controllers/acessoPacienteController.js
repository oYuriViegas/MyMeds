const BASE_URL_PACIENTES = 'http://localhost:8080/pacientes';

function logoutPaciente() {
  localStorage.removeItem('pacienteLogado')
  window.location.replace('/web/html/index.html')
}

async function getRemedios() {
  const idPaciente = localStorage.getItem('pacienteLogado')
  const { data } = await axios.get(`${BASE_URL_PACIENTES}/${idPaciente}/remedios`)
  const remediosEmUso = data.filter(remedio => remedio.emUso);
  const remediosTableBody = document.getElementById("remediosTableBody")
  remediosTableBody.innerHTML = ""
  remediosEmUso.forEach(remedio => {
    const tableRow = remediosTableBody.insertRow()
    const name = tableRow.insertCell()
    const mg = tableRow.insertCell()
    const frequencia = tableRow.insertCell()
    const intervalo = tableRow.insertCell()
    name.appendChild(document.createTextNode(remedio.name));
    mg.appendChild(document.createTextNode(remedio.miligramagem));
    frequencia.appendChild(document.createTextNode(remedio.frequencia));
    intervalo.appendChild(document.createTextNode(remedio.intervalo));
  })
}

window.onload = getRemedios;