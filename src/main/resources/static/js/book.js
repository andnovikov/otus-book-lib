let url = "/api/book";
let urlAuthor = "/api/author";
let urlGenre = "/api/genre";

$(function () {
    getAll();
});

function getAll() {
    $.get(url,
        function (data) {
            let table = $("#container");
            table.empty();
            $.each(data, function (key, value) {
                let book = "<tr>" +
                    "<td>" + value.id + "</td>" +
                    "<td>" + value.genre.genre_name + "</td>" +
                    "<td>" + value.author.first_name + " " + value.author.last_name + "</td>" +
                    "<td>" + value.title + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-primary' onclick='getBook(\"" + value.id + "\")'>Редактировать</button>" +
                    "<button type='button' class='btn btn-secondary' onclick='deleteBook(\"" + value.id + "\")'>Удалить</button>" +
                    "</td>" +
                    "</tr>";
                table.append(book);
            });
        });
}

function newBook() {
    $("#modal-title").text("Новая книга");
    $("#inputId").val("");
    $("#inputTitle").val("");

    $.get(urlAuthor,
        function (data) {
            let select = $("#author-input")
            select.empty();
            $.each(data, function (key, value) {
                select.append(
                    $("<option></option>")
                        .attr("value", value.id)
                        .text(value.first_name + " " + value.last_name));
            });
        });

    $.get(urlGenre,
        function (data) {
            let select = $("#genre-input");
            select.empty();
            $.each(data, function (key, value) {
                select.append(
                    $("<option></option>")
                        .attr("value", value.id)
                        .text(value.genre_name));
            });
        });
    $('#myModal').modal();
}

function saveBook() {
    let type = "POST";
    let formData = {
        id: $("#inputId").val(),
        genre: {
            id: $("#genre-input").val()
        },
        author: {
            id: $("#author-input").val()
        },
        title: $("#inputTitle").val()
    };
    if (formData.id !== "") {
        url += "/" + formData.id;
        type = "PUT";
    }
    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(formData)
    }).done(function () {
        $('#myModal').modal('hide');
        location.reload();
    });
}

function deleteBook(id) {
    if (confirm('Вы уверены, что хотите удалить?')) {
        $.ajax({
            url: url + "/" + id,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
    }
}

function getBook(id) {
    $("#modal-title").text("Редактировать книгу");
    $.get(urlAuthor,
        function (data) {
            let select = $("#author-input")
            select.empty();
            $.each(data, function (key, value) {
                select.append(
                    $("<option></option>")
                        .attr("value", value.id)
                        .text(value.first_name + " " + value.last_name));
            });
        });

    $.get(urlGenre,
        function (data) {
            let select = $("#genre-input");
            select.empty();
            $.each(data, function (key, value) {
                select.append(
                    $("<option></option>")
                        .attr("value", value.id)
                        .text(value.genre_name));
            });
        });

    $.get(url + "/" + id,
        function (data) {
            $("#inputId").val(data.id);
            $("#genre-input").val(data.genre.id);
            $("#author-input").val(data.author.id);
            $("#inputTitle").val(data.title);
        }).done(function () {
        $('#myModal').modal('show');
    });
}
