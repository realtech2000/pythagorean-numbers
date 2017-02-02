package hu.realtech2000;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

@Configuration
@EnableBinding(Source.class)
public class NumbersSource {

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "100", maxMessagesPerPoll = "1"))
	public MessageSource<NumbersMessage> timerMessageSource() {
		return () -> {
			Map<String,Object> headers = new HashMap<>();
			String uuid = UUID.randomUUID().toString();
			headers.put(IntegrationMessageHeaderAccessor.CORRELATION_ID, uuid);
			Integer a = (int) (Math.random() * 15 +1);
			Integer b = (int) (Math.random() * 15 +1);
			System.out.println("Starting message, uuid: " + uuid + ", a = " + a + ", b = " + b);
			MessageHeaders messageHeaders = new MessageHeaders(headers);
			return new GenericMessage<>(new NumbersMessage(a, b) , messageHeaders);
		};
	}

}
