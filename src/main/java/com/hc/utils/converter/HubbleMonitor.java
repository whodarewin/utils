package com.hc.utils.converter;

import com.immomo.hubble.client.HubbleClient;
import com.immomo.hubble.client.HubbleClientFactory;
import com.immomo.hubble.client.common.MonitorSource;
import com.immomo.hubble.client.monitor.CounterMonitor;
import com.immomo.hubble.client.monitor.HistogramMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HubbleMonitor
 *
 * @author han.congcong
 * @date 2019/8/6
 */

public class HubbleMonitor {

    private static Logger LOGGER = LoggerFactory.getLogger(HubbleMonitor.class);

    private static HubbleClient hubbleClient = HubbleClientFactory.getHubbleClientBySource(MonitorSource.BUSINESS);
    private static final String APPLICATION = "data-transfer";

    private static Map<String, CounterMonitor> counterMonitorCache = new ConcurrentHashMap<String, CounterMonitor>();
    private static Map<String, HistogramMonitor> histogramMonitorCache = new ConcurrentHashMap<String, HistogramMonitor>();

    public static void recordCount(String indicatorKey, int count) {
        try {
            CounterMonitor monitor = counterMonitorCache.get(indicatorKey);
            if (monitor == null) {
                monitor = hubbleClient.newCounter(APPLICATION, indicatorKey);
                counterMonitorCache.put(indicatorKey, monitor);
            }
            monitor.incr(count);
        } catch (Exception e) {
            LOGGER.error("hubble monitor error ", e);
        }
    }

    public static void recordOne(String indicatorKey) {
        recordCount(indicatorKey, 1);
    }

    public static void recordTime(String indicatorKey, long time) {
        try {
            HistogramMonitor monitor = histogramMonitorCache.get(indicatorKey);
            if (monitor == null) {
                monitor = hubbleClient.newHistogram(APPLICATION, indicatorKey);
                histogramMonitorCache.put(indicatorKey, monitor);
            }
            monitor.update(time);
        } catch (Exception e) {
            LOGGER.error("hubble monitor error", e);
        }
    }
}
