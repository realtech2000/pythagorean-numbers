package hu.realtech2000;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.aggregator.AggregatingMessageHandler;
import org.springframework.integration.aggregator.DefaultAggregatingMessageGroupProcessor;
import org.springframework.integration.aggregator.MessageCountReleaseStrategy;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.messaging.MessageHandler;

@EnableBinding(Processor.class)
@SpringBootApplication
public class AggregateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregateApplication.class, args);
	}

	@Autowired
	Processor processor;

	@ServiceActivator(inputChannel = Processor.INPUT)
	@Bean
	public MessageHandler aggregator() {

		AggregatingMessageHandler aggregatingMessageHandler
				= new AggregatingMessageHandler(new DefaultAggregatingMessageGroupProcessor(),
						new SimpleMessageStore(10));

		//AggregatorFactoryBean aggregatorFactoryBean = new AggregatorFactoryBean();
		//aggregatorFactoryBean.setMessageStore();
		aggregatingMessageHandler.setOutputChannel(processor.output());
		//aggregatorFactoryBean.setDiscardChannel(processor.output());
		aggregatingMessageHandler.setSendPartialResultOnExpiry(false);
		aggregatingMessageHandler.setSendTimeout(5000L);
		//aggregatingMessageHandler.setCorrelationStrategy(new ExpressionEvaluatingCorrelationStrategy("'FOO'"));
		aggregatingMessageHandler.setReleaseStrategy(new MessageCountReleaseStrategy(2)); //ExpressionEvaluatingReleaseStrategy("size() == 5")
		aggregatingMessageHandler.setExpireGroupsUponCompletion(true);
		aggregatingMessageHandler.setGroupTimeoutExpression(new ValueExpression<>(5000L)); //size() ge 2 ? 5000 : -1
		aggregatingMessageHandler.setExpireGroupsUponTimeout(true);
		return aggregatingMessageHandler;
	}

}
