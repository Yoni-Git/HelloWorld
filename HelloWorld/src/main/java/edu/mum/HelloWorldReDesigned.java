package edu.mum;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageSource;
import edu.mum.component.impl.HelloWorldMessageSource;
import edu.mum.component.impl.StandardOutMessageDisplay;

public class HelloWorldReDesigned {
    public static void main(String[] args) {
        MessageDisplay messageDisplay = new StandardOutMessageDisplay();
        MessageSource messageSource = new HelloWorldMessageSource();
        messageDisplay.setMessageSource(messageSource);
        messageDisplay.display();
    }
}
