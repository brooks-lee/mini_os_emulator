/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mini_os;

/**
 *
 * @author dell
 */
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Board  implements WindowListener
{
    @SuppressWarnings("deprecation")
	public void win()
    {
        String message = "Congratulations !!!!! \n    \nYou win \n";
        
        JOptionPane pane = new JOptionPane(message);
        JDialog dialog = pane.createDialog(new JFrame(), "Dilaog");
        dialog.show();
    }
    
    @SuppressWarnings("deprecation")
	public void draw()
    {
        String message = "\n    \nMATCH DRAW \n";
        
        JOptionPane pane = new JOptionPane(message);
        JDialog dialog = pane.createDialog(new JFrame(), "Dilaog");
        dialog.show();
    }
    
    public void windowClosing(WindowEvent we)
    {
        System.exit(0);
    }
    
    public void windowOpened(WindowEvent we)
    {
    }
    
    public void windowClosed(WindowEvent we)
    {
    }
    
    public void windowActivated(WindowEvent we)
    {
    }
    
    public void windowDeactivated(WindowEvent we)
    {
    }
    
    public void windowIconified(WindowEvent we)
    {
    }
    
    public void windowDeiconified(WindowEvent we)
    {
    }
}