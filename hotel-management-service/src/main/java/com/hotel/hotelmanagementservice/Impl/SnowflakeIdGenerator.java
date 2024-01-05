package com.hotel.hotelmanagementservice.Impl;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Objects;

public class SnowflakeIdGenerator {
    private static final long EPOCH = 1641062400000L; // Start date: January 1, 2023
    private static final long MACHINE_ID_BITS = 5;
    private static final long SEQUENCE_BITS = 12;

    private long machineId;
    private volatile long sequence = 0L;
    private volatile long lastTimestamp = -1L;

    private static SnowflakeIdGenerator instance;

    private SnowflakeIdGenerator() {
        this.machineId = createNodeId();
        if (machineId < 0 || machineId >= (1 << MACHINE_ID_BITS)) {
            throw new IllegalArgumentException("Machine ID must be between 0 and " + ((1 << MACHINE_ID_BITS) - 1));
        }
    }

    public synchronized static SnowflakeIdGenerator getInstance() {
        if (instance == null) {
            instance = new SnowflakeIdGenerator();
        }
        return instance;
    }

    public synchronized Long generateId() {
        long timestamp = System.currentTimeMillis() - EPOCH;

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & ((1 << SEQUENCE_BITS) - 1);

            if (sequence == 0) {
                // Wait until next millisecond
                timestamp = waitNextMillis(timestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return (timestamp << (MACHINE_ID_BITS + SEQUENCE_BITS)) |
                (machineId << SEQUENCE_BITS) |
                sequence;
    }

    private long waitNextMillis(long currentTimestamp) {
        long timestamp = System.currentTimeMillis() - EPOCH;
        while (timestamp <= currentTimestamp) {
            timestamp = System.currentTimeMillis() - EPOCH;
        }
        return timestamp;
    }

    private static int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (Objects.nonNull(mac))
                    for (byte macPort : mac)
                        sb.append(String.format("%02X", macPort));
            }
            nodeId = sb.toString().hashCode();
        } catch (SocketException ex) {
            nodeId = (int) (new SecureRandom().nextInt());
        }
        nodeId = nodeId & ((1 << MACHINE_ID_BITS) - 1);
        return nodeId;
    }
}

