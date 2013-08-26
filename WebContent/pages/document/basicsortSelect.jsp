<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
    function getMajor(o){
    	document.getElementById("College").innerHTML=o;
    }
    </script>

<ul id="nav">
	<li><a href="javascript:void(0)"
		onclick="getMajor(this.innerHTML)" id="College">学院列表&raquo;<!--[if gte IE 7]><!--></a>
	<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
		<ul>
			<li><a href="javascript:void(0)">经济学院 &raquo;<!--[if gte IE 7]><!--></a>
			<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业一&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业二&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--></li>

				</ul>
				<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>


			<li><a href="javascript:void(0)">自动化学院&raquo;<!--[if gte IE 7]><!--></a>
			<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业一&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业二&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业三&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>

				</ul>
				<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>

			<li><a href="javascript:void(0)">医学院 &raquo;<!--[if gte IE 7]><!--></a>
			<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业一&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业二&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>

				</ul>
				<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>



			<li><a href="javascript:void(0)">计算机学院 &raquo;<!--[if gte IE 7]><!--></a>
			<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业一&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业二&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>
					<li><a href="javascript:void(0)"
						onclick="getMajor(this.innerHTML)">专业三&raquo;<!--[if gte IE 7]><!--></a>
					<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--></li>

				</ul>
				<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>



		</ul>
		<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
</ul>
<!--[if lte IE 6]></td></tr></table></a><![endif]-->