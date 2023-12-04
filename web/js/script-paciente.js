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
function handleLoginInputs() {
  let email = document.getElementById('email').value;
  let senha = document.getElementById('senha').value;
  
  let dadosLogin = {
    email: email,
    senha: senha
  };
  console.log(dadosLogin);
  fetch('http://localhost:8080/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dadosLogin)
  })
  .then(response => {
    if(response.status === 200) {
      window.location.href = '/home';
    } else {
      console.error('Erro de login');
    }
  })
  .catch((error) => {
    console.error('Error:', error);
  });
}


