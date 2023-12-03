// Endereço do servidor onde o controlador Spring Boot está rodando
const baseUrl = 'http://localhost:8080/remedios';

// Obter todos os remédios
function getAllRemedios() {
    axios.get(baseUrl)
        .then(response => console.log(response.data))
        .catch(error => console.error(error));
}

// Criar um novo remédio
function createRemedio(remedio) {
    axios.post(baseUrl, remedio)
        .then(response => console.log(response.data))
        .catch(error => console.error(error));
}

// Atualizar um remédio
function updateRemedio(id, remedio) {
    axios.put(`${baseUrl}/${id}`, remedio)
        .then(response => console.log(response.data))
        .catch(error => console.error(error));
}

// Deletar um remédio
function deleteRemedio(id) {
    axios.delete(`${baseUrl}/${id}`)
        .then(() => console.log('Remédio deletado com sucesso'))
        .catch(error => console.error(error));
}
