# pythagorean-numbers

## Spring Cloud Stream - Aggregator

Proof of concept for aggeregating different messages.
I was interested only in the aggregator. 
I have not spent time in design/implementation details.
This are NO 12 factor apps!

One service emmits 2 random numbers (a,b). 
Over some other services in the end we are checking, if (together with calculated c = sqrt(a X a + b X b)) they form a [Pythagorean triple](https://en.wikipedia.org/wiki/Pythagorean_triple). 

Services:
![alt text](https://github.com/realtech2000/pythagorean-numbers/blob/master/images/services.png "Services")

`generator -> (a,b) -> calculation-one -> (a X a) -> aggregator -> results`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`          -> calculation-two -> (b X b) -> aggregator`
                   
Remarks:
NumberOneMessage and NumberTwoMessage are intentionaly different classes.


Technology stack:
- Language: Java 8
- Build system: Maven
- Framework: Spring Boot with  Spring Cloud Stream
- Messaging: RabbitMq
