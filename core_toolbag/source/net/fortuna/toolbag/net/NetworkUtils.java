package net.fortuna.toolbag.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility methods regarding network-related tasks.
 * @author Ben Fortuna
 */
public final class NetworkUtils {

    /**
     * Constructor made private to prevent instantiation.
     */
    private NetworkUtils() {
    }
    
    /**
     * Identifies all bound ports between start and end.
     * @param start starting port
     * @param end ending port
     * @return a collection of all ports in use (represented
     * by Integers)
     */
    public static Collection boundPorts(final int start, final int end) {
        Collection ports = new ArrayList();

        ServerSocket ss;

        for (int i = start; i <= end; i++) {

            try {
                ss = new ServerSocket(i);
            }
            catch (IOException e) {
                // port is bound..
                ports.add(new Integer(i));
            }
        }

        return ports;
    }

    /**
     * Attempts to load the specified URL in the operating system's default
     * browser.
     * NOTE: currently only supports Windows
     * @param url a url specifying the document to open
     * @throws IOException if unable to execute the open command
     * @throws InterruptedException if interrupted during the
     * open command
     */
    public static void showDocument(final URL url) throws IOException,
            InterruptedException {
        StringBuffer command = new StringBuffer();
        command.append("cmd.exe /c start ");
        //command.append('"');
        command.append(url);
        //command.append('"');

        Process process = Runtime.getRuntime().exec(command.toString());
        process.waitFor();
    }

    /**
     * Returns the base URL of the specified URL
     * 
     * @param url
     *            a url
     * @return the base url of the specified url (ie. the remote directory in
     *         which it is located)
     * @throws MalformedURLException
     *             where the base path does not form a valid url
     */
    public static URL getUrlBase(final URL url) throws MalformedURLException {
        String urlString = url.toString();

        return new URL(urlString.substring(0, urlString.lastIndexOf('/')));
    }
}