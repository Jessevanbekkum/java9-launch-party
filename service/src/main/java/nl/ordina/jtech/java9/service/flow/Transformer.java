package nl.ordina.jtech.java9.service.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class Transformer<FROM, TO> {//extends SubmissionPublisher<TO> implements Flow.Processor<FROM, TO> {
    private final static Logger LOG = LoggerFactory.getLogger(Transformer.class);

    private Function<? super FROM, ? extends TO> function;
    //private Flow.Subscription subscription;

    public Transformer(Function<? super FROM, ? extends TO> function) {
        this.function = function;
    }

    //@Override
    public void onSubscribe(/*Flow.Subscription subscription*/) {
        //- enable this - remember subscription & request one item at a time
        //this.subscription = subscription;
        //subscription.request(1);
    }

    //@Override
    public void onNext(FROM item) {
        //- enable this - submit transformed item & request a new one
        //submit(function.apply(item));
        //subscription.request(1);
    }

    //@Override
    public void onError(Throwable t) {
        LOG.error("onError", t);
    }

    //@Override
    public void onComplete() {
        LOG.info("Done!");
        //- enable this - closes parent SubmissionPublisher, signalling onComplete() to subscriber;
        //  in this case NumberDoublerLoggingConsumer
        // close();
    }
}
