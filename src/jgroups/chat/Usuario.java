
package jgroups.chat;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgroups.Address;
import org.jgroups.Message;

/**
 *
 * @author Gonzalez
 */
public class Usuario{
 
   public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        JGroupsChat chat01 = new JGroupsChat();
        chat01.start();
        enviarMensaje(chat01);
        enviarMensajeDirecto(chat01);
        mensajePizarra(chat01);
    }
    
    private static void enviarMensaje(JGroupsChat chat01){
        chat01.Ventana.btn_send_to_all.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String enviar =chat01.Ventana.txt_mensaje.getText();
                chat01.sendMessage(null, enviar);
                chat01.Ventana.txt_mensaje.setText(" ");
                    
            }
        
        });
    }
    
    private static void enviarMensajeDirecto(JGroupsChat chat1){
        chat1.Ventana.btn3_msgDirecto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){           
                try {
                    Address destino = null;
                    String destinationName = chat1.Ventana.txt_address.getText();
                    destino = chat1.getAddress(destinationName)
                    .orElseThrow(() -> new Exception("\"Destino no encontrado\""));

                    String enviar =chat1.Ventana.area2_mensaje.getText();
                    chat1.sendMessage(destino, enviar);
                    chat1.Ventana.area2_mensaje.setText("");
                    chat1.Ventana.txt_address.setText(" ");

                } catch (IOException ioe) {
                 
                }       
                catch (Exception ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
    }
    
    private static void mensajePizarra(JGroupsChat chat1){
        chat1.Ventana.btn2_enviar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                String enviar ="MSG For Pizarra"+chat1.Ventana.area_pizarra.getText();
                chat1.sendMessage(null,enviar);
                //para que el usuario tambien vea su mensaje
                String target=enviar.copyValueOf("MSG For Pizarra".toCharArray());
                enviar=enviar.replace(target, "You Pinned This Message:");
                chat1.Ventana.msgPizarra(enviar);
                chat1.Ventana.msgBorradoPizarra(enviar);
                //se limpia el area del mensaje
                chat1.Ventana.area_pizarra.setText(" ");
            }     
        });
    }
}
