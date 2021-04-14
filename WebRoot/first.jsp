
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    

<!-- https://materializecss.com -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- https://fontawesome.com/ -->
<link rel="stylesheet" href="css/templatemo-style.css">
<title>Home</title>

</head>

<body>
<div class="container">
	<div class="row">
		<div>
			<i class="far fa-comments fa-5x tm-site-icon"></i>
			<h1 class="tm-site-name">KP{0,1}背包</h1>
		</div>
	</div>
	<div class="row">
		<div class="tm-intro">
			<div class="col-sm-12 col-md-8 mb-md-0 mb-4 tm-intro-left">
				<p class="mb-0">背包问题（Knapsack Problem，KP）是NP Complete问题，也是一个经典的组合优化问题，有着广泛而重要的应用背景。{０-1}背包问题（{0-1 }Knapsack Problem，{0-1}KP）是最基本的KP问题形式，它的一般描述为：从若干具有价值系数与重量系数的物品（或项）中，选择若干个装入一个具有载重限制的背包，如何选择才能使装入物品的重量系数之和在不超过背包载重前提下价值系数之和达到最大？

        D{0-1} KP 是经典{ 0-1}背包问题的一个拓展形式，用以对实际商业活动中折扣销售、捆绑销售等现象进行最优化求解，达到获利最大化。D{0-1}KP数据集由一组项集组成，每个项集有3项物品可供背包装入选择，其中第三项价值是前两项之和，第三项的重量小于其他两项之和，算法求解过程中，如果选择了某个项集，则需要确定选择项集的哪个物品，每个项集的三个项中至多有一个可以被选择装入背包，D{0-1} KP问题要求计算在不超过背包载重量 的条件下，从给定的一组项集中选择满足要求装入背包的项，使得装入背包所有项的价值系数之和达到最大；D{0-1}KP instances数据集是研究D{0-1}背包问题时，用于评测和观察设计算法性能的标准数据集；动态规划算法、回溯算法是求解D{0-1}背包问题的经典算法。</p>
			</div>
			<div class="col-sm-12 col-md-4">
				<em>
					<p class="tm-highlight">&ldquo;折扣0/1背包&rdquo;</p>
				</em>
			</div>
		</div>
	</div>

	 <form action="firstcheck.jsp" method="GET" name="fm"> 
			
			<div class="col-12 tm-respondent-info">
				<hr>
				<h2 class="tm-question-header tm-question-header-mt">KP{0,1}功能测试：</h2>
				<div class="row">
					<div class="col-md-6 col-lg-4">
						<div class="form-group">
							<label>请选择您要查看的文件名：</label>
								<select class="" name="filecount" id="occupation">
								<option value="select">请选择</option>
								<option value="1">1:idkp1-10.txt文件</option>
								<option value="2">2:sdkp1-10.txt文件</option>
								<option value="3">3:udkp1-10.txt文件</option>
								<option value="4">4:wdkp1-10.txt文件</option>
							     </select>
						</div>
						<div class="form-group">
							<label>请输入您选择的第几组数据：</label>
							<input type="email" class="form-control browser-default" id="inputEmail1" name="tempcount"/>
						</div>
					</div>
					<div class="col-md-6 col-lg-4">
						<div class="tm-choice-mt">
							<label>   请选择您要采用的算法：</label>
							<label class="tm-q-choice">
								<input type="radio" name="gender" class="with-gap" value="1" />
								<span>动态规划算法</span>
							</label>
							<label class="tm-q-choice">
								<input type="radio" name="gender" class="with-gap" value="2" />
								<span>回溯算法</span>
							</label>
						</div>
						<!-- <div class="input-field col tm-dropdown-container">

						</div> -->
					</div>
					<div class="col-md-12 col-lg-4">
						<label for="message" class="mb-3">结果展示</label>
						<textarea class="p-3" name="message" id="message" cols="30" rows="3"></textarea>
					</div>
				</div>
			</div>
			<div class="col-12 text-center tm-submit-container">
				<button type="submit" href="#" class="btn btn-primary tm-btn-submit" onclick=" checkpost();">提交</button>
			</div>
			<div class="col-12">
				<hr>
			</div>
		</div> <!-- row -->
	 </form> 
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/materialize.min.js"></script>
<script>

	// Parallax function
	var background_image_parallax = function ($object, multiplier) {
		multiplier = typeof multiplier !== 'undefined' ? multiplier : 0.5;
		multiplier = 1 - multiplier;
		var $doc = $(document);
		$object.css({ "background-attatchment": "fixed" });
		$(window).scroll(function () {
			var from_top = $doc.scrollTop(),
				bg_css = 'center ' + (multiplier * from_top - 200) + 'px';
			$object.css({ "background-position": bg_css });
		});
	};

	/**
	 * detect IE
	 * returns version of IE or false, if browser is not Internet Explorer
	 */
	function detectIE() {
		var ua = window.navigator.userAgent;

		var msie = ua.indexOf('MSIE ');
		if (msie > 0) {
			// IE 10 or older => return version number
			return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
		}

		var trident = ua.indexOf('Trident/');
		if (trident > 0) {
			// IE 11 => return version number
			var rv = ua.indexOf('rv:');
			return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
		}

		// var edge = ua.indexOf('Edge/');
		// if (edge > 0) {
		//     // Edge (IE 12+) => return version number
		//     return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
		// }

		// other browser
		return false;
	}

	$(document).ready(function () {

		// Detect IE
		if (detectIE()) {
			alert('Please use the latest version of Chrome, Firefox, or Edge for best browsing experience.');
		}

		$('select').formSelect();
		// Parallax image background
		background_image_parallax($(".tm-parallax"), 0.40);

		// Darken image when its radio button is selected
		$(".tm-radio-group-1").click(function () {
			$('.tm-radio-group-1').parent().siblings("img").removeClass("darken");
			$(this).parent().siblings("img").addClass("darken");
		});

		$(".tm-radio-group-2").click(function () {
			$('.tm-radio-group-2').parent().siblings("img").removeClass("darken");
			$(this).parent().siblings("img").addClass("darken");
		});

		$(".tm-checkbox").click(function () {
			$(this).parent().siblings("img").toggleClass("darken");
		})
	});
	             function checkpost(){
             var tempcount = fm.tempcount.value;
             if (tempcount=="" ||undefined || null) {
                 alert("请输入您选择的第几组数据"); return false;
             }

             else {
                 alert("提交成功！");return true;
             }
             }
</script>
</body>

</html>