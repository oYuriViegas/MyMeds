const BASE_URL_PACIENTES = 'http://localhost:8080/pacientes';

function handleLoginInputs(event) {
  event.preventDefault();
  let email = document.getElementById('email').value;
  let senha = document.getElementById('senha').value;
  
  let dadosLogin = {
    email,
    senha
  };
  console.log(dadosLogin);
  axios.post(`${BASE_URL_PACIENTES}/login`, dadosLogin)
  .then(response => {
    localStorage.setItem('pacienteLogado', response.data.id)
    window.location.replace('/web/html/acessopaciente.html')
  })
  .catch(error => {
    alert(error.response.data);
  });
}