module niss.utils {
    requires transitive niss.log4j;
    uses niss.log4j.logger.ILogger;

    exports niss.utils.common;
}