package de.therazzerapp.vpdt_16fm;

import java.io.*;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.2
 */
public class FileReader {

    /**
     *
     * @param file
     * @return
     */
    public static String getFileContent(File file){

        StringBuilder text = new StringBuilder();
        BufferedReader br;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while (line != null) {
                text.append(line);
                line = br.readLine();
            }
        } catch (IOException ioe) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
            }
        }

        return text.toString();

    }
}
