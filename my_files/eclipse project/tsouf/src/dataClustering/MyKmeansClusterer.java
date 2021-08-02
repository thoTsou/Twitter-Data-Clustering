package dataClustering;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.conversion.InputDriver;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.RandomSeedGenerator;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.utils.clustering.ClusterDumper;


public class MyKmeansClusterer {

	public static ClusterDumper execute() throws Exception {
		
		//Find files
		
		Configuration conf2 = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000/"), conf2);
		FileStatus[] fileStatus = fs.listStatus(new Path("hdfs://localhost:9000/finalProject_file15"));
			    
		String files[]=new String[fileStatus.length];
			    
		int cout=0;
			    
		for(FileStatus file : fileStatus){
			 //System.out.println(status.getPath().toString());
			  files[cout] = file.getPath().toString();
			  cout++;
		}
		
		
		String row;
		ArrayList<String> DATA = new ArrayList<String>();
		
		for(int k=0 ; k<files.length ; k++) {
		
			Path path = new Path(files[k]);
			
			
			Configuration configuration = new Configuration();
	        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
	        FileSystem fileSystem = FileSystem.get(configuration);
			
			BufferedReader Reader = new BufferedReader(new InputStreamReader(fileSystem.open(path)));
			
			
			while ((row = Reader.readLine()) != null) { 
				
				if( row.contains("bad") || row.contains("don't") || row.contains("die") || row.contains("died") || row.contains("?") || row.contains("death") || row.contains("cancel") || row.contains("fraud") || row.contains("danger") || row.contains("dangerous") || row.contains("untrusty") || row.contains("fear") || row.contains("unreliable") || row.contains("anti-vax") || row.contains("lie") || row.contains("lies") || row.contains("fake") || row.contains("hate") ) 
				{
					//negative
					DATA.add("1 1");
				}else {
					//positive
					DATA.add("9 9");
				}
				
			}
			
			
		}
		
		//display DATA 
//    	for(String entry : DATA) {
//			System.out.println(entry);
//		}
		
		
		//save DATA to a .dat file into hdfs
		createDirectory();
		
		writeFileToHDFS(DATA);	
	
		//use the created .dat file in order to complete the task
		Path input = new Path("hdfs://localhost:9000/KmeansFinalProject_InputData_5");
		Path output = new Path("hdfs://localhost:9000/KmeansOutput_finalProject_5");
		
		
		Configuration conf = new Configuration();
		HadoopUtil.delete(conf, output);
		
		ClusterDumper clusterDumper = run(conf,input,output,new EuclideanDistanceMeasure(),2,0.5,10);
		
		return clusterDumper;

	}
	
	public static ClusterDumper run(Configuration conf , Path input , Path output , DistanceMeasure measure , int k , double convergenceDelta , int maxIterations) throws Exception {
		
		Path directoryContainingCnvertedInput = new Path(output,"KmeansOutputData");
		
		
		InputDriver.runJob(input, directoryContainingCnvertedInput, "org.apache.mahout.math.RandomAccessSparseVector");
		
		Path clusters = new Path(output,"random-seeds");
		clusters = RandomSeedGenerator.buildRandom(conf,directoryContainingCnvertedInput,clusters,k , measure);
		
		KMeansDriver.run(conf,directoryContainingCnvertedInput,clusters,output,convergenceDelta,maxIterations,true,0.0,false);
		
		Path outGlob = new Path(output , "clusters-*-final");
		Path clusteredPoints = new Path(output , "clusteredPoints");
		
		ClusterDumper clusterDumper = new ClusterDumper(outGlob,clusteredPoints);
		clusterDumper.printClusters(null);
		
		return clusterDumper;
		
	}
	
	//create directory into hdfs
	public static void createDirectory() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fileSystem = FileSystem.get(configuration);
        Path path = new Path("hdfs://localhost:9000/KmeansFinalProject_InputData_5");
        fileSystem.mkdirs(path);
    }
	
	//write file into hdfs
	public static void writeFileToHDFS(ArrayList<String> DATA) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fileSystem = FileSystem.get(configuration);
        //Create a path
        String fileName = "inputData.dat";
        Path hdfsWritePath = new Path("/KmeansFinalProject_InputData_5/" + fileName);
        FSDataOutputStream fsDataOutputStream = fileSystem.create(hdfsWritePath,true);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fsDataOutputStream,StandardCharsets.UTF_8));
        
        for(String DataEntry : DATA) {
        	bufferedWriter.write(DataEntry + System.lineSeparator() );
        }
        
        bufferedWriter.newLine();
        bufferedWriter.close();
        fileSystem.close();
    }

}
