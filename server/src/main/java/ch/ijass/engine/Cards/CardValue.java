package ch.ijass.engine.Cards;

public enum CardValue {
  SIX,
  SEVEN,
  EIGHT,
  NINE {
    @Override
    public int ordinalWithTrump() {
      return 9;
    }
  },
  TEN {
    @Override
    public int points() {
      return 10;
    }
  },
  JACK {
    @Override
    public int points() {
      return 2;
    }

    @Override
    public int ordinalWithTrump() {
      return 10;
    }
  },
  QUEEN {
    @Override
    public int points() {
      return 3;
    }
  },
  KING {
    @Override
    public int points() {
      return 4;
    }
  },
  ACE {
    @Override
    public int points() {
      return 11;
    }
  };

  public int points() {
    return 0;
  }

  public int ordinalWithTrump() {
    return ordinal();
  }
};