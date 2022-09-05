package ch.ijass.server;

import ch.ijass.engine.GameManager;
import ch.ijass.engine.State;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = Utils.expectedClientUrl)
public class Mappings {

    private final static int nullValue = -1;
    private ConcurrentHashMap<Integer, GameManager> mapping;

    public Mappings() {
        mapping = new ConcurrentHashMap<>();
    }

    public void newGame() {
        GameManager gm = new GameManager();
        mapping.put(gm.getGameId(), gm);
    }

    public void next(@RequestParam Integer gameId, @RequestParam(required = false)Integer playerId,
                     @RequestParam(required = false)Integer cardPlayed, @RequestParam(required = false)Integer trump) {
        GameManager concerned = mapping.get(gameId);
        if (trump != null) {
            concerned.setTrump(trump);
            return;
        }
        else if (playerId != null && cardPlayed != null) {
            State newState = concerned.compute(playerId, cardPlayed);
        }
    }
}
