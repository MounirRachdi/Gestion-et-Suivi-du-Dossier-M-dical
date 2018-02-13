/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfeapplication;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static pfeapplication.Lines.ind;


/**
 *
 * @author Mounir
 */
public class Visualisation extends javax.swing.JFrame {

    /**
     * Creates new form Visualisation 09/18789  
     */
     Calendar cal=Calendar.getInstance();
      int i;
       int taille;
       Date dt=new Date();
       int[] month =new int[12];
       int[] jours=new int[31];
       int anne;
       ArrayList<Date> dates = new ArrayList<Date>();
       ArrayList<Date> datesorti = new ArrayList<>();
       //{AMK,AUG,CFT,CFZ,CLY,CPX,CTR,IMI,OFX,OXC,PPR,PPT,IEI,VCM};
    //new String[];
    
    /**
     * Creates new form Lines
     */
       Courbe2 c2=new Courbe2();
       public int jour;
     public  int nbrjour;
 int x=43,y=100;
 int x1=197, y2=143;
 int xhaut=200, yhaut=33;
 int xbas=200, ybas=480;
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
              String nom;
              String prenom;
               private ArrayList<JLabel> Labelhaut = new ArrayList<JLabel>();
             private ArrayList<JLabel> Labelbas = new ArrayList<JLabel>();
               private ArrayList<JLabel> labacte = new ArrayList<JLabel>();
             private ArrayList<JButton> bouton = new ArrayList();
             private ArrayList<JLabel> labname = new ArrayList();
             private ArrayList<JLabel>labnom_act=new ArrayList();
 courbe c=new courbe();
  Acte ac=new Acte();
  int day=0;
  Image img1 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\tri_bleu.png"));
ImageIcon icon1 = new ImageIcon(img1);
 
Image img2 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\tri_move.png"));
 ImageIcon icon2 = new ImageIcon(img2);
 
 Image img3 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\tri_rose.png"));
 ImageIcon icon3 = new ImageIcon(img3);
 
 Image img4 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\triangle_vert.png"));
ImageIcon icon4 = new ImageIcon(img4);

Image img5 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\tringle_rouge.png"));
 ImageIcon icon5 = new ImageIcon(img5);
 
 Image img6 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\vert2.png"));
 ImageIcon icon6 = new ImageIcon(img6);
 
 Image img7 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\bleu2.png"));
 ImageIcon icon7 = new ImageIcon(img7);
 
 Image img8 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\rouge2.png"));
 ImageIcon icon8 = new ImageIcon(img8);
 
 Image img9 =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\noire.png"));
 ImageIcon icon9 = new ImageIcon(img9);
 
String act[];
 /**************************************************/
    public Visualisation(String ind) throws ClassNotFoundException, SQLException, IOException {
        initComponents();
       this.ind=ind;
        initComponents();
 
   //jLabel8.setText("");
   //jLabel21.setText("");
 //remp.rempli();
//  jLabel22.setText(strDate);
//jLabel17.setText(ind);
		patient();
                changer_jour_haut();
                changer_jour_bas();
                // for(int k=0;k<labname.size();k++)
                  //   labname.get(k).setVisible(false);
                 getSex();

 c.setVisible(false);
 jLabel17.setText("Nom: "+nom );
            jLabel16.setText("prenom: "+prenom);
            SetVisibleAct(false);
    }
      public void changer_jour_haut()
    {
      //  Labelhaut= new JLabel[jour];
        
      for(i=0;i<7;i++)
      { if(i<dates.size())
      { JLabel lab1=new JLabel();
    
      lab1.setName("nb"+jour);
     
      lab1.setForeground(Color.DARK_GRAY);
      
      
      lab1.setBounds(xhaut, yhaut,70, 50 );
      
     
     
     lab1.setText(dates.get(i).toString());//i+"/"+month);
     Labelhaut.add(lab1);
   
     
     jPanel1.add(lab1); xhaut+=100;
      }
      }
   nbrjour=i;
   xhaut=220;
 
    }
     
    public void changer_jour_bas()
    {
    
    
      for(i=0;i<7;i++)
      {   if(i<dates.size()){
      JLabel lab2=new JLabel();
     
     lab2.setName("nb"+jour);
     
      
      lab2.setForeground(Color.DARK_GRAY);
      
      
      lab2.setBounds(xbas, ybas,70, 50);
     
        lab2.setText(dates.get(i).toString());//i+"/"+month);
      xbas+=100;
     Labelbas.add(lab2);
        jPanel1.add(lab2);
       
  
      }
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
int nbline=0;
public void patient() throws ClassNotFoundException, SQLException, IOException
{

   // JLabel[] lab=nbrantibio();
con=new Connexion();
    Connection cnx= con.connect();
       Statement st1 = cnx.createStatement();

     String Query = "select distinct p.nom, p.pre, h.ind, pr.ref, pr.dose, h.dentRea, h.dsortRea, pr.dt as date_dosage from patient p, hospital h, presAnti pr "
             + "where h.ind=p.ind and pr.dos=h.dos and p.ind like '"+ind+"%' order by pr.dt";
ResultSet rs1; 
        rs1 = st1.executeQuery(Query);
        ResultSetMetaData resultMeta = rs1.getMetaData();     
      //int i=0;
        while(rs1.next() ){         
      
                  if(!dates.contains(rs1.getDate("date_dosage"))&& !rs1.getDate("date_dosage").after(rs1.getDate("dsortRea")))
            dates.add(rs1.getDate("date_dosage"));
          
      //  System.out.println("\n---------------------------------");
        float dos=rs1.getFloat("dose");
     
     
        //month=cal.get(Calendar.MONTH);
    dt=rs1.getDate("dentRea");
     //dates.add(rs1.getDate("dentRea"));
  /*  while(res2.next())
    { JLabel lab=new JLabel();
        lab.setName(res2.getString("ref"));
        lab.setForeground(Color.blue);
        
                     lab.setBounds(x, y, 70, 14);
                     lab.setText(lab.getName());
        
                   
     
        jPanel1.add(lab);
      
        
        y+=50;}*/
        }
       day=dates.size()/7;
       // paintbotton(id,0,dates.size());
      
 
 }
public void Paintlab() throws ClassNotFoundException, SQLException
{  y=100;
con=new Connexion();
Connection cnx= con.connect();
 String req="select distinct pr.ref, a.lib from patient p, hospital h, presAnti pr, antibiotique a" +
" where h.ind=p.ind and a.ref=pr.ref and pr.dos=h.dos and p.ind like '"+ind+"%'";
          Statement st = cnx.createStatement();
          
          ResultSet res; 
        res = st.executeQuery(req);
        while(res.next())
        {
        JLabel lab=new JLabel();
        lab.setName(res.getString("lib").trim());
        lab.setForeground(Color.blue);
        
                     lab.setBounds(x, y,120, 14);
                     lab.setText(lab.getName());
        
                   
     labname.add(lab);
        jPanel1.add(lab);
        jPanel1.repaint();
      nbline++;
        
        y+=70;
        
        }
       
}
public void getSex() throws ClassNotFoundException, SQLException, IOException
{
String req="select nom, pre, sex from patient where ind like '"+ind+"%'";
String sex;
con=new Connexion();
    Connection cnx= con.connect();
       Statement st1 = cnx.createStatement();
       ResultSet res=st1.executeQuery(req);
       while(res.next())
       { nom=res.getString("nom");
         prenom=res.getString("pre");
           if("Masculin".equals(res.getString("sex").trim()))
           {
           Image img =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\homme.png"));
ImageIcon icon = new ImageIcon(img);
jLabel2.setIcon(icon);
           }
           else
           {
           Image img =ImageIO.read(new File("E:\\Proj_majed\\PFEapplication\\src\\image\\femme.png"));
ImageIcon icon = new ImageIcon(img);
jLabel2.setIcon(icon);
           System.out.print(ind);
           }
       
       
       }
}

public void paintbotton(String idd, int indice, int size) throws ClassNotFoundException, SQLException
{
con=new Connexion ();
Connection cnx=con.connect();
Statement st1 = cnx.createStatement();
int lbn=0;
    
    
for(int it=indice;it<size;it++){
     if(it<Labelhaut.size())
   {
String req="select distinct h.ind, pr.ref, pr.dose, h.dentRea, h.dsortRea, pr.dt as date_dosage from patient p, hospital h, presAnti pr " +
"where h.ind=p.ind and pr.dos=h.dos and p.ind like '"+idd+"%' and pr.dt='"+dates.get(it)+"'";
ResultSet res; 
        res = st1.executeQuery(req);
     y=100;
    
        while(res.next() )
        {
      
             float dos=res.getFloat("dose");
        if(dos>=0 && dos <=2 )
        {// y=100;
        JButton b=new JButton();
        b.setBackground(Color.green);
        b.setName(res.getObject("dose").toString());
       
        b.setBounds(Labelhaut.get(it).getX(), y, 30, 10);
       
        b.setToolTipText( res.getString("ref").trim()+","+dos);
        System.out.print("tttolllti= "+b.getToolTipText());
        bouton.add(b);
        jPanel1.add(b);
        //x1+=100;
       
        }
        else if(dos>2 && dos <6 )
        {//y=100;
            JButton b=new JButton();
             
       
        b.setBackground(Color.blue);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y, 50, 13);
      
        b.setBounds(Labelhaut.get(it).getX(), y, 50, 13);
        
         b.setToolTipText( res.getString("ref").trim()+","+dos);
         bouton.add(b);
        jPanel1.add(b);
       // x1+=100;
        
             
      
        }
        else if(dos>5 && dos<11 ){
        
       // y=100;
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y-10, 62, 13);
       
        b.setBounds(Labelhaut.get(it).getX(), y, 62, 13);
        
         b.setToolTipText( res.getString("ref").trim()+","+dos);
          System.out.print("tttolllti= "+b.getToolTipText());
         bouton.add(b);
        jPanel1.add(b);
        //x1+=100;
       
        }
          else if(dos>10 && dos<51 ){
       // y=100;
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y-10, 75, 13);
     
        b.setBounds(Labelhaut.get(it).getX(), y, 75, 13);
        
         b.setToolTipText( res.getString("ref").trim()+","+dos);
         bouton.add(b);
        jPanel1.add(b);
        //x1+=100;
        
        }
          else if(dos>50 && dos<201 ){
       // y=100;
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y-10, 93, 13);
       
        b.setBounds(Labelhaut.get(it).getX(), y, 93, 13);
       
         b.setToolTipText( res.getString("ref").trim()+","+dos);
         bouton.add(b);
        jPanel1.add(b); 
        //x1+=100;
        }  else if(dos>200 && dos<401 ){
       // y=100;
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y-10, 109, 13);
       
        b.setBounds(Labelhaut.get(it).getX(), y, 109, 13);
      
         b.setToolTipText( res.getString("ref").trim()+","+dos);
         bouton.add(b);
        jPanel1.add(b);
        //x1+=100;
        }
          else if(dos>400 && dos<801 ){
      JButton b=new JButton();
        
        b.setBackground(Color.red);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y-10, 149, 13);
      
        b.setBounds(Labelhaut.get(it).getX(), y, 149, 13);
       
         b.setToolTipText( res.getString("ref").trim()+","+dos);
         bouton.add(b);
        jPanel1.add(b); 
        
        //x1+=100;
        }
          else {
       // y=100;
        
        JButton b=new JButton();
        b.setBackground(Color.red);
        b.setName(res.getObject("dose").toString());
       // b.setBounds(Labelhaut.get(it).getX(), y-10, 159, 13);
        
        b.setBounds(Labelhaut.get(it).getX(), y, 159, 13);
     
         b.setToolTipText( res.getString("ref").trim()+","+dos);
         bouton.add(b);
        jPanel1.add(b); 
        
        }
        y+=70;
    
   
   
       } //x1+=100;
 }//x1+=100;y+=50;
  //y+=70; 
    
jPanel1.repaint();
}}
 public void paintRect(int indice, int size) throws ClassNotFoundException, SQLException
      {
    
    con=new Connexion();
    Connection cn=con.connect();
    Statement st=cn.createStatement();

int i=0;
int lbn=0;
for(int it=indice;it<size;it++){
  y=100;  
  if(it<Labelhaut.size())
   {
       String req="select distinct v.cacte, v.dact from verifierActe v, acte a, hospital h, patient p where a.cacte=v.cacte" +
" and v.dos=h.dos and p.ind=h.ind and  h.ind like '"+ind+"%' and v.dact='"+dates.get(it)+"'";
ResultSet res=st.executeQuery(req);
      while(res.next())
{ String a=res.getString("cacte");
if("CAT       ".equals(a))
{
    JLabel lab=new JLabel();
    lab.setIcon(icon1);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
    lab.setToolTipText(" tool"+a.trim());
   
    labacte.add(lab);
    jPanel1.add(lab);//
    
}
else if("DLCR      ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon2);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
     lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);
}
else if("IT        ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon3);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
     lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
else if("KTC       ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon4);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
     lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
else if("PIV       ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon5);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
     lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
else if("SU        ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon6);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
    lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
else if("T         ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon7);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
     lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
else if("VA        ".equals(a))
{
JLabel lab=new JLabel();
    lab.setIcon(icon8);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setName(a);
     lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
else
{
JLabel lab=new JLabel();
    lab.setIcon(icon9);
    lab.setBounds(Labelhaut.get(it).getX(), y, 34, 34);
    lab.setToolTipText(" tool"+a.trim());
    labacte.add(lab);
    jPanel1.add(lab);

}
//lbn++;
    //if(lbn==labname.size())
       ///lbn=0 ;
y+=50;
}
      
  }   jPanel1.repaint(); 
}
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 170, 30));

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/azer.jpg"))); // NOI18N
        jButton4.setText("Antibiotique");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setBorderPainted(false);
        jButton4.setHideActionText(true);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.setName(""); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 90, 40));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/azer.jpg"))); // NOI18N
        jButton1.setText("Acte");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setBorderPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 500));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

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
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton3)
                        .addGap(801, 801, 801)
                        .addComponent(jButton5)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel8)
                .addGap(428, 428, 428)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton5)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 930, 530));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 140, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/precedent-icone-5823-16.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/suivant-icone-7661-16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 660, -1, -1));

        jButton7.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/azer.jpg"))); // NOI18N
        jButton7.setText("Retour");
        jButton7.setBorder(null);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 90, 40));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 90));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void paintActe() throws ClassNotFoundException, SQLException
{
    y=100;
    con=new Connexion();
    Connection cn=con.connect();
   
    Statement st=cn.createStatement();
String req="select distinct a.libact from verifierActe v, acte a, hospital h, patient p where a.cacte=v.cacte" +
" and v.dos=h.dos and p.ind=h.ind and  h.ind like '"+ind+"%'";
ResultSet res=st.executeQuery(req);
 jLabel1.setText("Acte");
for(int j=0;j<labname.size();j++)
labname.get(j).setVisible(false);
for(int j=0;j<bouton.size();j++)
    bouton.get(j).setVisible(false);
while(res.next())
{

System.out.println(res.getString("libact"));//+res.getDate("dact"));
System.out.println();
JLabel lab=new JLabel();
        lab.setName(res.getString("libact"));
        lab.setForeground(Color.blue);
        
                     lab.setBounds(x, y, 120, 14);
                     lab.setText(lab.getName());
        
                   
     labnom_act.add(lab);
        jPanel1.add(lab);
      
        
        y+=50;
}
jPanel1.revalidate();
jPanel1.repaint();
} 
boolean click=false;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       //c.setVisible(true);
        jPanel1.removeAll();
             c2.setVisible(false);
             ac.setVisible(false); 
             SetVisibleAct(false);
             c.setVisible(true);
             c.setBounds(1, 1, 930, 520);
jLabel1.add(c);
this.getContentPane().add(jLabel1);
 jPanel1.repaint(); 
         /* for(int i=0;i<labname.size();i++)
    labname.get(i).setVisible(false);
        for(int b=0;b<labacte.size();b++)
            labacte.get(b).setVisible(false);
for(int j=0;j<Labelhaut.size();j++)
{
Labelhaut.get(j).setVisible(false);
Labelbas.get(j).setVisible(false);  
jPanel1.repaint(); 
}*/

       if(click==false){
           click=true;
         
           for(int k=0;k<labname.size();k++)
                     labname.get(k).setVisible(true);
           try {
             patient();
             Paintlab();
             changer_jour_haut();
             changer_jour_bas();
             if(dates.size()<8)
             paintbotton(ind,0,dates.size()); 
             else if(dates.size()>7)
             {  // for(int k=8;k<dates.size();k++)
              
             paintbotton(ind,0,7); 
             }
            
             c.setVisible(true);
             jLabel1.setText("ANTIBIO");
             
             for(int i=0;i<dates.size();i++)
                 System.out.println(dates.get(i).toString());
             jLabel21.setText("NBR JOURS");
             jPanel1.revalidate();
             jPanel1.repaint();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         }  catch (IOException ex) {
                Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
      else
      {
       
       try {
             //patient(ind);
           
            x1=197;
            y=100;  patient();
             Paintlab();SetVisibleAct(false);
             changer_jour_haut();
             changer_jour_bas();
            for(int k=0;k<labname.size();k++)
            { 
                if(k<nbline)
labname.get(k).setVisible(true);
                else
                    labname.get(k).setVisible(false);

            }
for(int l=0;l<bouton.size();l++)
    bouton.get(l).setVisible(false);
             paintbotton(ind,0,7);
            
             jLabel1.setText("ANTIBIO");
             
             for(int i=0;i<dates.size();i++)
                 System.out.println(dates.get(i).toString());
             jLabel21.setText("NBR JOURS");
             jPanel1.revalidate();
             jPanel1.repaint();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         }  catch (IOException ex) {
                Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
ac.setVisible(true);
c2.setVisible(false);
c.setVisible(false);
SetVisibleAct(true);
for(int i=0;i<labname.size();i++)
    labname.get(i).setVisible(false);
for(int j=0;j<Labelhaut.size();j++)
{
Labelhaut.get(j).setVisible(false);
Labelbas.get(j).setVisible(false); jPanel1.repaint(); 
}

         try {
              patient();
            
             changer_jour_haut();
             changer_jour_bas();
             paintActe();
             System.out.println("dates taille===="+dates.size());
             if(dates.size()<8)
             paintRect(0,dates.size()); 
             else if(dates.size()>7)
            
              
             paintRect(0,7); 
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //  jButton6.setSize(60, 20);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_jButton5ActionPerformed
public void SetVisibleAct(boolean var)
{
/*
vert.setVisible(var);
bleu.setVisible(var);
vert1.setVisible(var);
bleu1.setVisible(var);*/

}
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
   //   i=i;
      for(int j=0;j<7;j++)
       { 
        
        Labelhaut.get(j).setText(dates.get(i).toString());
        Labelbas.get(j).setText(dates.get(i).toString());
        //jPanel1.remove(Labelhaut.get(i));
   //Labelhaut.remove(i);
            Labelhaut.get(j).setVisible(true);
   Labelbas.get(j).setVisible(true);
               jPanel1.repaint();
       
       i++;
       }
             for(int k=0;k<bouton.size();k++)
        {
        //jPanel1.remove(bouton.get(k));
    
        bouton.get(k).setVisible(true);
         jPanel1.repaint();
         jPanel1.revalidate();
        }           
             for(int l=bouton.size()-1;l>0;l--)
             {
             bouton.get(l).setVisible(false);
         jPanel1.repaint();
         jPanel1.revalidate();
             
             }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //  changer_jour();
      
        for(int j=0;j<7;j++)
       { 
        if(nbrjour<dates.size())
        {   Labelhaut.get(j).setText(dates.get(nbrjour).toString());
        Labelbas.get(j).setText(dates.get(nbrjour).toString());}
        else
        {     
   Labelhaut.get(j).setVisible(false);
   Labelbas.get(j).setVisible(false);
  
   
        }
         
          nbrjour++;     
       
      //i++;
       }
         
            
              for(int j=0;j<7;j++)
       { 
        if(j<dates.size())
          bouton.get(j).setVisible(false);
        
       
            try {
               x1=197;
               y=100; 
           
                   paintbotton(ind,7,dates.size());
              if(dates.size()>14)
                { x1=197;
               y=100; 
               for(int k=7;k<15;k++)
               {  if(k<dates.size())
          bouton.get(k).setVisible(false);}
               paintbotton(ind,14,dates.size());}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       jPanel1.repaint();
       jPanel1.revalidate();
    }//GEN-LAST:event_jButton2ActionPerformed
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

      AjoutPatient p;
         try {
             p = new AjoutPatient();
              if(p.isVisible()==false)
                 p.setVisible(true);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
         }
         
           
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        // TODO add your handling code here:
         c2.setBounds(1, 1, 930, 520);c2.setVisible(true);
  jPanel1.add(c2);
 
 jPanel1.repaint();
    }//GEN-LAST:event_jPanel1MouseMoved
 
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
            java.util.logging.Logger.getLogger(Visualisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualisation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Visualisation(ind).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Visualisation.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
