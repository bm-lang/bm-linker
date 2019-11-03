package bm.linker.target;

import bm.linker.source.SContainer;
import bm.linker.source.SMember;

public class TOrigin {

  private final SContainer[] path;
  private final SMember member;

  public TOrigin(SContainer[] path, SMember member) {
    this.path = path;
    this.member = member;
  }

  public SMember getMember() {
    return member;
  }

  public SContainer[] getPath() {
    return path;
  }
}
