
//public class CountryMap extends Mapper<LongWritable, Text, Text, IntWritable> {
public class CountryMap extends PatentMap {
	
	public boolean validate(String field) {
		boolean isValid = false;
    	 
    	 if (field.length() == 2 && field.matches("^[A-Z]+$")) {
    		 isValid = true;
    	 }
    	 return isValid;
	}
	
	public Integer setElement() {
		return 8;
	}
	
	/*
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	 
    	 Text word = new Text();
    	 IntWritable one = new IntWritable(1);
         String country = new String();
         List<String> patentList = new ArrayList<String>();
         String patternString = new String("(?<![a-zA-Z]),");
         
         String line = value.toString();
         
         patentList = Arrays.asList(line.split(patternString));
         country = patentList.get(8);
         country = country.replaceAll("\"", "").trim();
         
         if (validateCountryCode(country)) {
        	 word.set(country);
        	 context.write(word, one);
         }
     }
     
     public void countCountry(List<String> patentList) {
         String country = new String();
         country = patentList.get(8);
         country = country.replaceAll("\"", "").trim();
    	 
     }
     public boolean validateCountryCode(String code) {
    	 boolean isCode = false;
    	 
    	 if (code.length() == 2 && code.matches("^[A-Z]+$")) {
    		 isCode = true;
    	 }
    	 return isCode;
     }
     */
} 
