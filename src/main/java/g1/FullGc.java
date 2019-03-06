package g1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * -XX:+PrintGCApplicationStoppedTime
 * -XX:+PrintGCApplicationConcurrentTime
 * -XX:+PrintGCDetails
 * -XX:+PrintSafepointStatistics
 * -XX:PrintSafepointStatisticsCount=1
 * -Xloggc:./gc.log
 * -XX:+UnlockDiagnosticVMOptions
 * -XX:+LogVMOutput
 * -XX:LogFile=./test.log
 * -XX:+UseG1GC
 * -XX:MaxGCPauseMillis=200
 * -XX:InitiatingHeapOccupancyPercent=45
 * -XX:+PrintGCDateStamps
 * -Xmx128m
 * -Xms128m
 */
public class FullGc {
    private static final Collection<Object> leak = new ArrayList<>();
    private static volatile Object sink;

    public static void main(String[] args) {
        while (true) {
            try {
                leak.add(new byte[1024 * 1024]);
                sink = new byte[1024 * 1024];
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                leak.clear();
            }
        }
    }
}