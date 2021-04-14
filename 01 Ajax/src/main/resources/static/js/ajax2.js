var state = false;
var loading = $("#loading");
var loaded = 8;

function f() {
    if (state) {
        loading.addClass("hidden");
        state = false;
    } else {
        loading.removeClass("hidden");
        state = true;

    }

    //TODO Should block re-download for up down scrolling
    $(window).on('scroll', function () {
        if (($(this).scrollTop() + $(window).height()) > document.documentElement.scrollHeight - 100) {
            f();
            $.get("/posts", function (data) {
                $("#main").append(data);
                loaded = loaded + 5;
                f();
            })
        }
    });
}