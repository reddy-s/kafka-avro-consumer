package io.dataglitter.kafka.consumer.processor.impl;

import io.dataglitter.kafka.consumer.constant.TweetConstants;
import io.dataglitter.kafka.consumer.processor.ProcessorAbstract;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by reddys on 04/04/2018.
 */
@Service
public class SchemaV2Processor extends ProcessorAbstract {

    private Logger logger = LoggerFactory.getLogger(SchemaV1Processor.class);

    private int processorVersion;

    private DateFormat tweetDateFormatter;

    private DateFormat transformedDateFormatter;

    public SchemaV2Processor() {
        super.setProcessorVersion(2);
        tweetDateFormatter = new SimpleDateFormat(TweetConstants.TWEET_DATE_FORMAT);
        transformedDateFormatter = new SimpleDateFormat(TweetConstants.TRANSFORMED_DATE_FORMAT);
    }

    @Override
    public void process(GenericRecord record) throws ParseException {

        String transformedCreatedOn = transformedDateFormatter.format(
                tweetDateFormatter.parse(record.get(TweetConstants.TWEETED_ON).toString())
        );

        logger.info(TweetConstants.SEPERATOR + TweetConstants.ID + TweetConstants.SPLIT +
                record.get(TweetConstants.ID).toString() + TweetConstants.SEPERATOR + TweetConstants.TEXT +
                TweetConstants.SPLIT + record.get(TweetConstants.TEXT) + TweetConstants.SEPERATOR + TweetConstants.LANG +
                TweetConstants.SPLIT + record.get(TweetConstants.LANG) + TweetConstants.SEPERATOR + TweetConstants.IS_RETWEET +
                TweetConstants.SPLIT + record.get(TweetConstants.IS_RETWEET).toString() + TweetConstants.SEPERATOR +
                TweetConstants.TWEETED_ON + TweetConstants.SPLIT + transformedCreatedOn);
    }
}
