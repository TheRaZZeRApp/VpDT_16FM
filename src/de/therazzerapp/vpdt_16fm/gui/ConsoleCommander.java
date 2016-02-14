package de.therazzerapp.vpdt_16fm.gui;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.2
 */
public class ConsoleCommander {

    /**
     * Contains every console message
     */
    private static ArrayList<String> messages = new ArrayList<>();

    /**
     * Prints a error message to the console.<br>
     * Format: [time] <b color=RED>[Error]</b> + text + \n
     * @param textPane
     *          The console pane
     * @param text
     *          The text to be printed
     */
    public static void sendError(JTextPane textPane, String text){
        messages.add(getTime() + "<b color=RED>[Error]</b> " + text + "<br>");
        textPane.setText(getText());
    }

    /**
     * Prints a info message to the console.<br>
     * Format: [time] <b color=GREEN>[Info]</b> + text + \n
     *
     * @param textPane
     *          The console pane
     * @param text
     *          The text to be printed
     */
    public static void sendInfo(JTextPane textPane, String text){
        messages.add(getTime() + "<b color=GREEN>[Info]</b> " + text + "<br>");
        textPane.setText(getText());
    }

    /**
     *
     * @return
     */
    private static String getText(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String ms : messages){
            stringBuilder.append(ms);
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    private static String getTime(){
        SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm:ss");
        return "[" + time_formatter.format(System.currentTimeMillis()) + "] ";
    }
}
