/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mini_os;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dell
 */

public class blank_frame extends javax.swing.JFrame implements ActionListener {

    private static String location, root_location = "C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\";
    private JPopupMenu fold_right_menu;
    private JPanel panel;
    private JFrame frame;
    private Desktop d;
    
    Icon folder_button,file_folder;
    /**
     * Creates new form blank_frame
     */
    public blank_frame(String loc,Desktop desk) {
        initComponents();
        this.frame= new JFrame();
        this.d=desk;
        System.out.println("in the constructor.");
        this.location = loc;
        //this.JFrame.setName(location);
        String location_name = root_location+location;
        System.out.println(location_name);
        File base = new File(root_location);
        folder_button = new ImageIcon("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\new_folder.jpg");
        file_folder = new ImageIcon("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\file.png");
        
        setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        add(this.panel, BorderLayout.CENTER);
        //JButton button = new JButton("CLICK HERE");
        //add(jMenuItem1, BorderLayout.SOUTH);
        jMenuItem1.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        
        frame.setName(location);
        
     
        /////folder right menu////////
       fold_right_menu=new JPopupMenu("Options");
        JMenuItem renameMenuItem=new JMenuItem("Rename");
        renameMenuItem.setActionCommand("Rename");
        renameMenuItem.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                Component invoker=fold_right_menu.getInvoker();
                JButton b=new JButton();
                if(invoker instanceof JButton)
                {
                    b=(JButton)invoker;
                }
                String name=b.getText();
                File f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+location+"\\"+name);
                String new_name=JOptionPane.showInputDialog(null,"Enter the new name: ","Rename Dialog Box",1);
                if(new_name !=null)
                {
                   b.setText(new_name);
                   File new_f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+location+"\\"+new_name);
                   f.renameTo(new_f);
                }
            }
        });
        
        JMenuItem deleteMenuItem=new JMenuItem("Delete");
        deleteMenuItem.setActionCommand("Delete");
        deleteMenuItem.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                Component invoker=fold_right_menu.getInvoker();
                if( invoker instanceof JButton)
                {
                   
                   JButton b=(JButton)invoker;
                   System.out.println("The text is "+b.getText());
                   String name=b.getText();
                   File dir=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+location+"\\"+name);
                   File new_dir=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Dumpster\\"+name);
                   
                   File recycle_bin=new File("C:\\Users\\dell\\Desktop\\Recycle Bin\\"+name);
                   System.out.println("the name of the frame is "+frame.getName());
                   if (frame.getName().equals("Dumpster"))
                   {
                       System.out.println("True");
                       //boolean t=dir.renameTo(recycle_bin);
                       //System.out.println("rename is "+t);
                       dir.delete();
                   }
                   else
                   {
                       boolean t=dir.renameTo(new_dir);
                       
                       System.out.println("rename is not dumpster "+t);
                   }
                   Container parent=b.getParent();
                   parent.remove(b);
                   parent.validate();
                   parent.repaint();
                   // change the icon of recycle bin here........
                   
                   
                }
            }
        });
        
        JMenuItem copyMenuItem=new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");
        copyMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 Component invoker=fold_right_menu.getInvoker();
                if( invoker instanceof JButton)
                {
                   JButton b=(JButton)invoker;
                   System.out.println("The text is "+b.getText());
                   String name=b.getText();
                   File dir=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+location+"\\"+name);
                   d.copy=name;
                   d.from_frame=location;
                 
                }
            }
        });
        
        
        
       
        
        fold_right_menu.add(renameMenuItem);
        fold_right_menu.add(deleteMenuItem);
        fold_right_menu.add(copyMenuItem);
        
           File screen=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+this.location);
        
        for(File file:screen.listFiles())
        {
            
                System.out.println("display existing folders.");
                if (file.isDirectory())
            {
                String name=file.getName();
                System.out.println(" Directory found");
                JButton btn=new JButton(name,folder_button);
                btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("opened new frame");
                blank_frame f=new blank_frame(location+"\\"+name,d);
                f.setVisible(true);
                
            }
        });
                this.panel.add(btn);
                btn.setComponentPopupMenu(fold_right_menu);
            }
            if (file.isFile())
            {
                System.out.println("file on screen.");
                String name=file.getName();
                System.out.println("File found");
                JButton btn=new JButton(name,file_folder);
                File text=new File(root_location+name);
                System.out.println("the location is "+location+"and the name is "+name);
                btn.addMouseListener(new MouseListener(){
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String data=open_editor(location+"\\"+name);//To change body of generated methods, choose Tools | Templates.
                        new text_editor(data,location,name).setVisible(true);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }
                });
                this.panel.add(btn);
            }
            
            
        
    }
        //frame.add(this.panel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("New Folder");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Paste");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String read_file(String fileLocation)
        throws FileNotFoundException {

    File file = new File(fileLocation);

    Scanner scanner = new Scanner(file);

    String fileContents = scanner.nextLine(); // only reads line

    scanner.close();

    
    return fileContents;
}
    
    public String open_editor(String name)
    {
        String data="";
        try{ 
                 data=read_file("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+name);
                 System.out.println("the path of the file is "+"C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+name);
                  System.out.println("The name of the text file is "+name);
                System.out.println("opened new editor");
                
                
            }
        
                catch (FileNotFoundException evnt) {
        
    }
        return data;
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        
        if(evt.isPopupTrigger())
        {
            jPopupMenu1.show(this,evt.getX(),evt.getY());
            
        }
      
    }//GEN-LAST:event_formMouseReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
         
                File f=null;
                
                String name=frame.getName();
                System.out.println("The frame of the popup menu is "+name);
                System.out.println("The from frame is "+d.from_frame);
                if  (name.equals(d.from_frame))
              {
                  d.copy="Copy_"+d.copy;
              }
                String copy_to="C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+name+"\\" +d.copy;
                System.out.println("The copy to location is "+copy_to);
            boolean bool=false;
            try{
                f=new File(copy_to);
                bool=f.mkdir();
                System.out.println("File copy status "+bool);
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
             
            JButton b=new JButton(d.copy,folder_button);
            b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("opened new frame");
                blank_frame f=new blank_frame(location+"\\"+d.copy,d);
                f.setVisible(true);
                
            }
        });
            b.setComponentPopupMenu(fold_right_menu);
            this.panel.add(b);
         
            
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        Component[] components=this.panel.getComponents();
        
        
             System.out.println("the current space is "+root_location+location);
              File f=new File(root_location+location)  ;
              if(!f.isFile())
              {
                  System.out.println("The clicked item is a file "+f.isFile());
                  for( File file:f.listFiles())
              {   int flag=0;
                  String name=file.getName();
                  for(int i=0;i<components.length;i++)
                {     
                  if(components[i] instanceof JButton)
                    {
                  
                  JButton temp=(JButton)components[i];
                  if(name.equals(temp.getText()))
                  {
                      flag=1;
                      break;
                  }
                    }
              }
              if(file.isDirectory() && flag==0)
              {
                  JButton btn=new JButton(name,folder_button);
                  btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                System.out.println("opened new frame");
                blank_frame f=new blank_frame(location+"\\"+name,d);
                f.setVisible(true);
                
            }
        });
            this.panel.add(btn);
              }
              if(file.isFile() && flag==0)
              {
                  System.out.println("file inside a folder");
                  JButton btn=new JButton(name,file_folder);
                  this.panel.add(btn);
                  
                   btn.addMouseListener(new MouseListener(){
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String data=open_editor(location+"\\"+name);//To change body of generated methods, choose Tools | Templates.
                        
                        new text_editor(data,location,name).setVisible(true);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }
                });
    
                  
              }
              }
            
              }
              if(f.isFile())
              {
                  
                  String name="";
                  for(String st:location.split("\\"))
                  {
                      name=st;
                  }
                  JButton b=new JButton(name,file_folder);
                  b.addMouseListener(new MouseListener(){
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String data=open_editor(location);//To change body of generated methods, choose Tools | Templates.
                        
                        new text_editor(data,"",location).setVisible(true);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }
                });
                  this.panel.add(b);
              }
        
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * @param args the command line arguments
     */
    public final void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(blank_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(blank_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(blank_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(blank_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new blank_frame().setVisible(true);
                
                
            }
        });
        
        

    }

    
    
    public void actionPerformed(ActionEvent evt) {
        
        
        Icon folder_button=new ImageIcon("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\new_folder.jpg");
        File screen=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+this.location);
        int folders_=0,untitled=0;
        for(File file:screen.listFiles())
        {
            if (file.isDirectory())
            {
                String name=file.getName();
                String pattern="Untitled\\d*";
                Pattern r=Pattern.compile(pattern);
                Matcher m=r.matcher(name);
                if(m.find()){
                    if(untitled<Integer.parseInt(name.substring(8)))
                        untitled=Integer.parseInt(name.substring(8));
                }
                folders_++;
                
            }
        
               
        }
        String folder_name="Untitled"+(untitled+1);
        JButton btn=new JButton(folder_name,folder_button);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("opened new frame");
                blank_frame f=new blank_frame(location+"\\"+folder_name,d);
                f.setVisible(true);
                
            }
        });
        this.panel.add(btn);
        btn.setComponentPopupMenu(fold_right_menu);
        System.out.println("added from here");
        this.panel.revalidate();
        validate();
        
        File f=null;
        boolean bool=false;
         try{
            f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+this.location+"\\"+folder_name);
            bool=f.mkdir();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
