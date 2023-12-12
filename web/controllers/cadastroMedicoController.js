const BASE_URL_MEDICOS = 'http://localhost:8080/medicos';

function handleDoctorRegistrationInputs(event) {
  event.preventDefault();
  let senha = document.getElementById('senha').value;
  let crm = document.getElementById('crm').value;
  let uf = document.getElementById('uf').value;

  if (hasInvalidValues(senha, crm, uf)) {
    alert('Por favor, preencha seus dados!');
    return;
  }

  const buttonSubmit = document.getElementById('btn-submit-register');
  
  let dadosDoctor = {
    senha,
    crm,
    uf
  };
  
  buttonSubmit.innerText = 'Consultando CRM'
  buttonSubmit.setAttribute('style', "opacity: 60%; cursor: default") 
  buttonSubmit.setAttribute('disabled', true) 
  axios.post(BASE_URL_MEDICOS, dadosDoctor)
  .then(response => {
    console.log(response.data);
    window.location.replace('/web/html/loginmedico.html')
  })
  .catch(error => {
    alert(error.response.data)
  });
}

function hasInvalidValues(...args) {
  return args.some(value => !value);
}