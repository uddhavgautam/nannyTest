package com.nexuslab.forensics.grr.nanny;

/**
 * @author gaute
 */
public class Constants {
    /**
     * minimum interval in seconds between two client restarts
     */
    public static final int CLIENT_RESURRECTION_PERIOD = 20;
    /**
     * maximum interval in seconds before the client is considered dead
     */
    public static final int CLIENT_UNRESPONSIVE_TIME = 300 * 1000;
    /**
     * checking interval in seconds
     */
    public static final int HEARTBEAT_CHECK_INTERVAL = 60;
}
