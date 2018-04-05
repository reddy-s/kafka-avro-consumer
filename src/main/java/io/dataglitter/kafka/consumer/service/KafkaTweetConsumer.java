package io.dataglitter.kafka.consumer.service;

import io.dataglitter.kafka.consumer.constant.TweetConstants;
import io.dataglitter.kafka.consumer.processor.Processor;
import io.dataglitter.kafka.consumer.processor.ProcessorFactory;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by reddys on 10/03/2018.
 */
@Service
public class KafkaTweetConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTweetConsumer.class);

    private ProcessorFactory processorFactory;

    public KafkaTweetConsumer(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    @KafkaListener(id = "${kafka.consumer.group-id}", topics = "${kafka.topic}")
    public void listen(GenericRecord record) throws Exception {
        logger.info(TweetConstants.TWEET_RECEIVED + record.get("id").toString());
        processorFactory.getProcessor(record.get(TweetConstants.VERSION)).process(record);
    }

}
