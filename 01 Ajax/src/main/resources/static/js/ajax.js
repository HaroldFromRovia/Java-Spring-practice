cash = new Map();

$("#brends").change(function() {
    var loading = $("#loading");
    loading.removeClass("hidden");
    f1();
})

function f1() {
    id = $("#brends").children(":selected").attr("id");
    if (!cash.has(id)) {
        $.get("/models/" +  id, function (data) {
            cash.set(id, data.data);
            $("#models").html(cash.get(id))
            loading.addClass("hidden");
        })
    }
    else {
        $("#models").html(cash.get(id))
        loading.addClass("hidden");
    }
}