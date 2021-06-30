var authorsArr = [];
$(document).ready(
    function() {
        getBooksTable();
        getAllAuthors();
    });

function getAllAuthors() {
    $.get("/api/authors").done(function(authors) {
        authorsArr = authors;
    });
}

function getBooksTable() {
    var authorId = $("#authorIdDiv").text();
    var getRequestUrl = "";
    if (authorId == "") {
        getRequestUrl = '/api/books';
    } else {
        getRequestUrl = '/api/author/' + authorId + '/books';
    }
    $.get(getRequestUrl).done(function(books) {
        books.forEach(function(b) {
            fillBooksTableRow(b);
        });
    });
}

function fillBooksTableRow(b) {
    $("#books_table_body").append(`
                    <tr id=${b.bookId} class="text-center">
                        <td id="bookIdRow" hidden="hidden">${b.bookId}</td>
                        <td>${b.title}</td>
                        <td>${b.author.firstAndLastAuthorName}</td>
                        <td>
                        <button value=${b.bookId} onclick="deleteBook(this)" type="button" class="btn btn-danger">
                            <span class="glyphicon glyphicon-trash"></span>
                        </button>

                        </td>
                        <td>
                        <button value=${b.bookId} onclick="loadBookModalDialog(this)" type="button" class="btn btn-outline-secondary">
                            <span class="glyphicon glyphicon-file"></span>
                        </button>

                        </td>
                    </tr>
                `)
}

function deleteBook(objButton) {
    var bookId = objButton.value;
    $.ajax({
        url: '/api/book/' + bookId,
        type: 'DELETE',
        success: function(result) {
            $("#" + bookId).remove();
        }
    });
}

function loadBookModalDialog(objButton) {
    var firstAndLastAuthorName = "";
    $.get("/api/book/" + objButton.value).done(function(book) {
        firstAndLastAuthorName = book.author.firstAndLastAuthorName;
        loadModal(firstAndLastAuthorName);
        $("#id-input-hidden").val(book.bookId);
        $("#bookTitle").val(book.title);
        $("#selectedAuthor").append('<option value=' + book.author.authorId + ' selected="selected"> ' +
            firstAndLastAuthorName + '</option>');
    });

}

function submitEditBook(e) {
    e.preventDefault();
    var dataArr = {};
    dataArr["bookId"] = $("#id-input-hidden").val();
    dataArr["title"] = $("#bookTitle").val();
    dataArr["authorId"] = $('#selectedAuthor option:selected').val();
    $.post("/api/book/" + dataArr["bookId"], dataArr).done(
        function(messageFromController) {
            console.log("Data Loaded: " + messageFromController);
            $("#formContainer").hide();
            $("#formContainer").after("<div><h2>" + messageFromController + "</h2></div>");
            $("#myModal").on("hidden.bs.modal", function() {
                $('tbody').empty();
                getBooksTable();
            });
        });
}




function loadNewBookModalDialog() {
    var emptyAuthor = "";
    loadModal(emptyAuthor);
    $("#id-input-hidden").val("empty_id_for_new_book");
};



function loadModal(firstAndLastAuthorName) {
    $("#div_for_modal").empty();
    $("#div_for_modal").append(`<div class="modal fade" id="myModal"><div class="modal-dialog modal-dialog-centered">
<div class="modal-content">
<div class="modal-header">
                                                <h4 class="modal-title">Редактировать книгу</h4>
                                                <button type="button" class="close" data-dismiss="modal">×</button>
                                            </div>

                                            <div class="modal-body">
                                                <div id="formContainer" class="container justify-content-center">
                                                    <div class="row ">
                                                        <div class="col">
                                                            <h1>Книга:</h1>
                                                            <form id="edit-form" onsubmit="submitEditBook(event)" class="form">
                                                                <div class="form-row">
                                                                    <input id="id-input-hidden" type="hidden" readonly="readonly"/>
                                                                </div>
                                                                <div class="form-row">
                                                                    <div class="form-col">
                                                                        <div class="form-group">
                                                                            <label for="bookTitle" class="control-label">Наименование книги</label>
                                                                            <input id="bookTitle" type="text" class="form-control"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-row">
                                                                    <div class="form-col">
                                                                        <div class="form-group">
                                                                            <label for="selectedAuthor" class="control-label">Автор</label>
                                                                            <select class="form-control" id="selectedAuthor"></select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-row">
                                                                    <div class="form-col">
                                                                        <br/>
                                                                        <input class="btn btn-outline-success" type="submit" value="Сохранить">
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>
                                            </div>

                                        </div>
                                    </div>
                                </div>`);

    $("#formContainer").show();
    $("#myModal").modal();
    $("option").remove();
    authorsArr.forEach(function(a) {
        if (firstAndLastAuthorName != a.firstAndLastAuthorName) {
            $("#selectedAuthor").append("<option value= " + a.authorId + "> " + a.firstAndLastAuthorName + "</option>");
        }
    });
}