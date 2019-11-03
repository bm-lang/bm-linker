package bm.linker.source;

import bm.linker.LinkContext;
import bm.linker.target.TMember;

public class SMemberRef {

  public String[] path;

  public SMemberRef(String... path) {
    this.path = path;
  }

  public TMember link(LinkContext context) {
    SMember smember = context.resolveMember(path);

    return smember.link(context);
  }

}
