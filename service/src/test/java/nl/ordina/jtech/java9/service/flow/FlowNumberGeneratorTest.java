package nl.ordina.jtech.java9.service.flow;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowNumberGeneratorTest {
    private final static Logger LOG = LoggerFactory.getLogger(FlowNumberGeneratorTest.class);

    @Test
    public void shouldGenerateNumbers() throws InterruptedException {
        //- Create Publisher
        //SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        //- Register Subscriber
        StringLoggingConsumer stringSub = new StringLoggingConsumer();
        //publisher.subscribe(stringSub);

        //- Register Transformer
        Transformer<String, Integer> transformer = new Transformer<>(Integer::parseInt);
        NumberDoublerLoggingConsumer numberSub = new NumberDoublerLoggingConsumer();
        //publisher.subscribe(transformer);
        //transformer.subscribe(numberSub);

        //- Publish items
        LOG.info("Publishing items...");
        //- we use an old-school array to illustrate nulls - List.of() bans them!
        String[] items = {"1", "2", null, "3", "-1", "foo"};
        //- Java 8 syntax would be: Arrays.asList(items).stream().forEach(i -> { if (i != null) {publisher.submit(i);}});
        //- enable this Java 9 code to start off the process
        //Arrays.stream(items).forEach(item -> Stream.ofNullable(item).forEach(publisher::submit));

        //- See how many items get processed in 100ms, then close up
        Thread.sleep(100);
        // publisher.close();
        LOG.info("Done!");
        throw new RuntimeException("this code is missing a lot - all commented code does not compile on Java 8. " +
                "Enable & study it, then remove this exception");
    }
}
