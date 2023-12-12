const BASE_URL_PACIENTES = 'http://localhost:8080/pacientes';

function handlePatientRegistrationInputs(event) {
  event.preventDefault();
  let nome = document.getElementById("nome").value;
  let email = document.getElementById("email").value;
  let senha = document.getElementById("senha").value;
  let crm = document.getElementById("crm").value;
  
  if (hasInvalidValues(nome, email, senha, crm)) {
    alert('Por favor, preencha todos os dados!');
    return;
  }

  let dadosPaciente = {
    nome,
    email,
    senha,
    crm
  };

  axios.post(BASE_URL_PACIENTES, dadosPaciente)
    .then(response => {
      window.location.replace('/web/html/loginpaciente.html')
    })
    .catch(error => {
      alert(error.response.data)
    });
}

function hasInvalidValues(...args) {
  return args.some(value => !value);
}