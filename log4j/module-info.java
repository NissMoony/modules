module niss.log4j {
    exports niss.log4j.logger;
    exports niss.log4j.logger.impl;
    provides niss.log4j.logger.ILogger with niss.log4j.logger.impl.SpecificLogger;
}