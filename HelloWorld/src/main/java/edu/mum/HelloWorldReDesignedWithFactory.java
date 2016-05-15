package edu.mum;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;
import edu.mum.component.factory.MessageFactory;

public class HelloWorldReDesignedWithFactory {
    public static void main(String[] args) {
    	
    	MessageFactory messageFactory = MessageFactory.getInstance();
    	
        MessageDisplay messageDisplay = messageFactory.getMessageDisplay();
        MessageSource messageSource = messageFactory.getMessageSource();
        messageDisplay.setMessageSource(messageSource);
        messageDisplay.display();
    }
}
