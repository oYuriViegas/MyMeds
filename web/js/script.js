document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('.containerForm');
    const crmInput = document.getElementById('crm');
    const cpfInput = document.getElementById('cpf');
    const ufSelect = document.getElementById('uf');
    const senhaInput = document.getElementById('senha');

    form.addEventListener('submit', function(event) {

        let hasError = false;
        let errorMessage = '';

        // Validar CRM - deve ser entre 7 e 8 caracteres
        if (crmInput.value.trim().length < 7 || crmInput.value.trim().length > 8) {
            errorMessage += 'O CRM deve conter entre 7 e 8 caracteres.\n';
            hasError = true;
        }

        // Validar senha - deve ter mais de 6 caracteres
        if (senhaInput.value.trim().length <= 6) {
            errorMessage += 'A senha deve conter mais de 6 caracteres.\n';
            hasError = true;
        }

        // Se houver erro, evita o envio do formulário e mostra os erros
        if (hasError) {
            event.preventDefault();
            alert(errorMessage);
        } else {
            // Se não houver erro, redireciona para a próxima página
            window.location.href = "html/acessomedico.html";
        }
    });
});

