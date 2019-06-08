package com.mulesoft.training;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.interceptor.AbstractEnvelopeInterceptor;
import org.mule.management.stats.ProcessingTime;

public class AuditLoggingEnvelopeInterceptor extends AbstractEnvelopeInterceptor {
	
	public AuditLoggingEnvelopeInterceptor() {
		System.out.println("AuditLoggingEnvelopeInterceptor constructor called.");
	}

	@Override
	public MuleEvent before(MuleEvent event) throws MuleException {
		System.out.println("***** BEFORE interceptor triggered *****");
		return event;
	}

	@Override
	public MuleEvent after(MuleEvent event) throws MuleException {
		System.out.println("***** AFTER interceptor triggered *****");
		return event;
	}

	@Override
	public MuleEvent last(MuleEvent event, ProcessingTime time, long startTime, boolean exceptionWasThrown)
			throws MuleException {
		System.out.println("***** Flow execution complete *****");
		return event;
	}


}
