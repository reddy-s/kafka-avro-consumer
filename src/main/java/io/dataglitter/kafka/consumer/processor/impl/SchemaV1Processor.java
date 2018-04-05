package io.dataglitter.kafka.consumer.processor.impl;

import io.dataglitter.kafka.consumer.constant.TweetConstants;
import io.dataglitter.kafka.consumer.processor.Processor;
import io.dataglitter.kafka.consumer.processor.ProcessorAbstract;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Created by reddys on 04/04/2018.
 */
@Service
public class SchemaV1Processor extends ProcessorAbstract {

    private Logger logger = LoggerFactory.getLogger(SchemaV1Processor.class);

    public SchemaV1Processor() {
        super.setProcessorVersion(1);
    }

    @Override
    public void process(GenericRecord record) {
        logger.info(TweetConstants.SEPERATOR + TweetConstants.ID + TweetConstants.SPLIT +
                record.get(TweetConstants.ID).toString() + TweetConstants.SEPERATOR + TweetConstants.TEXT +
                TweetConstants.SPLIT + record.get(TweetConstants.TEXT) + TweetConstants.SEPERATOR + TweetConstants.LANG +
                TweetConstants.SPLIT + record.get(TweetConstants.LANG) + TweetConstants.SEPERATOR + TweetConstants.IS_RETWEET +
                TweetConstants.SPLIT + record.get(TweetConstants.IS_RETWEET).toString());
    }
}
