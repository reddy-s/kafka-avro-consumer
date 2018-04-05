package io.dataglitter.kafka.consumer.processor;

import io.dataglitter.kafka.consumer.processor.impl.SchemaV1Processor;
import io.dataglitter.kafka.consumer.processor.impl.SchemaV2Processor;
import org.springframework.stereotype.Service;

/**
 * Created by reddys on 04/04/2018.
 */
@Service
public class ProcessorFactory {

    private SchemaV1Processor schemaV1Processor;

    private SchemaV2Processor schemaV2Processor;

    public ProcessorFactory(SchemaV1Processor schemaV1Processor, SchemaV2Processor schemaV2Processor) {
        this.schemaV1Processor = schemaV1Processor;
        this.schemaV2Processor = schemaV2Processor;
    }

    public Processor getProcessor(Object version){
        if (schemaV1Processor.getProcessorVersion().equals(version)){
            return schemaV1Processor;
        } else if (schemaV2Processor.getProcessorVersion().equals(version)){
            return schemaV2Processor;
        }
        return schemaV1Processor;
    }
}
