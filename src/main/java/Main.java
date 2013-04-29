import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	//public static class NewJob <T extends Map> {
	public static class NewJob <T>{
		
		private Job job;
		private String jobName;
		private String fileInputPath;
		private String fileOutputPath;
		private Configuration conf = new Configuration();
		Class<? extends Map> cls;
		//private T cls;
		
		//public NewJob(Class<?> cls) {// String jobName) {
		public NewJob(T cls, String jobName, String inputPath, String outputPath) {
			setFileInputPath(inputPath);
			setFileOutputPath(outputPath);
			setCls(cls);
			setJobName(jobName);
			setJob();
			
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void setCls(T cls) {
			this.cls = (Class) cls;
			//this.cls = cls;
		}
		
		public void setJobName(String jobName) {
			this.jobName = jobName;
		}
		
		public void setJob() {
			try {
				job = new Job(conf, jobName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void setFileInputPath(String path) {
			this.fileInputPath = path;
		}
		public void setFileOutputPath(String path) {
			this.fileOutputPath = path;
		}
		
		public void runJob() {
			
			try {
				
				System.out.println("fileInputPath " + fileInputPath);
				System.out.println("fileOutputPath " + fileOutputPath);
				job.setJarByClass(Main.class);
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(IntWritable.class);
	         
				job.setMapperClass(cls);
				job.setReducerClass(Reduce.class);
				job.setInputFormatClass(TextInputFormat.class);
				job.setOutputFormatClass(TextOutputFormat.class);
				
				FileInputFormat.addInputPath(job, new Path(fileInputPath));
				FileOutputFormat.setOutputPath(job, new Path(fileOutputPath));
				
				job.waitForCompletion(true);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 1.  Generic class to run multiple jobs
		// 2.  Create separate output directories
		// 3.  Dump results into hbase
		
		
		  
		//NewJob countryJob = new NewJob(CountryMap.class, "Country Count");
		//Class<? extends PatentMap> cls = CountryMap.class;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		NewJob<Mapper> countryJob = new NewJob(CountryMap.class, "CountryCount", args[0], args[1]);
		countryJob.runJob();
		  
		/*
		  Configuration conf = new Configuration();
		  Job job = new Job(conf, "CountryCount");
	     
		  job.setJarByClass(Main.class);
		  job.setOutputKeyClass(Text.class);
		  job.setOutputValueClass(IntWritable.class);
	         
		  //job.setMapperClass(CountryMap.class);
		  job.setMapperClass(cls);
		  job.setReducerClass(Reduce.class);
	         
		  job.setInputFormatClass(TextInputFormat.class);
		  job.setOutputFormatClass(TextOutputFormat.class);
	         
		  FileInputFormat.addInputPath(job, new Path(args[0]));
		  FileOutputFormat.setOutputPath(job, new Path(args[1]));
	         
		  try {
			job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/

	}

}
