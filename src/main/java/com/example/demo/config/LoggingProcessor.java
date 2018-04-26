package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * This custom {@code ItemProcessor} simply writes the information of the
 * processed student to the log and returns the processed object.
 *
 * @author Petri Kainulainen
 */
public class LoggingProcessor implements ItemProcessor<Object, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingProcessor.class);

    @Override
    public Object process(Object item) throws Exception {
        LOGGER.info("Processing student information: {}", item);
        return item;
    }
}