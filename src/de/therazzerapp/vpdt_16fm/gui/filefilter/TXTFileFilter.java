package de.therazzerapp.vpdt_16fm.gui.filefilter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Accepts only files ending with .txt
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.2
 */
public class TXTFileFilter extends FileFilter{
    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Text File";
    }
}
