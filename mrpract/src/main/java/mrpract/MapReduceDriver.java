package mrpract;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;


public class MapReduceDriver {	
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
		if(args.length!= 2){
			System.out.println("Usage MapReduceDriver <input dir> <output dir> \n");
			System.exit(-1);
		}
		Job job = new Job();
		job.setJarByClass(MapReduceDriver.class);
		job.setJobName("MapReduceDriver");
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordMapper.class);		
		job.setReducerClass(WordCountReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		boolean success = job.waitForCompletion(true);
		System.out.println(success ? 0 : 1);

	}	

}
