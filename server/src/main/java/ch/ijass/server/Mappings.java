package ch.ijass.server;

import ch.ijass.engine.GameManager;
import ch.ijass.engine.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = Utils.expectedClientUrl)
public class Mappings {

  private static final int nullValue = -1;
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

  @GetMapping("/startRound")
  public String startRound(@RequestParam Integer gameId, @RequestParam Integer playerId) {
    GameManager concerned = mapping.get(gameId);
    concerned.initiateRound(playerId);
    try {
      return new ObjectMapper()
          .writerWithDefaultPrettyPrinter()
          .writeValueAsString(concerned.getState());
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @GetMapping("/chooseTrump")
  public String chooseTrump(@RequestParam Integer gameId, @RequestParam Integer trump) {
    GameManager concerned = mapping.get(gameId);
    concerned.setTrump(trump);
    try {
      return new ObjectMapper()
          .writerWithDefaultPrettyPrinter()
          .writeValueAsString(concerned.getState());
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @GetMapping("/firstPartFold")
  public String firstPartFold(
      @RequestParam Integer gameId, @RequestParam(required = false) Integer playerId) {
    GameManager concerned = mapping.get(gameId);
    if (playerId != null) {

      State newState = concerned.startFold(playerId);
      try {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(newState);
      } catch (Exception e) {
        return e.getMessage();
      }
    }
    return "No changes";
  }

  @GetMapping("/secondPartFold")
  public String secondPartFold(
      @RequestParam Integer gameId,
      @RequestParam Integer playerId,
      @RequestParam(required = false) Integer cardPlayed,
      @RequestParam(required = false) Integer trump) {
    GameManager concerned = mapping.get(gameId);
    try {
      return new ObjectMapper()
          .writerWithDefaultPrettyPrinter()
          .writeValueAsString(concerned.endFold(playerId, cardPlayed));
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
