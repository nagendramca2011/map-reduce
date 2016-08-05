package wow.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlightMapper extends Mapper<LongWritable,Text, Text, LongWritable> {
	
	protected void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException, ArrayIndexOutOfBoundsException
	{
		Text word=new Text();
		String[] split=value.toString().split("t+");
		word.set(split[2]+"---"+split[3]);
		LongWritable lw=new LongWritable();
		long lng;
		lng=Long.parseLong(split[6]);
		lw.set(lng);
		context.write((word),lw);
		
	}
}
