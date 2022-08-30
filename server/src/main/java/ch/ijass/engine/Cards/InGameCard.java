package ch.ijass.engine.Cards;

import ch.ijass.engine.Players.Player;

public class InGameCard extends Deck{

    public int countPoints(CardColor trump){
        isValid();
        int result = 0;
        for(Card card: content){
            result += card.points(trump);
        }
        return result;
    }

    /*
    public Player getWinner(CardColor colorAsked, CardColor trump){
        isValid();

         s'il y a de l'atout joué
                alors on cherche la plus haute carte d'atout
           sinon
                on itère sur
           la plus haute carte parmis la couleur demandé l'emporte

    }

     */

    private void isValid(){
        if( content.size() != 4){
            throw new RuntimeException(); // todo pas sur que ce soit la bonne exception
        }
    }

    public int size(){
        return content.size();
    }

    public CardColor colorAsked(){
        return content.firstElement().getColor();
    }

    public boolean isCut(CardColor trump){
        for(Card card: content){
            if(card.getColor() == trump){
                return true;
            }
        }
        return false;
    }

}