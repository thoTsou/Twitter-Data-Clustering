<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="dataProccessing.My_Mapper" %>
    <%@ page import="dataProccessing.My_Reducer" %>
    <%@ page import="dataProccessing.My_Runner" %>
    <%@ page import="dataProccessing.ReadFiles" %>
    <%@ page import="java.util.ArrayList" %>
    

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Statistics</title>
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
	
	<h3 style="font-weight:bold;text-align:center;">Statistics</h3>
	
	<div style="margin-top: 3%;border-style:solid;border-color:grey;">
		<%
			
			My_Runner.run();
		
			String data = ReadFiles.readFileFromHDFS();
			
			String[] dataArray = data.split("\\s+");
			
			
			for(int i=0 ; i<dataArray.length ; i=i+2){
				//display
			
				out.println("<p style='text-align:center;'> Word--> <b>"+dataArray[i]+"</b>  ,  Found --> <b>"+dataArray[i+1]+"</b> times</p>");
					
			}
			
			out.println("<hr/>");
			
			out.println("<h4 style='text-align:center;'> We collected 2000 streaming data .The goal is to see how many people express negative things and thoughts about covid-19 vaccination-vaccines  </h4>");
			
			out.println("<hr/>");
			
			out.println("<form action='clustering.jsp' style='text-align:center;'> <button type='submit' class='btn btn-info'>Procceed to clustering</button> </form>");
		
		%>
	</div>
	
</body>
</html>