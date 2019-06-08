package com.mulesoft.training;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.interceptor.Interceptor;
import org.mule.processor.AbstractInterceptingMessageProcessor;

public class LoggingInterceptor extends AbstractInterceptingMessageProcessor implements Interceptor {

	@Override
	public MuleEvent process(MuleEvent event) throws MuleException {
		System.out.println("***** Custom interceptor called *****");
		try {
			System.out.println("Payload: " +event.getMessage().getPayloadAsString());
		} 
		catch (Exception e) {
			;
		}
		
		return event;
	}

}
