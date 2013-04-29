import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public abstract class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
	String field = new String();
	List<String> patentList = new ArrayList<String>();
	Integer element = setElement();
	
	public Map() {
		setElement();
	}
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
   	 
		Text word = new Text();
   	 	IntWritable one = new IntWritable(1);
   	 	String patternString = new String("(?<![a-zA-Z]),");
        String line = value.toString();
        
        patentList = Arrays.asList(line.split(patternString));
        setField();
        if (validate(field)) {
       	 word.set(field);
       	 context.write(word, one);
        }
    }
	public void setField() {
        field = patentList.get(element);
        field = field.replaceAll("\"", "").trim();
	}
	
	public String getField() {
		return field;
	}
	
	public abstract boolean validate(String field);
	public abstract Integer setElement();

}
