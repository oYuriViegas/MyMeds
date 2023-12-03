const apiBaseUrl = "http://localhost:8080/medicos"

function getPacientes(id){
    return axios.get(`${apiBaseUrl}/${id}/pacientes`);
}



