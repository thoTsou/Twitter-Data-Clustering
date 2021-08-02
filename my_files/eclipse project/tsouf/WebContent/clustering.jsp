<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="dataClustering.MyKmeansClusterer" %>
    <%@ page import="org.apache.mahout.utils.clustering.ClusterDumper" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Clustering Results</title>
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
<body>
	
	<%
		
	ClusterDumper clusterDumper = MyKmeansClusterer.execute();
	
	out.println("<h4 style='font-weight:bold;text-align:center;margin-top:15%'>Everything went Ok .To see results open your hdfs UI and check folder : /KmeansOutput_finalProject_5</h4>");
	
	%>
	
	<!-- last added  -->
	<%
	
	%>
	
	
	
</body>
</html>