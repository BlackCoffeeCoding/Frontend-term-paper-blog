const form = document.getElementById('postForm');
const articles = document.getElementById('articles');
const archive = document.getElementById('archive');
const API_URL = 'http://localhost:8080/api/recipes';

// Загрузка рецептов и архива при старте
window.addEventListener('DOMContentLoaded', () => {
    loadRecipes();
    loadArchive();
});

// Обработка формы создания рецепта
form.addEventListener('submit', function (e) {
    e.preventDefault();

    const newRecipe = {
        title: document.getElementById('title').value,
        category: document.getElementById('category').value,
        author: document.getElementById('author').value,
        content: document.getElementById('content').value,
        archived: false
    };

    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newRecipe)
    })
        .then(res => res.json())
        .then(recipe => {
            addArticleToPage(recipe);
            form.reset();
        })
        .catch(err => console.error('Ошибка создания рецепта:', err));
});

// Загрузка и отображение всех рецептов
function loadRecipes() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            articles.innerHTML = '';
            data.forEach(addArticleToPage);
        })
        .catch(err => console.error('Ошибка загрузки:', err));
}

// Загрузка и отображение архива
function loadArchive() {
    fetch(API_URL + '/archived')
        .then(res => res.json())
        .then(data => {
            archive.innerHTML = '';
            data.forEach(recipe => {
                const li = document.createElement('li');
                li.textContent = recipe.title;
                archive.appendChild(li);
            });
        })
        .catch(err => console.error('Ошибка загрузки архива:', err));
}

// Добавление одного рецепта на страницу
function addArticleToPage(recipe) {
    const article = document.createElement('article');

    const h1 = document.createElement('h1');
    h1.textContent = recipe.title;

    const p1 = document.createElement('p');
    p1.innerHTML = 'Категория: <strong>' + recipe.category + '</strong>';

    const p2 = document.createElement('p');
    p2.innerHTML = 'Автор: <strong>' + recipe.author + '</strong>';

    const p3 = document.createElement('p');
    p3.innerHTML = recipe.content.replace(/\n/g, '<br>');

    const delBtn = document.createElement('button');
    delBtn.textContent = 'Удалить';
    delBtn.className = 'delete-btn';
    delBtn.addEventListener('click', () => {
        fetch(`${API_URL}/${recipe.id}`, {
        method: 'DELETE'
    })
        .then(() => article.remove())
        .catch(err => console.error('Ошибка удаления:', err));
});

const archiveBtn = document.createElement('button');
archiveBtn.textContent = 'Архив';
archiveBtn.className = 'archive-btn';
archiveBtn.addEventListener('click', () => {
    fetch(`${API_URL}/${recipe.id}/archive`, {
        method: 'PUT'
    })
.then(() => {
        const li = document.createElement('li');
        li.textContent = recipe.title;
        archive.appendChild(li);
        article.remove();
    })
        .catch(err => console.error('Ошибка архивирования:', err));
});

article.append(h1, p1, p2, p3, delBtn, archiveBtn);
articles.appendChild(article);
}