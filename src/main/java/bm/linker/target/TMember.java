package bm.linker.target;

import bm.linker.LinkerException;
import bm.linker.target.members.TField;

public class TMember {

  private final TOrigin origin;

  public TMember(TOrigin origin) {
    this.origin = origin;
  }

  public TOrigin getOrigin() {
    return origin;
  }

  public <T> T cast(Class<T> type) {
    if (!type.isInstance(this)) {
      throw new LinkerException();
    }

    return type.cast(this);
  }

}
