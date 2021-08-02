<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
      
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home Page</title>
	<!-- BootStrap -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jquery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- font awesome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!--My external css -->
    <link rel="stylesheet" type="text/css" href="">
    <!-- My external Js -->
    <script src="./Javascript-Scripts/scripts.js"></script>


    <meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body style="text-align:center;">

	<h2 style="margin-top:10%">Keywords : "covid-19" , "vaccine" , "vaccination" , "vaccinated" , "AstraZeneca" , "Pfizer" </h2>
	
	<form action="producer.jsp">
		<button type="submit" class="btn btn-primary" style="margin-top:2%" id="startSearchingButton">Start Collecting Streaming Data</button>
	</form>

</body>
</html>