let url = "/api/genre";
let form = $("#form");

$(function () {
    getAll();
});

function getAll() {
    $.get(url,
        function (data) {
            let table = $("#container");
            table.empty();
            $.each(data, function (key, value) {
                let genre = "<tr>" +
                    "<td>" + value.id + "</td>" +
                    "<td>" + value.genre_name + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-primary' onclick='getGenre(\"" + value.id + "\")'>Редактировать</button>" +
                    "<button type='button' class='btn btn-secondary' onclick='deleteGenre(\"" + value.id + "\")'>Удалить</button>" +
                    "</td>" +
                    "</tr>";
                table.append(genre);
            });
        });
}

function newGenre() {
    $("#modal-title").text("Новый жанр");
    $("#inputId").val("");
    $("#inputGenrename").val("");
    $('#myModal').modal();
}

function saveGenre() {
    let type = "POST";
    let formData = {
        id: $("#inputId").val(),
        genre_name: $("#inputGenrename").val()
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

function deleteGenre(id) {
    if (confirm('Вы уверены, что хотите удалить?')) {
        $.ajax({
            url: url + "/" + id,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
    }
}

function getGenre(id) {
    $("#modal-title").text("Редактировать жанр");
    $.get(url + "/" + id,
        function (data) {
            $("#inputId").val(data.id);
            $("#inputGenrename").val(data.genre_name);
        }).done(function () {
        $('#myModal').modal('show');
    });
}
