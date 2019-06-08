package com.mulesoft.training;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class SetPayloadComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		message.setPayload("Hello World!");
		System.out.println("SetPayloadComponent executed.");

		return message;
	}

}
