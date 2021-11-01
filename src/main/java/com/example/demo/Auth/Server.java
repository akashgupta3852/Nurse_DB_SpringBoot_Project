package com.example.demo.Auth;

import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Server implementation, which echoes message, only if client was authenticated :)
 * Client and server exchange String based messages:
 *
 * (1)
 * MultiLinePacket = command
 *                   *(parameter LF)
 *                   LF
 * parameter = TEXT (ASCII)
 *
 * Server filters are built in a following way:
 *
 * {@link TransportFilter} - reads/writes data from/to network
 * {@link MultiStringFilter} - translates Buffer <-> List&lt;String&gt;
 * {@link MultiLineFilter} - translates String <-> MultiLinePacket (see 1)
 * {@link ServerAuthFilter} - checks authentication header in an incoming packets.
 * {@link org.glassfish.grizzly.samples.echo.EchoFilter} - sends echo to a client.
 */
public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        // Create a FilterChain using FilterChainBuilder
        FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
        // Add TransportFilter, which is responsible
        // for reading and writing data to the connection
        filterChainBuilder.add(new TransportFilter());

        // AuthFilter is responsible for a client authentication
        filterChainBuilder.add(new ServerAuthFilter());
        // EchoServer sends the client message back
        filterChainBuilder.add(new EchoFilter());

        // Create TCP transport
        final TCPNIOTransport transport =
                TCPNIOTransportBuilder.newInstance().build();
        transport.setProcessor(filterChainBuilder.build());

        try {
            // binding transport to start listen on certain host and port
            transport.bind(HOST, PORT);

            // start the transport
            transport.start();

            logger.info("Press any key to stop the server...");
            System.in.read();
        } finally {
            logger.info("Stopping transport...");
            // stop the transport
            transport.shutdownNow();

            logger.info("Stopped transport...");
        }
    }
}