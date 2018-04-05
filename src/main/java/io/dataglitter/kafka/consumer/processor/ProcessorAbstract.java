package io.dataglitter.kafka.consumer.processor;

import org.apache.avro.generic.GenericRecord;

/**
 * Created by reddys on 04/04/2018.
 */
public abstract class ProcessorAbstract implements Processor<GenericRecord, Integer>{

    private int processorVersion;

    @Override
    public Integer getProcessorVersion() {
        return this.processorVersion;
    }

    @Override
    public void setProcessorVersion(Integer version) {
        this.processorVersion = version;
    }
}
