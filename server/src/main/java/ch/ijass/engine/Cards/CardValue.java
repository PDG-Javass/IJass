package ch.ijass.engine.Cards;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CardValue {
  SIX,
  SEVEN,
  EIGHT,
  NINE {
    @Override
    public int ordinalWithTrump() {
      return 7;
    }
  },
  TEN {
    @Override
    public int points() {
      return 10;
    }

    public int ordinalWithTrump() {
      return 3;
    }
  },
  JACK {
    @Override
    public int points() {
      return 2;
    }

    @Override
    public int ordinalWithTrump() {
      return 8;
    }
  },
  QUEEN {
    @Override
    public int points() {
      return 3;
    }

    public int ordinalWithTrump() {
      return 4;
    }
  },
  KING {
    @Override
    public int points() {
      return 4;
    }

    public int ordinalWithTrump() {
      return 5;
    }
  },
  ACE {
    @Override
    public int points() {
      return 11;
    }

    public int ordinalWithTrump() {
      return 6;
    }
  };

  public static List<CardValue> valuesWithTrump() {
    Stream stream =
        Stream.of(
            CardValue.SIX,
            CardValue.SEVEN,
            CardValue.EIGHT,
            CardValue.TEN,
            CardValue.QUEEN,
            CardValue.KING,
            CardValue.ACE,
            CardValue.NINE,
            CardValue.JACK);
    return (List<CardValue>) stream.collect(Collectors.toList());
  }

  public int points() {
    return 0;
  }

  public int ordinalWithTrump() {
    return ordinal();
  }
  @JsonValue
  public int value() { return ordinal(); }
}
