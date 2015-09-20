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
public class User {
    public String nickname;
    public int lastMessage;
    
    public User(){
        nickname = "";
        lastMessage = 0;
    }
    
    public User(String _nickname){
        nickname = _nickname;
        lastMessage = 0;
    }
    
    public User(String _nickname,int _lastMessage){
        nickname = _nickname;
        lastMessage = _lastMessage;
    }
}
