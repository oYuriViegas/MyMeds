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
  fetch('http://api.mymeds.com/pacientes', {
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

