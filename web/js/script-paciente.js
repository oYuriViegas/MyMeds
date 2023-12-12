function handlePatientRegistrationInputs(event) {
  event.preventDefault();
  let nome = document.getElementById("nome").value;
  let email = document.getElementById("email").value;
  let senha = document.getElementById("senha").value;
  let crm = document.getElementById("crm").value;

  let dadosPaciente = {
    nome: nome,
    email: email,
    senha: senha,
    crm: crm
  };

  console.log(dadosPaciente);
  let url = 'http://localhost:8080/pacientes';
  axios.post(url, dadosPaciente)
    .then(response => {
      console.log(response.data);
    })
    .catch(error => {
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


