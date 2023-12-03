function handleDoctorRegistrationInputs() {
  let nome = document.getElementById('nome').value;
  let senha = document.getElementById('senha').value;
  let crm = document.getElementById('crm').value;
  
  let dadosDoctor = {
    nome: nome,
    senha: senha,
    crm: crm
  };
  console.log(dadosDoctor);
  fetch('http://localhost:8080/medicos', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dadosDoctor)
  })
  .then(response => response.json())
  .then(data => console.log(data))
  .catch((error) => {
    console.error('Error:', error);
  });
}