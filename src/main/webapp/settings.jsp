<!DOCTYPE html>
<html lang="en">
<head>
<title>File Upload</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="page-header">
		<h1>
			Italent App<small>Application Settings </small>
		</h1>
	</div>
	<form method="POST" action="settings" enctype="application/x-www-form-urlencoded" >
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">App Name</span> <input
				type="text" class="form-control" placeholder="Application Name"
				aria-describedby="basic-addon1" id="APP_NAME" name="APP_NAME" value="${APP_NAME}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">Auth Android</span>
			<input type="text" class="form-control" placeholder="Auth Android"
				aria-describedby="basic-addon1" id="AUTH_ANDROID" name="AUTH_ANDROID" value="${AUTH_ANDROID}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">Auth IOS</span> <input
				type="text" class="form-control" placeholder="Auth IOS"
				aria-describedby="basic-addon1" id="AUTH_IOS" name="AUTH_IOS" value="${AUTH_IOS}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">App Email</span> <input
				type="email" class="form-control" placeholder="App Email"
				aria-describedby="basic-addon1" id="APP_EMAIL_ID" name="APP_EMAIL_ID" value="${APP_EMAIL_ID}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">App Email
				PWD</span> <input type="text" class="form-control"
				placeholder="Email Password" aria-describedby="basic-addon1"
				id="EMAIL_PWD" name="EMAIL_PWD" value="${EMAIL_PWD}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">GOOGLE_PROJECT_ID</span>
			<input type="text" class="form-control"
				placeholder="GOOGLE_PROJECT_ID" aria-describedby="basic-addon1"
				id="GOOGLE_PROJECT_ID" name="GOOGLE_PROJECT_ID" value="${GOOGLE_PROJECT_ID}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">MESSAGING_HTTP_API</span>
			<input type="text" class="form-control"
				placeholder="MESSAGING_HTTP_API" aria-describedby="basic-addon1"
				id="MESSAGING_HTTP_API" name="MESSAGING_HTTP_API" value="${MESSAGING_HTTP_API}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">MESSAGING_AUTH_KEY</span>
			<input type="text" class="form-control"
				placeholder="MESSAGING_AUTH_KEY" aria-describedby="basic-addon1"
				id="MESSAGING_AUTH_KEY" name="MESSAGING_AUTH_KEY" value="${MESSAGING_AUTH_KEY}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">DATE_FORMAT</span>
			<input type="text" class="form-control" placeholder="DATE_FORMAT"
				aria-describedby="basic-addon1" id="DATE_FORMAT" name="DATE_FORMAT" value="${DATE_FORMAT}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">SMTP</span> <input
				type="text" class="form-control" placeholder="SMTP"
				aria-describedby="basic-addon1" id="SMTP" name="SMTP" value="${SMTP}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">SEND_SMS_API</span>
			<input type="text" class="form-control" placeholder="SEND_SMS_API"
				aria-describedby="basic-addon1" id="SEND_SMS_API" name="SEND_SMS_API" value="${SEND_SMS_API}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">SMS_USER_NAME</span>
			<input type="text" class="form-control" placeholder="SMS_USER_NAME"
				aria-describedby="basic-addon1" id="SMS_USER_NAME" name="SMS_USER_NAME" value="${SMS_USER_NAME}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">SMS_TAG_NAME</span>
			<input type="text" class="form-control" placeholder="SMS_TAG_NAME"
				aria-describedby="basic-addon1" id="SMS_TAG_NAME" name="SMS_TAG_NAME" value="${SMS_TAG_NAME}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1">SMS_USER_PWD</span>
			<input type="text" class="form-control" placeholder="SMS_USER_PWD"
				aria-describedby="basic-addon1" id="SMS_USER_PWD" name="SMS_USER_PWD" value="${SMS_USER_PWD}">
		</div>
		 <button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>