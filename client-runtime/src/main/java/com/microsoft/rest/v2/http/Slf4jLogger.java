/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.rest.v2.http;

/**
 * An adapter that connects an HttpPipeline.Logger to an slf4j.Logger.
 */
public class Slf4jLogger extends AbstractHttpPipelineLogger {
    private final org.slf4j.Logger slf4jLogger;

    /**
     * Create a new Slf4jLogger with the provided slf4jLogger object.
     * @param slf4jLogger The org.slf4j.Logger to adapt to the HttpPipeline.Logger interface.
     */
    public Slf4jLogger(org.slf4j.Logger slf4jLogger) {
        this.slf4jLogger = slf4jLogger;
    }

    @Override
    public void log(HttpPipelineLogLevel logLevel, String message, Object... formattedArguments) {
        message = format(message, formattedArguments);
        switch (logLevel) {
            case ERROR:
                slf4jLogger.error(message);
                break;

            case WARNING:
                slf4jLogger.warn(message);
                break;

            case INFO:
            default:
                slf4jLogger.info(message);
                break;
        }
    }
}