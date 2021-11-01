package com.example.demo.Auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.utils.Charsets;

/**
 * Client implementation, which sends a message to a {@link Server} and checks
 * the response.
 * 
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
 * {@link MultiLineFilter} - translates List&lt;String&gt; <-> MultiLinePacket (see 1)
 * {@link ClientAuthFilter} - checks, if client is authenticated. If not - initialize client authentication, and only then sends the message.
 * {@link ClientFilter} - client filter, which gets server echo and prints it out.
 *
 */
public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // Create a FilterChain using FilterChainBuilder
        FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
        // Add TransportFilter, which is responsible
        // for reading and writing data to the connection
        filterChainBuilder.add(new TransportFilter());

        // AuthFilter is responsible for client authentication
        filterChainBuilder.add(new ClientAuthFilter());
        // Client filter, which prints out the server echo message
        filterChainBuilder.add(new ClientFilter());
        
        // Create TCP transport
        final TCPNIOTransport transport =
               TCPNIOTransportBuilder.newInstance().build();
        transport.setProcessor(filterChainBuilder.build());

        try {
            // start the transport
            transport.start();

            Future<Connection> connectFuture =
                    transport.connect(Server.HOST, Server.PORT);

            final Connection connection = connectFuture.get(10, TimeUnit.SECONDS);
            
            BufferedReader reader  = new BufferedReader(new InputStreamReader(
                    System.in, Charsets.ASCII_CHARSET));

            while (true) {
                System.out.print("Type the message (Empty line for quit): ");
                String input = reader.readLine();

                if ("".equals(input)) {
                    break;
                }

                // Send echo message
                final MultiLinePacket request = MultiLinePacket.create("echo", input);
                logger.log(Level.INFO, "--------- Client is sending the request:\n{0}", request);

                connection.write(request);
            }
            
        } finally {
            logger.info("Stopping transport...");
            // stop the transport
            transport.shutdownNow();

            logger.info("Stopped transport...");
        }
    }
}