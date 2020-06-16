$(function () {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/api/author/all",
		success: function (authors) {
			authors.forEach(function (author) {
				$("select#authors-select").append(`
                   <option value="${author.id}"> ${author.name}</option>`
				);
			});

		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/api/genre/all",
		success: function (genres) {
			genres.forEach(function (genre) {
				$("select#genres-select").append(`
                   <option value="${genre.id}"> ${genre.title}</option>`
				);
			});

		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
	$("#create-form").submit(function (event) {
		event.preventDefault();
		var data = {};
		data["id"] = null;
		data["title"] = $("#bookTitle").val();

		var genres = [];
		$("#genres-select :selected").each(function () {
			var genre = {};
			genre["id"] = $(this).val();
			genre["title"] = $(this).html();
			genres.push(genre);
		});
		data["genres"] = genres;
		data["comments"] = [];
		var authors = [];
		$("#authors-select :selected").each(function () {
			var author = {};
			author["id"] = $(this).val();
			author["name"] = $(this).html();
			authors.push(author);
		});
		data["authors"] = authors;
		$.ajax({
			type: 'POST',
			contentType: "application/json",
			url: "/api/book",
			data: JSON.stringify(data),
			success: function (data) {
				var url = "/";
				$(location).attr('href', url);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
			}
		})
	});
});

