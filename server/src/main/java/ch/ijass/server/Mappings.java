package ch.ijass.server;

import ch.ijass.engine.GameManager;
import ch.ijass.engine.State;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping("/newgame")
    public String newGame() {
        GameManager gm = new GameManager();
        int id = gm.getGameId();

        mapping.put(id, gm);
        return String.valueOf(id);
    }

    @GetMapping("/next")
    public String next(@RequestParam Integer gameId, @RequestParam(required = false)Integer playerId,
                     @RequestParam(required = false)Integer cardPlayed, @RequestParam(required = false)Integer trump) {
        GameManager concerned = mapping.get(gameId);
        if (trump != null) {
            concerned.setTrump(trump);
            return "Changed trump";
        }
        else if (playerId != null && cardPlayed != null) {

            State newState = concerned.compute(playerId, cardPlayed);
            try {
                return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(newState);
            } catch(Exception e) {
                return e.getMessage();
            }
        }
        return "No changes";
    }
}
