$(function () {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/api/book/all",
		success: function (books) {
			books.forEach(function (book) {
				$("table#books").append(`
                   <tr id="${book.id}">
                     <td>${book.id}</td>
       				   <td>${book.title}</td>
                                        <td >       
                                         <a  href="/book?id=${book.id}" class="btn btn-link">Показать</a>
                               				 		
                    </tr> `);
			});

		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/api/author/all",
		success: function (authors) {
			authors.forEach(function (author) {
				$("table#authors").append(`
                   <tr id="${author.id}">
                     <td>${author.id}</td>
       				   <td>${author.name}</td>
            
                   </tr> `);
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
				$("table#genres").append(`
                   <tr id="${genre.id}">
                     <td>${genre.id}</td>
       				   <td>${genre.title}</td>  </tr> `);
			});

		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.error(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});