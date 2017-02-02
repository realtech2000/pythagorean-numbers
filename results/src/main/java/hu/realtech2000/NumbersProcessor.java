package hu.realtech2000;

import java.util.List;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 *
 * @author zolika
 */
@Component
public class NumbersProcessor {
	
  @StreamListener(Sink.INPUT)
  public void result(@Header(IntegrationMessageHeaderAccessor.CORRELATION_ID) String uuid, List<Object> objects) {
	  if (objects.size() != 2) {
	   System.out.println("----> Result does not have right amount of objects: " + objects.size() + " !!!");
	  }
	  Integer a = 0;
	  Integer b = 0;
	  for (Object o : objects) {
		 if (o instanceof NumberOneMessage) {
			 a = ((NumberOneMessage)o).getFirstNumber();
		 }
		 if (o instanceof NumberTwoMessage) {
			 b = ((NumberTwoMessage)o).getSecondNumber();
		 }
	  }
	  Integer c = a + b;
	  System.out.println("");
	  System.out.println("uuid: " + uuid);
	  System.out.println("a = " + (int)Math.sqrt(a));
	  System.out.println("b = " + (int)Math.sqrt(b));
	  System.out.println("c = " + Math.sqrt(c));
	  if (Math.sqrt(c) == (int)Math.sqrt(c)) {
		  System.out.println("pythagorean numbers");
	  }
  }	

}
