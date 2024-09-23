function toggleNavbar(collapseID) {
    const navbar = document.getElementById(collapseID);
    if (navbar.classList.contains('hidden')) {
        navbar.classList.remove('hidden');
        navbar.classList.add('block');
    } else {
        navbar.classList.remove('block');
        navbar.classList.add('hidden');
    }
}

// Fecha o menu se clicar fora dele
window.addEventListener('click', (event) => {
    const navbar = document.getElementById('example-collapse-navbar');
    if (!navbar.contains(event.target) && !event.target.matches('button')) {
        navbar.classList.add('hidden');
        navbar.classList.remove('block');
    }
});