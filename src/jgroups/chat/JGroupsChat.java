
package jgroups.chat;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;
/**
 *
 * @author Gonzalez
 */
public class JGroupsChat implements Receiver{
    private static final String nombreGrupo = "Sistema-Distribuido-Chat";
    
    String user_name=System.getProperty("user.name");
    
    private JChannel channel = null;
    
    final List<String> state=new LinkedList<String>();
      
    public Interfaz Ventana;
    
    public JGroupsChat(){
        Ventana = new Interfaz();
    }
    
 
    public void start() throws Exception{
        try {
            channel = new JChannel();
            channel.connect(nombreGrupo);
            channel.setDiscardOwnMessages(true);
            channel.setReceiver(this);
            channel.getState(null, 10000);
            
        }catch (Exception e){
            System.out.println("¡Error al iniciar el chat!");
        }
    }
 
    public void sendMessage(Address dst, String text){
  
        try {
            Message msg = new Message(dst,text.getBytes());
            channel.send(msg);
        }catch (Exception e) {
            System.out.println("¡El chat no pudo enviar el mensaje!");
        }            
    }
    
 
    @Override
    public void receive(Message msg) {
        String line = msg.getSrc() + ":" + new String(msg.getBuffer());
        
       if(line.contains("MSG For Pizarra")){
            String target=line.copyValueOf("MSG For Pizarra".toCharArray());
            line=line.replace(target, "");
                Ventana.msgPizarra(line);
                Ventana.msgBorradoPizarra("");
        }
        else{
                Ventana.msgGrupo(line);   
        }
        
       synchronized(state) {
        state.add(line);
        }

    }
    
    @Override
    public void viewAccepted(View view) {
        //System.out.println("vista！" + view.toString());
         Ventana.ventana.setTitle("Usuario: " + channel.getAddress());
       
    }   
    
    public Optional<Address> getAddress(String name) { 
        View view = channel.view(); 
        return view.getMembers().stream()
        .filter(address -> name.equals(address.toString()))
        .findAny(); 
    }
    
    public byte[] getState() {
        synchronized(state) {
            try {
                return Util.objectToByteBuffer(state);
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    
    public void setState(byte[] new_state) {
        try {
            List<String> list=(List<String>)Util.objectFromByteBuffer(new_state);
            synchronized(state) {
                state.clear();
                state.addAll(list);
            }
            Ventana.msgGrupo("estado recibido(" + list.size() + " mensajes en el historial de chat):");
            for(String str: list) {
                Ventana.msgGrupo(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
