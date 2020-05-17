$(document).delegate("[action|='deleteComment']", 'click', function () {
	var parent = $(this).parent().parent();
	var id = parent.children("td:nth-child(1)");

	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/api/comment/" + id.html(),
		success: function () {
			$(parent).fadeOut('slow', function () {
				$(parent).remove();
			});
		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});
$(document).delegate("[action|='deleteBook']", 'click', function () {
	var id = $("#bookId");
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/api/book/" + id.html(),
		success: function () {
			window.location.href = window.location.origin;
		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

$(document).delegate("[action|='addNewComment']", 'click', function () {
	var data = {};
	data["bookId"] = $("#bookId").html();
	data["text"] = $("#textComment").val();
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: "/api/comment",
		data: JSON.stringify(data),
		success: function (comment) {
			$("table#comments").append(`
                   <tr id="${comment.id}">
       				   <td>${comment.id}</td>
                       <td>${comment.text}</td>
                                       <td >                       				 		
                <button type="button" action="deleteComment" class="btn btn-link">Удалить комментарий</button>
       				 	</td>
                   </tr> `);
			$("#textComment").val("");
			$(".collapse").collapse('hide');
		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});
$(document).delegate("[action|='editTitle']", 'click', function () {
	var data = {};
	var newTitle = $("#newTitle").val();
	data["id"] = $("#bookId").html();
	data["title"] = newTitle;
	$.ajax({
		type: "PATCH",
		contentType: "application/json",
		url: "/api/book",
		data: JSON.stringify(data),
		success: function () {
			$("#bookTitle").html(newTitle);
			$(".collapse").collapse('hide');
			$("#newTitle").val("");
		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

$(function () {
	let searchParams = new URLSearchParams(window.location.search);
	let id = searchParams.get('id');

	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/api/book/" + id,
		success: function (book) {
			$("#bookTitle").html(book.title);
			$("#bookId").html(book.id);

			var authors = "";
			var genres = "";
			book.authors.forEach(function (author) {
				authors = authors + ' ' + author.name;
			});
			book.genres.forEach(function (genre) {
				genres = genres + ' ' + genre.title;
			});
			$("#authors").html(authors);
			$("#genres").html(genres);
			book.comments.forEach(function (comment) {
				$("table#comments").append(`
                   <tr id="${comment.id}">
       				   <td>${comment.id}</td>
                       <td>${comment.text}</td>
                                       <td >                       				 		
                <button type="button" action="deleteComment" class="btn btn-link">Удалить комментарий</button>
       				 	</td>
                   </tr> `);
			});

		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});

});