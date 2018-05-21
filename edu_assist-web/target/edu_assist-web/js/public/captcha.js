$(function(){
	var pageContext = $('#PageContext').val();
	$('#kaptchaImage').click(
			function() {// 生成验证码
				$(this).hide().attr(
						'src',
						pageContext + '/public/captcha-image.do?'
								+ Math.floor(Math.random() * 100)).fadeIn();
			});
	function changeCode() {
		$('#kaptchaImage').hide().attr(
				'src',
				pageContext + '/public/captcha-image.do?'
						+ Math.floor(Math.random() * 100)).fadeIn();
	}
})
