/**
 * http://usejsdoc.org/
 */

$(function() {
	$('#submit').click(function() {
			
				$.ajax({
					
					url:'ping',
					success : function(data) {
						var ans = $('input:radio:checked').map(function(i, el) {
							return $(el).val();
						});

						var que = $(".question").map(function() {
							return $(this).attr("alt");
						});
						var taskId = $('#taskId').val();
						var opponent = $('#opponent').val();
						// alert(opponent);
						var q1 = que[0], q2 = que[1], q3 = que[2], q4 = que[3], q5 = que[4];
						var a1 = ans[0], a2 = ans[1], a3 = ans[2], a4 = ans[3], a5 = ans[4];
						$.ajax({

							url : 'submit',
							method : 'post',

							data : {
								taskId : taskId,
								opponent : opponent,
								q1 : q1,
								q2 : q2,
								q3 : q3,
								q4 : q4,
								q5 : q5,
								a1 : a1,
								a2 : a2,
								a3 : a3,
								a4 : a4,
								a5 : a5
							},

							success : function(data) {
								alert("Success");
							},
							error : function() {
								// modal3.style.display="none";
								alert("Try Again Later");
							}
						});

					},
					error : function() {
						// modal3.style.display="none";
						alert("Your data is saved. Submit it later;");
					}
					
				});
		
	
	});
	$('#exit').click(function() {
		window.location("/");
	});

});














