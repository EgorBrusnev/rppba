// $(".stage-form").submit((e) => {
//     // e.preventDefault();
//     const test = new FormData(e.target);
//     console.log("data: ", test);
//     e.preventDefault();
// });

$("form").submit(function (e) {
    window.location.reload();
    console.log("submit");
});
