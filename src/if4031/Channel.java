/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package if4031;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Riady
 */
public class Channel {
    private String name;
    private List<User> listUser;
    private List<Message> listMessage;
    
    public Channel(){
        name = "";
        listUser = new ArrayList();
        listMessage = new ArrayList();
    }
    
    public Channel(String _name){
        name = _name;
        listUser = new ArrayList();
        listMessage = new ArrayList();
    }
    
    public String getChannelName(){
        return name;
    } 
    
    public boolean isContainNick(String nick){
        boolean found = false;
        
        for(int i=0 ; i<listUser.size() && !found; i++){
            if(listUser.get(i).nickname.equals(nick)){
                found = true;
            }
        }
        return found;
    }
    
    public boolean removeUser(String nick){
        boolean found = false;
        
        for(int i=0 ; i<listUser.size() && !found; i++){
            if(listUser.get(i).nickname.equals(nick)){
                listUser.remove(i);
                found = true;
            }
        }
        return found;
    }
    
    public void addMessage(Message message){
        listMessage.add(message);
    }
    
    public String getMessage(){
        return listMessage.get(0).message;
    }
    
    public List getallMessage(){
        return listMessage;
    }
    
    public List getMessageFrom(String nickname){
        List<Message> ret = new ArrayList();
        int lastMessage = 0;
        
        boolean found = false;
        
        for(int i=0 ; i<listUser.size() && !found; i++){
            if(listUser.get(i).nickname.equals(nickname)){
                found = true;
                lastMessage = listUser.get(i).lastMessage;
                //System.out.println(lastMessage);
                
                User temp = new User(listUser.get(i).nickname,listMessage.size());
                listUser.set(i, temp);
            }
        }
        
        for(int i=lastMessage; i < listMessage.size();i++){
            ret.add(listMessage.get(i));
        }
        return ret;
    }
    
    public void addUser(String nickname){
        User user = new User(nickname);
        listUser.add(user);
    }
    
    
}
