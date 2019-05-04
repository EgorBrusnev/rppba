let requests = [];

$("#request-form").submit((e) => {
    e.preventDefault();
    console.log("submit", e);
});

$("#add-request").click(e => {
    e.preventDefault();
    let object = {};
    let select = $("form select");
    let productId = select.val();
    let productText = select.text().trim();
    let count = $("input[name='qty']").val();
    object = {productText, count, productId};
    if (requests.filter(el => el.productId === productId).length > 0) {
        $("a[req-id=" + productId + "]").parent("li").remove();
        requests = requests.filter(el => el.productId !== productId);
    }
    requests.push(object);
    $("#requestList").append(
        '<li class="list-group-item d-flex justify-content-between align-items-center">' +
        object.productText + ' ' + object.count +
        '   <a req-id="' + productId + '" class="btn remove-request" href="#">Remove</a> ' +
        '</li>');
});

$(document).on("click", ".remove-request", (e) => {
    e.preventDefault();
    let id = $(e.currentTarget).attr("req-id");
    requests = requests.filter(el => el.productId !== id);
    $(e.currentTarget).parent("li").remove();
});

$("#submit-request").click(e => {
    e.preventDefault();
    $.ajax({
        url: "/order/new",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(requests),
        success(data) {
            console.log("success");
            window.location.reload();
        },
        error(err) {
            console.error(err);
        }
    })
});