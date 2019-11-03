package bm.linker.target;

import bm.linker.LinkerException;

public class TMember {

  private final TOrigin origin;

  public TMember(TOrigin origin) {
    this.origin = origin;
  }

  public TOrigin getOrigin() {
    return origin;
  }

  public TType toType() {
    if (!(this instanceof TType)) {
      throw new LinkerException();
    }

    return (TType)this;
  }

}
