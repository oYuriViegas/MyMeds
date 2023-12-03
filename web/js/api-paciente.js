const apiBaseUrl = "http://localhost:8080/pacientes"

function getRemedios(id){
    return axios.get(`${apiBaseUrl}/${id}/remedios`);
}

