const BASE_URL_MEDICOS = 'http://localhost:8080/medicos';

function handleLoginInputs(event) {
  event.preventDefault();
  let crm = document.getElementById('crm').value;
  let senha = document.getElementById('senha').value;
  
  let dadosLogin = {
    crm,
    senha
  };
  axios.post(`${BASE_URL_MEDICOS}/login`, dadosLogin)
  .then(response => {
    localStorage.setItem('medicoLogado', response.data.id)
    window.location.replace('/web/html/acessomedico.html')
  })
  .catch(error => {
    alert(error.response.data)
  });
}