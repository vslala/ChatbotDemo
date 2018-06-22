<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="${siteDescription!'Counter Clinic is an appointment management application.'}">
    <meta name="author" content="">
    <link rel="icon" href="/global/images/logo/">

    <title>${title!'Chatbot Demo'}</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<#if view.page??>
		<#include view.page + "/css/css.ftl">
	</#if>
  </head>

  	<body>

	<#include view.page + "/main/" + view.component + ".ftl" >
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<#include view.page + "/scripts/scripts.ftl">

	</body>
</html>