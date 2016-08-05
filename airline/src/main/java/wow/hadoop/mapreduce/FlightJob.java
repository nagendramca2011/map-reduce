package wow.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FlightJob implements Tool{
	
	private Configuration conf;//apply getter and setter 
	
	

	public Configuration getConf() {
		return conf;//getting the configuration
	}



	public void setConf(Configuration conf) {
		this.conf = conf;//setting the configuration
	}
	
	public int run(String[] args)throws Exception{
		//initializing the job configuraion
		//Job flightjob=new Job(getConf()); DEPRECATED
		Job flightjob=Job.getInstance(getConf());
		//setting the job name
		flightjob.setJobName("Flight Job");
		//to call this as a jar
		flightjob.setJarByClass(this.getClass());
		//setting custom mapper class
		flightjob.setMapperClass(FlightMapper.class);
		//setting custom reducer class
		flightjob.setReducerClass(FlightReducer.class);
		
		
		//MAPPER
		//setting mapper output key class: K2
		flightjob.setMapOutputKeyClass(Text.class);
		//setting mapper output value class:V2
		flightjob.setMapOutputValueClass(LongWritable.class);
		
		//REDUCER
		//setting reducer output key class :K3
		flightjob.setMapOutputKeyClass(Text.class);
		//setting reducer output value class : V3
		flightjob.setMapOutputValueClass(LongWritable.class);
		
		//setting the input format class i.e for K1,V1
		flightjob.setInputFormatClass(TextInputFormat.class);
		
		//setting the output format class
		flightjob.setOutputFormatClass(TextOutputFormat.class);
		
		//setting the input file path
		FileInputFormat.addInputPath(flightjob, new Path(args[0]));
		
		//setting the output folder path
		FileOutputFormat.setOutputPath(flightjob, new Path(args[1]));
		Path outputpath=new Path(args[1]);
		
		//delete the output folder if exits
		outputpath.getFileSystem(conf).delete(outputpath,true);
		//to execute the job and return the status
		return flightjob.waitForCompletion(true)?0:-1;
		
	}



	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		//start the job providing arguments and configurations
		ToolRunner.run(new Configuration(),new FlightJob(),args);

	}

}
