package dataProccessing;
import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class My_Mapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable> {
	
	
	private final static IntWritable one = new IntWritable(1);
	
	public void map(LongWritable key , Text value , OutputCollector<Text,IntWritable> output,Reporter reporter) throws IOException
	{
		String line = value.toString();
		
		JSONParser parser = new JSONParser();  
		try {
			
			//we are looking for negative words 
			
			JSONObject json = (JSONObject) parser.parse(line);
			String text = json.get("text").toString();
			
			//System.out.println(text);
			
			if (text.contains("bad")){
				output.collect(new Text("bad"), one);	
			}
			
			if(text.contains("don't")) {
				output.collect(new Text("don't"), one);	
			}
			
			if(text.contains("die")) {
				output.collect(new Text("die"), one);	
			}
			
			if(text.contains("death")) {
				output.collect(new Text("death"), one);	
			}
			
			if(text.contains("died")) {
				output.collect(new Text("died"), one);	
			}
			
			if(text.contains("cancel")) {
				output.collect(new Text("cancel"), one);	
			}
			
			if(text.contains("fraud")) {
				output.collect(new Text("fraud"), one);	
			}
			
			if(text.contains("danger")) {
				output.collect(new Text("danger"), one);	
			}
			
			if(text.contains("dangerous")) {
				output.collect(new Text("dangerous"), one);	
			}
			
			//
			
			if(text.contains("untrusty")) {
				output.collect(new Text("untrusty"), one);	
			}
			
			if(text.contains("fear")) {
				output.collect(new Text("fear"), one);	
			}
			
			if(text.contains("unreliable")) {
				output.collect(new Text("unreliable"), one);	
			}
			
			
			if(text.contains("anti-vax")) {
				output.collect(new Text("anti-vax"), one);	
			}
			
			if(text.contains("lie")) {
				output.collect(new Text("lie"), one);	
			}
			
			if(text.contains("lies")) {
				output.collect(new Text("lies"), one);	
			}
			
			if(text.contains("fake")) {
				output.collect(new Text("fake"), one);	
			}
			
			if(text.contains("hate")) {
				output.collect(new Text("hate"), one);	
			}
			
			if(text.contains("?")) {
				output.collect(new Text("?"), one);	
			}
			
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
		
			
	}
	
}
