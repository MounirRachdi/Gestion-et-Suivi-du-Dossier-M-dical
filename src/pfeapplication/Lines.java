/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfeapplication;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineListener;
import javax.swing.*;
import java.util.Date;
import java.util.Calendar;


/**
 *
 * @author PC-ASUS
 */

public class Lines extends javax.swing.JFrame {
    
       Calendar cal=Calendar.getInstance();
      int i;
       int taille;
       Date dt=new Date();
       int[] month =new int[12];
       int[] jours=new int[31];
       int anne;
       ArrayList<Date> dates = new ArrayList<>();
       ArrayList<Date> datesorti = new ArrayList<>();
       //{AMK,AUG,CFT,CFZ,CLY,CPX,CTR,IMI,OFX,OXC,PPR,PPT,IEI,VCM};
    //new String[];
    
    /**
     * Creates new form Lines
     */
       public int jour;
     public  int nbrjour;
 int x=63,y=100;
 int x1=197, y2=143;
 int xhaut=220, yhaut=33;
 int xbas=220, ybas=480;
   private Connexion con;
  public static String ind;
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-YYYY ");//dd/MM/yyyy
      SimpleDateFormat daterea=new SimpleDateFormat("dd-MM-YYYY ");
      String dt_rea=daterea.format(dt);
    Date now = new Date();
    String strDate = sdfDate.format(now);
public static JLabel l=new JLabel();
               boolean var=true;
          //   JLabel[] Labelhaut ;
               int semaine=jour/7;
               private ArrayList<JLabel> Labelhaut = new ArrayList<JLabel>();
             private ArrayList<JLabel> Labelbas = new ArrayList<JLabel>();
             private ArrayList<JLabel> reflab = new ArrayList<JLabel>();
 courbe c=new courbe();
 rempli_emplDAO remp=new rempli_emplDAO();
    public Lines(String ind) throws ClassNotFoundException, SQLException, IOException  {
       this.ind=ind;
        initComponents();
  
   jLabel8.setVisible(false);
   jLabel21.setVisible(false);
 //remp.rempli();
  jLabel22.setText(strDate);
//jLabel17.setText(ind);
		
 c.setBounds(1, 1, 930, 520);
 jPanel1.add(c);
 
             
              
             // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void changer_jour_haut()
    {
      //  Labelhaut= new JLabel[jour];
        
      for(i=0;i<7;i++)
      { 
        JLabel lab1=new JLabel();
    
      lab1.setName("nb"+jour);
     
      lab1.setForeground(Color.DARK_GRAY);
      
      
      lab1.setBounds(xhaut, yhaut,70, 50 );
      
     
     
     lab1.setText(dates.get(i).toString());//i+"/"+month);
     Labelhaut.add(lab1);
   
     
     jPanel1.add(lab1); xhaut+=100;
      
      }
 //   nbrjour=i;
   xhaut=220;
 
    }
    public void changer_jour_bas()
    {
    
    
      for(i=0;i<7;i++)
      {   
      JLabel lab2=new JLabel();
     
     lab2.setName("nb"+jour);
     
      
      lab2.setForeground(Color.DARK_GRAY);
      
      
      lab2.setBounds(xbas, ybas,70, 50);
     
        lab2.setText(dates.get(i).toString());//i+"/"+month);
      xbas+=100;
     Labelbas.add(lab2);
        jPanel1.add(lab2);
  
      
      }
    
    //xhaut=220;
  //  yhaut=33;
  xbas=220;
  ybas=480;
    
    }
    public void setvisible(boolean val)
    {
    
   
    jPanel1.setVisible(val);
   
    
    }

public static String addDays(String date, Integer days){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = "";
		try {
			Date df = sdf.parse(date);
			df.setTime(df.getTime() + (days * 24 * 3600 * 1000));
			result = String.valueOf(df.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
public void patient(String id) throws ClassNotFoundException, SQLException, IOException
{
   // JLabel[] lab=nbrantibio();
con=new Connexion();
    Connection cnx= con.connect();
       Statement st1 = cnx.createStatement();

     String Query = "select distinct p.nom, p.pre, h.ind, pr.ref, pr.dose, h.dentRea, h.dsortRea, pr.dt as date_dosage from patient p, hospital h, presAnti pr "
             + "where h.ind=p.ind and pr.dos=h.dos and p.ind like '"+id+"%' order by pr.dt";
ResultSet rs1; 
        rs1 = st1.executeQuery(Query);
        ResultSetMetaData resultMeta = rs1.getMetaData();     
      //int i=0;
        while(rs1.next() && rs1.getDate("date_dosage").before(rs1.getDate("dsortRea"))){         
        //for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            if(!dates.contains(rs1.getDate("date_dosage")))
            dates.add(rs1.getDate("date_dosage"));
          jLabel17.setText("Nom: "+ rs1.getObject("nom"));
            jLabel16.setText("prenom: "+rs1.getObject("pre"));
      //  System.out.println("\n---------------------------------");
        float dos=rs1.getFloat("dose");
     //jour=rs1.getInt("Jour");
        //cal.setTime(rs1.getDate("dentRea"));
   /*  changer_jour_haut(jour, rs1.getDate("dentRea"));
     changer_jour_bas(jour, rs1.getDate("dentRea"));*/
     
        //month=cal.get(Calendar.MONTH);
    dt=rs1.getDate("dentRea");
     //dates.add(rs1.getDate("dentRea"));
        JLabel lab=new JLabel();
        lab.setName(rs1.getString("ref"));
        lab.setForeground(Color.blue);
        
                     lab.setBounds(x, y, 70, 14);
                     lab.setText(lab.getName());
        if(!reflab.contains(lab))
        {   reflab.add(lab);
                     jPanel1.add(lab);}
        if(dos>=0 && dos <=2)
        { JButton b=new JButton();
        b.setBackground(Color.green);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y, 39, 10);
        b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;}
        else if(dos>2 && dos <6)
        {JButton b=new JButton();
        b.setBackground(Color.blue);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 50, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;}
        else if(dos>5 && dos<11){
        
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 62, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;
        }
          else if(dos>10 && dos<51){
        
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 75, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;
        }
          else if(dos>50 && dos<201){
        
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 93, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;
        }  else if(dos>200 && dos<401){
        
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 109, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;
        }
          else if(dos>400 && dos<801){
        
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 149, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;
        }
          else {
        
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(rs1.getObject("dose").toString());
        b.setBounds(x1, y-10, 159, 13);
         b.setToolTipText( rs1.getString("ref").trim()+","+dos);
        jPanel1.add(b);
        x1+=100;
        }
            y+=50;
             
        
          
      }

 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(990, 750));
        setPreferredSize(new java.awt.Dimension(980, 700));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 210, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Antibiotique");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Acte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 130, -1));

        jLabel22.setText("jLabel22");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 170, 30));

        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 500));

        jLabel21.setText("Jours");

        jLabel8.setText("Antibio");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/precedent-icone-5823-16.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/suivant-icone-7661-16.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel8))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton3)
                .addGap(801, 801, 801)
                .addComponent(jButton5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel8)
                .addGap(405, 405, 405)
                .addComponent(jLabel21)
                .addGap(166, 166, 166)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton5)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 900, 530));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/suivant-icone-7661-16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 660, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/precedent-icone-5823-16.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
  
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
       
    }//GEN-LAST:event_formMouseMoved

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
      //  jButton6.setSize(60, 20);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

       
     
        
        
           // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       
           try {
               patient(ind);
                 jLabel8.setVisible(true);
                 changer_jour_haut();
               changer_jour_bas();
                /* for(int i=0;i<dates.size();i++)
                     System.out.println(dates.get(i).toString());*/
   jLabel21.setVisible(true);
   jPanel1.revalidate();
               jPanel1.repaint();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(Lines.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(Lines.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(Lines.class.getName()).log(Level.SEVERE, null, ex);
           }

                  
              
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
      //  changer_jour();
        
     
  //   while(i<month )
      for(int j=0;j<7;j++)
       { 
        if(i<dates.size())
        {   Labelhaut.get(j).setText(dates.get(i).toString());
        Labelbas.get(j).setText(dates.get(i).toString());}
        else
        {     
   Labelhaut.get(j).setVisible(false);
   Labelbas.get(j).setVisible(false);
        }
           
               jPanel1.repaint();
       
      i++; }
   
             
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         
    i=0;
      for(int j=0;j<7;j++)
       { 
        
        Labelhaut.get(i).setText(dates.get(j).toString());
        Labelbas.get(i).setText(dates.get(j).toString());
        //jPanel1.remove(Labelhaut.get(i));
   //Labelhaut.remove(i);
            Labelhaut.get(j).setVisible(true);
   Labelbas.get(j).setVisible(true);
               jPanel1.repaint();
       
       i++;
       }
    }//GEN-LAST:event_jButton6ActionPerformed


    /**
     * @param args the command line arguments*/
     
   //rempli_emplDAO remp=new  rempli_emplDAO();
  public static void main(String args[]) throws ClassNotFoundException, SQLException {
       // jComboBox1.removeAllItems();
      
      
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        / * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html */
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
         
            public void run() {
                
               
          
                try {
                  new Lines(ind).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Lines.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Lines.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Lines.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                
           
               
            } 
           
        });
     
      
   }
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
//public void paint( Graphics g ) {
//      
//     
//         g.drawLine(0, 0,200, 300 );
//         
//      }

   
public class rempli_emplDAO {
     Connexion cnxx=new Connexion();
    Connection conn;
    private Statement ste;
int i=0;
        public rempli_emplDAO() {
            
        }
 
    public void rempli() throws ClassNotFoundException, SQLException {
        cnxx.loadDriver();
        conn = cnxx.connect();
        
        JComboBox Jbox = new JComboBox();
        try {
            ste = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("Erreur connexion");
        }
         
        String sql = "SELECT ind FROM patient";
        ResultSet Rs = null;
        try {
            Rs = ste.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Erreur Requête");
        }
       try {
            while (Rs.next()) {
                
                
              // jComboBox1.addItem(Rs.getString("ind"));
               
                
             //   System.out.println(Rs.getString("nom"));
            }
        } catch (SQLException ex) {
                System.out.println("Erreur Rs");
        }
         
        
         
       // return Jbox;
    }
    }

}