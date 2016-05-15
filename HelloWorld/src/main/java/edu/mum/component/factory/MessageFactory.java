package edu.mum.component.factory;

import java.io.InputStream;
import java.util.Properties;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;

public class MessageFactory {
    private static MessageFactory instance;

    private Properties properties;
    private MessageDisplay display;
    private MessageSource source;

    private MessageFactory() {
    	properties = new Properties();

        String fileName = "HelloWorld.properties";
        try {
        	
    		// Use ClassLoader to find resource...
        	InputStream  input = this.getClass().getClassLoader().getResourceAsStream("edu/mum/resources/" + fileName);
	    		if(input==null){
    	            System.out.println("Unable to find " + fileName);
    		    return ;
    		}
 
    		//load in properties file data 
    		properties.load(input);

             String displayClass = properties.getProperty("display.class");
            String sourceClass = properties.getProperty("source.class");

            display = (MessageDisplay) Class.forName(displayClass).newInstance();
            source = (MessageSource) Class.forName(sourceClass).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageFactory();
    }

    public static MessageFactory getInstance() {
        return instance;
    }

    public MessageDisplay getMessageDisplay() {
        return display;
    }

    public MessageSource getMessageSource() {
        return source;
    }
}
