package io.dataglitter.kafka.consumer.processor;

import java.text.ParseException;

/**
 * Created by reddys on 04/04/2018.
 */
public interface Processor<T1, T2> {

    void process(T1 record) throws ParseException;
    T2 getProcessorVersion();
    void setProcessorVersion(T2 version);

}
