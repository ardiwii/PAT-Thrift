/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package if4031;

/**
 *
 * @author Riady
 */
public class Message {
    public String nick;
    public String message;
    
    public Message(){
        nick = "";
        message = "";
    }
    
    public Message(String _nick,String _message){
        nick = _nick;
        message = _message;
    }
}
