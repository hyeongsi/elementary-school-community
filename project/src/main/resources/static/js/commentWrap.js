$(document).ready(function() {
  $("table").on("keyup", "textarea", function(e) {
    $(this).css("height", "auto");
    $(this).height(this.scrollHeight);
  });
  $("table").find("textarea").keyup();
})