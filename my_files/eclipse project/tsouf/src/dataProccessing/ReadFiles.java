package dataProccessing;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;



public class ReadFiles {
	
	public static String readFileFromHDFS() throws IOException {
		
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fileSystem = FileSystem.get(configuration);
        //Create a path
        String fileName = "part-00000";
        Path hdfsReadPath = new Path("hdfs://localhost:9000/finalProject_output_6/" + fileName);
        //Init input stream
        FSDataInputStream inputStream = fileSystem.open(hdfsReadPath);
        //Classical input stream usage
        String out= IOUtils.toString(inputStream, "UTF-8");
        //System.out.println(out);

        inputStream.close();
        fileSystem.close();
        
        return out;
    }

}
