package com.example.demo.Auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Packet, which contains multiple String lines.
 */
public class MultiLinePacket {
    // String lines list
    private final List<String> lines;

    public static MultiLinePacket create() {
        return new MultiLinePacket();
    }
    
    public static MultiLinePacket create(String... lines) {
        final MultiLinePacket packet = new MultiLinePacket();
        packet.getLines().addAll(Arrays.asList(lines));
        
        return packet;
    }

    static MultiLinePacket create(List<String> lines) {
        return new MultiLinePacket(lines);
    }

    private MultiLinePacket() {
        lines = new ArrayList<String>();
    }

    private MultiLinePacket(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Gets the packet string lines.
     * @return the packet string lines.
     */
    public List<String> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(256);

        for (String line : lines) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MultiLinePacket && lines.equals(((MultiLinePacket) obj).lines);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.lines != null ? this.lines.hashCode() : 0);
        return hash;
    }
}