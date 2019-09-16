


const searchModal = function() {
    $.get({'url' : '/posts.json'})
        .done(function (data) {
        let query = document.getElementById("Squery").value;
        let Users = [];
        let Posts = [];

        data.forEach(function (post) {
            if (post.user.email.toLowerCase().includes(query.toLowerCase())||
                post.user.username.toLowerCase().includes(query.toLowerCase())){
                let update = true;
                Users.forEach(function(postuser){
                    if(postuser.user.id === post.user.id){
                        update = false;
                    }
                });
                if(update === true){
                    Users.push(post);
                }
            }
            if(post.title.toLowerCase().includes(query.toLowerCase())||
                post.body.toLowerCase().includes(query.toLowerCase())) {
                Posts.push(post);
            }
        });
        if(Users.length > 0) {
            console.log(Users);
            let html = '';
            Users.forEach(function (post) {

                html += '<a href="'+post.user.id+'"><div class="card">';
                html += '<img class="col-2" src="' + post.user.photo + '" alt="error"/>';
                html += '<h1 class="card-title">' + post.user.username + '</h1><br/>';
                html += '<p class="card-text">' + post.user.email + '</p>';
                html += '</div></a>';
            });
            $("#Users").removeClass("hidden").html(html);
        }else{
            $("#Users").addClass("hidden")
        }
            if(Posts.length > 0) {
                console.log(Posts);
                let html = '';
                Posts.forEach(function (post) {
                    html += '<a href="/post/'+post.id+'"><div class="card">';
                    html += '<h1 class="card-title">' + post.title + '</h1><br/>';
                    html += '<p class="card-text">Author: ' + post.user.username + '</p>';
                    html += '</div></a>';
                });
            $("#Posts").removeClass("hidden").html(html);
            }else{
            $("#Posts").addClass("hidden")
            }
    });
};