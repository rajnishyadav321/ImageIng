<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<body>
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">Page 1</a></li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </div>
</nav>
		<div class="container">
			<div class="col-sm-6">
				<h2>Login</h2>
			<form class="form-horizontal" action="login" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Email:</label>
					<div class="col-sm-12">
						<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Password:</label>
					<div class="col-sm-12">          
						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
					</div>
				</div>
			
				<div class="form-group">        
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Login</button>
					</div>
				</div>
			</form>
			</div>
			<div class="col-sm-6">
					<h2>Register</h2>
			<form class="form-horizontal" action="register" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Username:</label>
					<div class="col-sm-12">
						<input type="text" class="form-control" id="text" placeholder="Enter username" name="username">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Email:</label>
					<div class="col-sm-12">
						<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Password:</label>
					<div class="col-sm-12">          
						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
					</div>
				</div>
				<div class="form-group">        
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Register</button>
					</div>
				</div>
			</form>
			</div>
		</div>

	</body>


	</html>