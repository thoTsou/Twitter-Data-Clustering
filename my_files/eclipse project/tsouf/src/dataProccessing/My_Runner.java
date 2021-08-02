package dataProccessing;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;


public class My_Runner {

	public static void run() throws IOException, URISyntaxException  {
		
		JobConf conf = new JobConf(My_Runner.class);
		conf.setJobName("WordCount");
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		conf.setMapperClass(My_Mapper.class);
		conf.setCombinerClass(My_Reducer.class);
		conf.setReducerClass(My_Reducer.class);
		
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		//Find files
		
		Configuration conf2 = new Configuration();
	    FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000/"), conf2);
	    FileStatus[] fileStatus = fs.listStatus(new Path("hdfs://localhost:9000/finalProject_file15"));
	    
	    String arguments[]=new String[fileStatus.length];
	    
	    int counter=0;
	    
	    for(FileStatus status : fileStatus){
	        //System.out.println(status.getPath().toString());
	    	arguments[counter] = status.getPath().toString();
	    	counter++;
	    }
		//
		
		String output = new String("hdfs://localhost:9000/finalProject_output_6"); //Output Directory
	
		for(int i=0;i<arguments.length;i++) {
		FileInputFormat.addInputPaths(conf, arguments[i] );
		}
		
		FileOutputFormat.setOutputPath( conf, new Path(output) );
		
		try {
			JobClient.runJob(conf);
			System.out.println("Job Successfull");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Job NOT succesfull");
		}
	}

}
