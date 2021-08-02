package dataProccessing;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class My_Reducer extends MapReduceBase implements Reducer<Text,IntWritable,Text,IntWritable> {

	public void reduce(Text key,Iterator<IntWritable> values,OutputCollector<Text,IntWritable> output , Reporter reporter) throws IOException
	{
		
		int sum = 0;
		
		while (values.hasNext()) 
		{
			// replace type of value with the actual type of our value
			IntWritable value = (IntWritable) values.next();
			sum += value.get();	
		}
		
		output.collect(key, new IntWritable(sum));
	}
	
	
}
