window.addEventListener('load', function(e) {
    console.log('document loaded');
    init();
});

function init() {
    document.filmForm.lookup.addEventListener('click', function(event) {
        event.preventDefault();
        var filmId = document.filmForm.filmId.value;
        if (!isNaN(filmId) && filmId > 0) {
            getFilm(filmId);
        }
    })
    document.getElementById('addFilm').addEventListener('click', function(evt){
        event.preventDefault();
        addFilm();
    });
}

function getFilm(filmId) {
    // TODO:
    // * Use XMLHttpRequest to perform a GET request to "api/films/"
    // with the filmId appended.
    // * On success, if a response was received parse the film data
    // and pass the film object to displayFilm().
    // * On failure, or if no response text was received, put "Film not found"
    // in the filmData div.
    let xhr = new XMLHttpRequest();
    // xhr.open('Get', 'api/films/'+filmId);
    xhr.open('GET', `api/films/${filmId}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // /TODO
                console.log('found film ' + filmId);
                let film = JSON.parse(xhr.responseText);
                displayFilm(film);
            } else {
                document.getElementById('filmData').textContent = 'Film not found';
            }
        }
    };

    xhr.send(null);

}

function displayFilm(film) {
    var dataDiv = document.getElementById('filmData');
    dataDiv.textContent = '';
    // TODO:
    // * Create and append elements to the data div to display:
    // * Film title (h1) and description (blockquote).
    // * Rating, release year, and length as an unordered list.
    let h1 = document.createElement("h1");
    h1.textContent = film.title;
    dataDiv.appendChild(h1);
    let ul = document.createElement("ul");
    let li = document.createElement("li");
    li.textContent = film.rating;
    ul.appendChild(li);
    li = document.createElement("li");
    li.textContent = film.releaseYear;
    ul.appendChild(li);
    li = document.createElement("li");
    li.textContent = film.length + " minutes";
    ul.appendChild(li);
    dataDiv.appendChild(ul);
    getAndDisplayFilmActors(film.id);
}

function getAndDisplayFilmActors(filmId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `api/films/${filmId}/actors`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log('found actors for film ' + filmId);
                let actors = JSON.parse(xhr.responseText);
                displayFilmActors(actors);
            }
        }
    };
    xhr.send(null);
}

function displayFilmActors(actors) {
    var actorDiv = document.getElementById('actorData');
    actorDiv.textContent = '';
    let ul = document.createElement("ul");
    for (let i = 0; i < actors.length; i++) {
        let li = document.createElement("li");
        li.textContent = actors[i].firstName + " " + actors[i].lastName;
        ul.appendChild(li);
    }
    actorDiv.appendChild(ul);
}

function addFilm() {
    console.log('addFilm() called.');
    let f = document.addFilmForm;
    let film = {};
    film.title = f.title.value;
    film.description = f.description.value;
    film.releaseYear = f.releaseYear.value;
    film.length = f.length.value;
    film.rating = f.rating.value;

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/films');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                let addedFilm = JSON.parse(xhr.responseText);
                console.log(addedFilm);
                displayFilm(addedFilm);
            }
        }
    }
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(film));
}
