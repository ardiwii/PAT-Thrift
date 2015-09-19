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
public class ListChannel {
    private List<Channel> listChannel;
    
    public ListChannel(){
        listChannel = new ArrayList();
    }
    
     public void addMessage(String nickname ,String messageText){
         
         Message message = new Message(nickname,messageText);
         for(int i=0 ; i<listChannel.size(); i++){
            if(listChannel.get(i).isContainNick(nickname)){
                listChannel.get(i).addMessage(message);
            }
        }
     }
    
    
    public boolean addMessageToChannel(String nickname ,String messageText ,String channelName){
        
        Message message = new Message(nickname,messageText);
        
        boolean found = false;
        
        for(int i=0 ; i<listChannel.size() && !found; i++){
            if(listChannel.get(i).getChannelName().equals(channelName) && listChannel.get(i).isContainNick(nickname)){
                listChannel.get(i).addMessage(message);
                found = true;
            }
        }
        return found;
    }
    
    public String getMessage(String nickname){
        List <String> ret = new ArrayList();
        for(int i = 0 ; i<listChannel.size();i++){
            if(listChannel.get(i).isContainNick(nickname)){
                List <Message> message = listChannel.get(i).getallMessage();
                for(int j=0 ;j<message.size();j++){
                    String temp = "["+listChannel.get(i).getChannelName()+"]("+message.get(j).nick+")"+message.get(j).message;
                    ret.add(temp);
                }
            }
        }
        String retString = "";
        for(int i = 0 ; i < ret.size() ; i++){
            retString += ret.get(i) + "\n";
        }
        return retString;
    }
    
    public boolean joinChannel(String nickname, String channelName){
        if(!isChannelExist(channelName)){
            addChannel(channelName);
        }
        
        boolean found = false;
        
        for(int i=0 ; i<listChannel.size() && !found; i++){
            if(listChannel.get(i).getChannelName().equals(channelName) && !listChannel.get(i).isContainNick(nickname)){
                listChannel.get(i).addUser(nickname);
                found = true;
                
            }
        }
        return found;
        
    }
    
    
    private void addChannel(String channelName){
        Channel channel = new Channel(channelName);
        listChannel.add(channel);
    }
    
    private boolean isChannelExist(String channelName){
        boolean found = false;
        for(int i=0 ; i<listChannel.size() && !found; i++){
            if(listChannel.get(i).getChannelName() == channelName){
                found = true;
            }
        }
        return found;
    }
}
