<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>User Account - Bootstrap Admin</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<link href="../res/css/bootstrap.min.css" rel="stylesheet" />
<link href="../res/css/bootstrap-responsive.min.css" rel="stylesheet" />

<link href="../res/css/font-awesome.css" rel="stylesheet" />

<link href="../res/css/adminia.css" rel="stylesheet" />
<link href="../res/css/adminia-responsive.css" rel="stylesheet" />


<link href="../res/css/pages/plans.css" rel="stylesheet" />

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<div class="navbar navbar-fixed-top">
	<div class="row" style="padding-left: 250px;">

		<div  class="span9">

			<div class="widget">

				<div class="widget-header">
					<h3>Account Information</h3>
				</div>
				<!-- /widget-header -->

				<div class="widget-content">



					<div class="tabbable">
						<div class="tab-content">
							<div class="tab-pane active" id="1">
								<form id="edit-profile" class="form-horizontal" 
								action="../user/register" method="post"/>
								<fieldset>

									<div class="control-group">
										<label class="control-label" for="username">Username</label>
										<div class="controls">
											<input type="text" class="input-medium" name="name"
												id="name" value=""  />
										</div>
										<!-- /controls -->
									</div>
									<!-- /control-group -->


									<div class="control-group">
										<label class="control-label" for="firstname">Age</label>
										<div class="controls">
											<input type="text" class="input-medium" id="age" name="age"
												value="" />
										</div>
										<!-- /controls -->
									</div>
									<!-- /control-group -->


									<div class="control-group">
										<label class="control-label" for="lastname">Sex</label>
										<div class="controls">
											<input type="radio" class="input-medium" id="sex" name="type"
												value="male" /><label >male</label>
												<input type="radio" class="input-medium" id="sex" name="type"
												value="female" /><label>female</label>
										</div>
										<!-- /controls -->
									</div>
									<!-- /control-group -->

									<div class="control-group">
										<label class="control-label" for="password1">Password</label>
										<div class="controls">
											<input type="password" class="input-medium" id="password1" name="password"
												value="" />
										</div>
										<!-- /controls -->
									</div>
									<!-- /control-group -->

									<div class="form-actions">
										<button type="submit" class="btn btn-primary">Save</button>
										<button class="btn">Cancel</button>
									</div>
									<!-- /form-actions -->
								</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /widget-content -->

			</div>
			<!-- /widget -->

		</div>
		<!-- /span9 -->

	</div>
	<!-- /row -->
	</div>

	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../res/js/jquery-1.7.2.min.js"></script>


	<script src="../res/js/bootstrap.js"></script>

</body>
</html>
