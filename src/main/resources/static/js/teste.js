document.addEventListener('DOMContentLoaded', () => {
    const dropdownMenu = document.getElementById('dropdown-menu');
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.getElementById('main-content'); // Selecione o conteúdo principal
    const kkk = {
        '1': 'O-1',
        '2': 'O-2',
        '3': 'O-3',
        '4': 'O-4'
    };

    // Lógica do dropdown no menu lateral
    document.addEventListener('click', (e) => {
        const clickedButton = e.target.closest('.dropdown-toggle');
        if (clickedButton) {
            const id = clickedButton.id;
            const h1ToShowId = kkk[id];
            const h1ToShow = document.getElementById(h1ToShowId);

            // Toggle o dropdown-menu
            dropdownMenu.classList.toggle('hidden');

            // Adiciona ou remove a classe para mover o conteúdo
            if (!dropdownMenu.classList.contains('hidden')) {
                mainContent.classList.remove('ml-16');  // Remove classes anteriores se houver
                mainContent.classList.add('ml-48');  // Move o conteúdo 8rem para a direita (ajuste conforme necessário)
            } else {
                mainContent.classList.remove('ml-48')
                mainContent.classList.add('ml-16');
            }

            // Esconder todos os blocos e mostrar apenas o relacionado ao botão clicado
            document.querySelectorAll('#dropdown-menu .ww').forEach(h1 => {
                h1.classList.add('hidden');
            });

            // Mostrar o conteúdo correspondente
            if (h1ToShow) {
                h1ToShow.classList.remove('hidden');
            } else {
                console.log('Elemento com o id ' + h1ToShowId + ' não encontrado.');
            }
        }
    });

    // Abrir/fechar o menu lateral no mobile
    mobileMenuButton.addEventListener('click', () => {
        sidebar.classList.toggle('menu-active');
    });
});