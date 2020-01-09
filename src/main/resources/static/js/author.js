let url = "/api/author";
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
                let author = "<tr>" +
                    "<td>" + value.id + "</td>" +
                    "<td>" + value.first_name + "</td>" +
                    "<td>" + value.last_name + "</td>" +
                    "<td>" +
                        "<button type='button' class='btn btn-primary' onclick='getAuthor(\"" + value.id + "\")'>Редактировать</button>" +
                        "<button type='button' class='btn btn-secondary' onclick='deleteAuthor(\"" + value.id + "\")'>Удалить</button>" +
                    "</td>" +
                    "</tr>";
                table.append(author);
            });
        });
}

function newAuthor() {
    $("#modal-title").text("Новый автор");
    $("#inputId").val("");
    $("#inputFirstname").val("");
    $("#inputLastname").val("");
    $('#myModal').modal();
}

function saveAuthor() {
    let type = "POST";
    let formData = {
        id: $("#inputId").val(),
        first_name: $("#inputFirstname").val(),
        last_name: $("#inputLastname").val()
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

function deleteAuthor(id) {
    if (confirm('Вы уверены, что хотите удалить?')) {
        $.ajax({
            url: url + "/" + id,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
    }
}

function getAuthor(id) {
    $("#modal-title").text("Редактировать автора");
    $.get(url + "/" + id,
        function (data) {
            $("#inputId").val(data.id);
            $("#inputFirstname").val(data.first_name);
            $("#inputLastname").val(data.last_name);
        }).done(function () {
        $('#myModal').modal('show');
    });
}
