$(function() {

	$("#category1").load("ajax/category-data.html #category1-1", function() {
		$(this).show();
	});

	$(document).on("change", "#category1 > select", function() {
		$("#category2").empty().hide();

		var target = $(this).find("option:selected").attr("data-target");
		var selector = "ajax/category-data.html " + target;

		$("#category2").load(selector, function() {
			$(this).show();
		});
	});

	$(document).on("change", "#category2 > select", function() {
		if ($(this).find("option:selected").index() > 0) {
			var data1 = $("#category1 > select > option:selected").val();
			var data2 = $(this).find("option:selected").val();

			$("#loc").val("[" + data1 + "/" + data2 + "]");
		}
	});
});