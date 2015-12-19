package de.therazzerapp.vpdt_16fm;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class CopyToClipboard {

    public void copyTextToClipboard(String text){
        StringSelection stringSelection = new StringSelection (text);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard ();
        clpbrd.setContents (stringSelection, null);
    }

    public void clearClipboard(){
        StringSelection stringSelection = new StringSelection ("");
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard ();
        clpbrd.setContents (stringSelection, null);
    }

}
