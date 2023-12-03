function handleMedsRegistrationInputs() {
  let remedio = document.getElementById('remedio').value;
  let intervalo = document.getElementById('intervalo').value;
  let miligramagem = document.getElementById('miligramagem').value;
  let frequencia = document.getElementById('frequencia').value;

  
  let dadosMeds = {
    remedio: remedio,
    intervalo: intervalo,
    miligramagem: miligramagem,
    frequencia: frequencia
  };
  console.log(dadosMeds);
  
  fetch('http://localhost:8080/remedios', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dadosMeds)
  })
  .then(response => response.json())
  .then(data => console.log(data))
  .catch((error) => {
    console.error('Error:', error);
  });
}
