/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.game;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Christophe
 */
public class PlayerRound extends AbstractAction{
    Game game;
    Player player;
    
    public PlayerRound(Game game){
    this.game = game;    
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // une classe par action....du joueur....
        // dans joueur... set chose pour envoyer instance
        //appler game pis jouer prochain joueur....
        String command = e.getActionCommand();
        if (command.equals("?")){
            
        }else if (command.equals("?")){
        
        }else if (command.equals("?")){
        
        }
        
        game.nextPlayerRound();
    }
    
}
