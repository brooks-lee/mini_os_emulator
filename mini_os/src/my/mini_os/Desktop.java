/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mini_os;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.String;
        

/**
 *
 * @author dell
 */
public class Desktop extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Desktop
     */
    JPanel panel;
    final JPopupMenu fold_right_menu;
    public String copy="";public String from_frame=null;
    public ArrayList<String> open;
    Icon folder_button,file_button;
    public Desktop() {
        super("Add component on JFrame at runtime");
        initComponents();
        open=new ArrayList<String>();
        setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        add(panel, BorderLayout.CENTER);
        //JButton button = new JButton("CLICK HERE");
        //add(jMenuItem1, BorderLayout.SOUTH);
        jMenuItem1.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        
        //////folder right menu
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
                File f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop\\"+name);
                String new_name=JOptionPane.showInputDialog(null,"Enter the new name: ","Rename Dialog Box",1);
                if(new_name !=null)
                {
                   b.setText(new_name);
                   File new_f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop\\"+new_name);
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
                   File dir=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop\\"+name);
                   File new_dir=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Dumpster\\"+name);
                   dir.renameTo(new_dir);
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
                   File dir=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop\\"+name);
                   copy=name;
                   from_frame="Desktop";
                   
                                    
                }
            }
        });
        
        
        
       
        
        fold_right_menu.add(renameMenuItem);
        fold_right_menu.add(deleteMenuItem);
        fold_right_menu.add(copyMenuItem);
        
        
        //this.panel.add(fold_right_menu);
        ///////display contents on the screen
        folder_button = new ImageIcon("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\new_folder.jpg");
        file_button = new ImageIcon("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\file.png");
        File screen=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop");
        
        for(File file:screen.listFiles())
        {
            if (file.isDirectory())
            {
                String name=file.getName();
                JButton btn=new JButton (name,folder_button);
                btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                call_blank(name);
            }
        });
                this.panel.add(btn);
                btn.setComponentPopupMenu(fold_right_menu);
                
            }
            if (file.isFile())
            {
                String name=file.getName();
                JButton b=new JButton(name,file_button);
                b.addMouseListener(new MouseListener(){
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String data=open_editor(name);//To change body of generated methods, choose Tools | Templates.
                        new text_editor(data,"Desktop",name).setVisible(true);
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
            
        
    }
    }    
      
    public String open_editor(String name)
    {
        String data="";
        try{ 
                 data=read_file("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop\\"+name);
                  System.out.println("The name of the text file is "+name);
                System.out.println("opened new editor");
                
                
            }
        
                catch (FileNotFoundException evnt) {
        
    }
        return data;
    }
    public String read_file(String fileLocation)
        throws FileNotFoundException {

    File file = new File(fileLocation);

    Scanner scanner = new Scanner(file);

    String fileContents = scanner.nextLine(); // only reads line

    scanner.close();

    
    return fileContents;
}
    
    public void actionPerformed(ActionEvent evt) {
        
        Icon folder_button=new ImageIcon("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\new_folder.jpg");
        File desktop=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop");
        int folders_desktop=0,untitled=0;
        for(File file:desktop.listFiles())
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
                folders_desktop++;
            }
        }
        
        String folder_name="Untitled"+(untitled+1);
        JButton fold=new JButton(folder_name,folder_button);
       
        
                                            
        fold.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt)
                {
                    
                    call_blank(folder_name);
                    
                }
        });
            
        
        this.panel.add(fold);
        fold.setComponentPopupMenu(fold_right_menu);
        this.panel.revalidate();
        validate();
        
        File f=null;
        boolean bool=false;
         try{
            f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop\\"+folder_name);
            bool=f.mkdir();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void call_blank(String folder_name)
    {
        blank_frame contents_frame=new blank_frame("Desktop\\"+folder_name,this);
        contents_frame.setVisible(true);
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Dumpster = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("G19 OS");
        setName("Desktop"); // NOI18N
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mini_os/resources/text_editor.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mini_os/resources/my_computer.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Dumpster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mini_os/resources/recycle_bin_empty.png"))); // NOI18N
        Dumpster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DumpsterActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mini_os/resources/game.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 375, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dumpster, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dumpster, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Desktop");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger())
        {
            jPopupMenu1.show(this,evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_formMouseReleased

 
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        text_editor textpad=new text_editor("");
        textpad.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        blank_frame contents_frame=new blank_frame("My PC",this);
        contents_frame.setVisible(true);
     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
                System.out.println("Inside Paste Function");
                File f=null;
                
                Component to_frame=jPopupMenu1.getInvoker();
                String name=to_frame.getName();
                System.out.println("The frame of the popup menu is "+name);
                System.out.println("The from frame is "+this.from_frame);
                if(name.equals("Desktop"))
                {
                    copy="Copy_"+copy;
                }
                String copy_to="C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\"+name+"\\"+copy;
                
            boolean bool=false;
            try{
                f=new File(copy_to);
                bool=f.mkdir();
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
            JButton b=new JButton(copy,folder_button);
            
            b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                call_blank(copy);
            }
        });
            b.setComponentPopupMenu(fold_right_menu);
            this.panel.add(b);
          
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
              // TODO add your handling code here:
        Component[] components=this.panel.getComponents();
        
              File f=new File("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop")  ;
              for( File file:f.listFiles())
              {   
                  int flag=0;
                  String name=file.getName();
                  for(int i=0;i<components.length;i++)
                {     
                  if(components[i] instanceof JButton)
                    {
                  
                  JButton temp=(JButton)components[i];
                  //System.out.println("the name of the file is "+name+" and the name of the button "+temp.getText());
                  //System.out.println("the length of the file name is "+name.length()+" and the length of the button name  "+temp.getText().length());
                  if(name.equals(temp.getText()))
                  {
                      flag=1;
                      break;
                  }
                  System.out.println("didn't break");
                    }
              }
                  System.out.println("This item is a directory "+file.isDirectory()+"and flag is "+flag);
                  System.out.println("This item is a file "+file.isFile()+"and flag is "+flag);
              if(file.isDirectory() && flag==0)
              {
                  System.out.println("printing directory.");
                  JButton btn=new JButton(name,folder_button);
                  btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                System.out.println("opened new frame");
                call_blank(name);
                
            }
        });
            this.panel.add(btn);
            btn.setComponentPopupMenu(fold_right_menu);
              }
              if(file.isFile() && flag==0)
              {
                  JButton bt=new JButton(name,file_button);
                 bt.addMouseListener(new MouseListener(){
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String data=open_editor(name);//To change body of generated methods, choose Tools | Templates.
                        new text_editor(data,"Desktop",name).setVisible(true);
                        
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
                  /*try{ 
                 String data=read_file("C:\\Users\\dell\\Documents\\OS_asgn\\mini_os\\os_files\\Desktop"+name);
    
                System.out.println("opened new editor");
                text_editor frm=new text_editor(data);
                frm.setVisible(true);
                
            }
        
                catch (FileNotFoundException evnt) {
        
    }*/
                  this.panel.add(bt);
              }
              }
              
    }//GEN-LAST:event_formWindowGainedFocus

    private void DumpsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DumpsterActionPerformed
        // TODO add your handling code here:
         blank_frame contents_frame=new blank_frame("Dumpster",this);
        contents_frame.setVisible(true);
    }//GEN-LAST:event_DumpsterActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        new TictacToe_GameApplet();
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 Desktop d=new Desktop();
                 d.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dumpster;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
