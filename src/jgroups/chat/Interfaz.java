
package jgroups.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JComponent;
import org.jgroups.Message;
import org.jgroups.util.Util;

/**
 *
 * @author Gonzalez
 */
public class Interfaz{
    JFrame ventana=null;
    JButton btn_send_to_all,btn2_enviar,btn3_msgDirecto =null;
    JTextField txt_mensaje,txt_address =null;
    JTextArea area_mensaje,area2_mensaje,area_pizarra,area_pizarra2=null;
    JPanel contenedor_areaChat,contenedor_areaChat2,contenedor_pizarra,cont,contenedor_pizarra2 =null;
    JPanel contedor_btntxt,contedor_btntxt2=null;
    JPanel contedor_btn=null;
    JScrollPane scroll,scroll2,scroll3,scrol4=null;
    JLabel l1,l2,l3,l4,l5=null;


    
    public Interfaz(){
        Init();
    }
    
    public void Init(){
        ventana =new JFrame();
        area_mensaje =new JTextArea(10,12);
        scroll =new JScrollPane(area_mensaje);
        area2_mensaje =new JTextArea(10,12);
        scroll2 =new JScrollPane(area2_mensaje);
        area_pizarra =new JTextArea(10,10);
        scroll3 =new JScrollPane(area_pizarra);
        area_pizarra2 =new JTextArea(10,12);
        scrol4 =new JScrollPane(area_pizarra2);
        
  
        btn3_msgDirecto = new JButton("Enviar MSGD");
        btn3_msgDirecto.setBounds(201,170,116,28);
        ventana.add(btn3_msgDirecto);
        
        btn2_enviar =new JButton("Enviar a pizzara");
        btn2_enviar.setBounds(333,361,205,26);
        ventana.add(btn2_enviar);
        
        btn_send_to_all =new JButton("Enviar a todos");
        btn_send_to_all.setBounds(202,360,116,28);
        ventana.add(btn_send_to_all);
        
        txt_mensaje = new JTextField(4);
        txt_mensaje.setBounds(15,361,180,26);
        ventana.add(txt_mensaje);
        
        contenedor_areaChat=new JPanel();
        area_mensaje.setEditable(false);
        contenedor_areaChat.setLayout(new GridLayout(1,1));
        contenedor_areaChat.add(scroll); 
        contenedor_areaChat.setBounds(17,249,297,104);
        ventana.add(contenedor_areaChat);
        
        l1=new JLabel("Address: ");
        l1.setBounds(16,170,58,28);
        ventana.add(l1);
        
        l2 =new JLabel("Mensaje Directo");
        l2.setBounds(110,23,116,24);
        ventana.add(l2);
        
        l3 =new JLabel("Mensaje a todo el grupo");
        l3.setBounds(78,215,177,26);
        ventana.add(l3);
        
        l4 =new JLabel("Mensaje pizarra");
        l4.setBounds(348,215,177,26);
        ventana.add(l4);
        
        l5 =new JLabel("Pizarra");
        l5.setBounds(379,25,116,26);
        ventana.add(l5);
        
        txt_address = new JTextField(4);
        txt_address.setBounds(78,169,116,28);
        ventana.add(txt_address);
        
        contenedor_areaChat2=new JPanel();
        contenedor_areaChat2.setLayout(new GridLayout(1,1));
        contenedor_areaChat2.add(scroll2); 
        contenedor_areaChat2.setBounds(17,59,297,104);
        ventana.add(contenedor_areaChat2);
        
        contenedor_pizarra=new JPanel();
        contenedor_pizarra.setLayout(new GridLayout(1,1));
        contenedor_pizarra.add(scroll3); 
        contenedor_pizarra.setBounds(336,248,203,104);
        ventana.add(contenedor_pizarra);
        
        contenedor_pizarra2 =new JPanel();
        contenedor_pizarra2.setBackground(Color.WHITE);
        area_pizarra2.setEditable(false);
        contenedor_pizarra2.setLayout(new GridLayout(1,1));
        contenedor_pizarra2.add(scrol4);       
        contenedor_pizarra2.setBounds(335,57,203,137);
        ventana.add(contenedor_pizarra2);
        
        cont=new JPanel();
        cont.setPreferredSize(new Dimension(50,50));
        cont.setBackground(Color.ORANGE);
        ventana.add(cont);
        
        ventana.setSize(556,420);
       // ventana.setBackground(Color.yellow);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  
    }
    public void msgGrupo(String Message){
        area_mensaje.append("\n" + Message);
     
    }
   
     public void msgPizarra(String Message){
        area_pizarra2.append("\n" +Message);
    }
     
       public void msgBorradoPizarra(String Message){
            Timer t= new Timer();
            TimerTask tarea= new TimerTask() {
               @Override
               public void run() {
                    area_pizarra2.setText(" ");
               }
           };
            t.schedule(tarea, 10000, 60000);
       
    }
   
}

     
   