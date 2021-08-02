<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Properties" %>
    <%@ page import="java.util.concurrent.BlockingQueue" %>
    <%@ page import="java.util.concurrent.LinkedBlockingQueue" %>
    <%@ page import="org.apache.kafka.clients.producer.KafkaProducer" %>
    <%@ page import="org.apache.kafka.clients.producer.Producer" %>
    <%@ page import="org.apache.kafka.clients.producer.ProducerRecord" %>
    <%@ page import="com.google.common.collect.Lists" %>
    <%@ page import="com.twitter.hbc.ClientBuilder" %>
    <%@ page import="com.twitter.hbc.core.Client" %>
    <%@ page import="com.twitter.hbc.core.Constants" %>
    <%@ page import="com.twitter.hbc.core.endpoint.StatusesFilterEndpoint" %>
    <%@ page import="com.twitter.hbc.core.processor.StringDelimitedProcessor" %>
    <%@ page import="com.twitter.hbc.httpclient.auth.Authentication" %>
    <%@ page import="com.twitter.hbc.httpclient.auth.OAuth1" %>
    <%@ page import="org.json.simple.JSONObject" %>
    <%@ page import="org.json.simple.parser.JSONParser" %>
    <%@ page import="org.json.simple.parser.ParseException" %>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Data Producing</title>
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

	<h3 style="font-weight:bold;text-align:center;">Wait while collecting data</h3>	
	
	<%
	/*
	 * PROPERTIES
	 */
	Properties props = new Properties();
	props.put("bootstrap.servers","localhost:9092");
	props.put("acks","all");
	props.put("retries",0);
	props.put("batch.size",16384);
	props.put("linger.ms",1);
	props.put("buffer.memory",33554432);
	props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	
	
	Producer<String,String> producer = null;
	
	try {
		
		producer = new KafkaProducer<>(props);
		String consumerKey = "ukiofiPI8jsgB8TH35xpuUne4";
		String consumerSecret = "4pu2MVvvzJVaT7soYqFzDL8yxid9PoOGSteJh0MQWwZjrTy2QH";
		String token = "1379807530537345026-zaCDaQBs2pDCwYtuZ5JRSjB307iR7V";
		String secret = "QqTKJuAUgwF9eh4ZjYsCle2U0KtZLFmrbjDML8ejLIOz6";
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		endpoint.trackTerms(Lists.newArrayList("covid-19","vaccine","vaccination","vaccinated","AstraZeneca","astrazeneca","astraZeneca","Pfizer","pfizer")); //keywords
		Authentication auth = new OAuth1(consumerKey,consumerSecret,token,secret);
		
		Client client = new ClientBuilder()
				.hosts(Constants.STREAM_HOST)
				.endpoint(endpoint)
				.authentication(auth)
				.processor(new StringDelimitedProcessor(queue))
				.build();
				
		client.connect();
		
		for(int i=0 ; i<2000;i++) {
			String msg = queue.take();
			producer.send(new ProducerRecord<String,String>("finalProject_file15",msg));
			
			
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(msg);  
			
			//display
			out.println("<h5 style='font-weight:bold;text-align:center;'>Message "+i+"</h5>");
			out.println("<p style='font-size:small;text-align:center;'>"+json.get("text")+"</p>");
			out.println("<hr/>");
		}
		
		out.println("<h3 style='font-weight:bold;text-align:center;'>Finished collecting Data</h3>");
		
		out.println("<form action='displayStatistics.jsp' style='text-align:center;'> <button type='submit' class='btn btn-info'>Click Here To See Staticts</button> </form>");
		
		producer.close();
		client.stop();
		
		
	}catch(Exception e){
		System.out.println(e);
		
	}
	
	%>
	

</body>
</html>