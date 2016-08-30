<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Dashboard - Login</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<link href="./res/css/bootstrap.min.css" rel="stylesheet" />
<link href="./res/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="./res/css/font-awesome.css" rel="stylesheet" />
<link href="./res/css/adminia.css" rel="stylesheet" />
<link href="./res/css/adminia-responsive.css" rel="stylesheet" />
<link href="./res/css/pages/login.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>

	<div class="navbar navbar-fixed-top">

		<div class="navbar-inner">

			<div class="container">

				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="./">EARTHLY FISHER</a>

				<div class="nav-collapse">

					<ul class="nav pull-right">

						<li class=""><a href="javascript:;"><i
								class="icon-chevron-left"></i> Back to Homepage</a></li>
					</ul>

				</div>
				<!-- /nav-collapse -->

			</div>
			<!-- /container -->

		</div>
		<!-- /navbar-inner -->

	</div>
	<!-- /navbar -->


	<div id="login-container">


		<div id="login-header">

			<h3>Login</h3>

		</div>
		<!-- /login-header -->

		<div id="login-content" class="clearfix">

			<form action="./user/login" method="post" />
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="username">Username</label>
					<div class="controls">
						<input type="text" class="" id="username" name="name" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" class="" id="password" name="password" />
					</div>
				</div>
				<c:if test="${sessionScope.loginResponse.resCode=='false' }">
					<div class="control-group">
						<label class="control-label" for="errorinfo">ERROR</label>
						<div class="controls">
							<p style="color: red">${sessionScope.loginResponse.errMsg}</p>
						</div>
					</div>
				</c:if>
			</fieldset>

			<div class="pull-right">
				<button type="submit" class="btn btn-warning btn-large">
					Login</button>
			</div>
			</form>

		</div>
		<!-- /login-content -->


		<div id="login-extra">

			<p>
				Don't have an account? <a href="./user/register">Sign Up.</a>
			</p>

		</div>
		<!-- /login-extra -->

	</div>

	<!-- /login-wrapper -->
	<script src="./res/js/jquery-1.7.2.min.js"></script>


	<script src="./res/js/bootstrap.js"></script>

</body>
</html>
