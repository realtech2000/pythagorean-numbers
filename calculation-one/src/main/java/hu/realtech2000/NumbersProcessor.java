package hu.realtech2000;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 *
 * @author zolika
 */
@Component
public class NumbersProcessor {
	
	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	
	public GenericMessage<NumberOneMessage> acceptNumbers(@Header(IntegrationMessageHeaderAccessor.CORRELATION_ID) String uuid, NumbersMessage message) {
		System.out.println("Calcone service, uuid: " + uuid + ", first number: " + message.getFirstNumber());
		Map<String,Object> headers = new HashMap<>();
		headers.put(IntegrationMessageHeaderAccessor.CORRELATION_ID, uuid);
		MessageHeaders messageHeaders = new MessageHeaders(headers);
		return new GenericMessage<>(new NumberOneMessage(message.getFirstNumber() * message.getFirstNumber()),messageHeaders);
	}
	

}
