package com.Utility;

import java.util.BitSet;

public class Check {
    public static final int MIN_IP_LENGTH = 7;
    public static final int MAX_IP_LENGTH = 15;
    public static final int MAX_INT_NUMBER_IP_IPV4 = 255;
    public static final int IP_PARTS = 4;
    private static final int BIT_IN_BYTES = 8;

    public static boolean checkIP_IPV4(String IP) {
        if (IP == null)
            return false;

        IP = IP.trim();

        if (IP.isBlank())
            return false;
        
        if (IP.length() < MIN_IP_LENGTH)
            return false;

        if (IP.length() > MAX_IP_LENGTH)
            return false;

        if (!IP.matches("[0-9.]+"))
            return false;

        var bytes = IP.split("\\.");

        if (bytes.length != IP_PARTS)
            return false;

        for (String b : bytes) {
            if (b.isEmpty())
                return false;

            if (b.length() > 1 && b.startsWith("0"))
                return false;

            try {
                var n = Integer.parseInt(b);
                if (n > MAX_INT_NUMBER_IP_IPV4)
                    return false;
            } catch (NumberFormatException e) {
                System.err.println("Parsing Error: " + e.getMessage());
                return false;
            }
        }

        return true;
    }

    public static boolean checkSubnetMask(String subnetMask) {
        if(!checkIP_IPV4(subnetMask)) return false;

        String[] parts = subnetMask.split("\\.");

        for(String part : parts) {
            byte b = (byte) Integer.parseInt(part);
            BitSet bits = BitSet.valueOf(new byte[]{b});
            
            boolean foundZero = false;
            for (int i = 0; i < BIT_IN_BYTES; i++) {
                if (!bits.get(i) && foundZero) return false;
                
                if (!bits.get(i)) foundZero = true;
            }

        }

        return true;
    }
}