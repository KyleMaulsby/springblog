


const searchModal = function() {
    let query = document.getElementById("Squery").value;
    if(query.length > 0) {
    $.get({'url': '/posts.json'})
        .done(function (data) {
            let Users = [];
            let Posts = [];

            data.forEach(function (post) {
                if (post.user.email.toLowerCase().includes(query.toLowerCase()) ||
                    post.user.username.toLowerCase().includes(query.toLowerCase())) {
                    let update = true;
                    Users.forEach(function (postuser) {
                        if (postuser.user.id === post.user.id) {
                            update = false;
                        }
                    });
                    if (update === true) {
                        Users.push(post);
                    }
                }
                if (post.title.toLowerCase().includes(query.toLowerCase()) ||
                    post.body.toLowerCase().includes(query.toLowerCase())) {
                    Posts.push(post);
                }
            });
            if (Users.length > 0) {
                console.log(Users);
                let html = '';
                Users.forEach(function (post) {

                    html += '<a class="modalA btn btn-light" href="/profile/' + post.user.id + '"><div class="card modalCard">';
                    html += '<img class="col-2" src="' + post.user.photo + '" alt="error"/>';
                    html += '<div class="col-10 row"><h4 class="card-text col-12 modalResultTitle">' + post.user.username + '</h4><br/>';
                    html += '<p class="card-text modalSearchResult">' + post.user.email + '</p>';
                    html += '</div></div></a>';
                });
                $("#userD").removeClass("hidden");
                $("#Users").html(html);
            } else {
                $("#userD").addClass("hidden")
            }
            if (Posts.length > 0) {
                console.log(Posts);
                let html = '';
                Posts.forEach(function (post) {
                    html += '<a class="modalA btn btn-light" href="/posts/' + post.id + '"><div class="card modalCard">';
                    html += '<div class="col-12 row"><h4 class="card-text col-12 modalResultTitle">' + post.title + '</h4><br/>';
                    html += '<p class="card-text modalSearchResult">Author: ' + post.user.username + '</p>';
                    html += '</div></div></a>';
                });
                $("#postD").removeClass("hidden");
                $("#Posts").html(html);
            } else {
                $("#postD").addClass("hidden")
            }
        });
    }else{
        $("#postD").addClass("hidden");
        $("#userD").addClass("hidden");
    }
};