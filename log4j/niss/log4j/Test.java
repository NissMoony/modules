package niss.log4j;

import niss.log4j.logger.impl.SpecificLogger;

public class Test {
    public static void main(String[] args) {
        new SpecificLogger().info("Aaaaaaaa");
        new SpecificLogger().debug("Aaaaaaaa");
        new SpecificLogger().warn("Aaaaaaaa");
        new SpecificLogger().error("Aaaaaaaa");
    }
}
