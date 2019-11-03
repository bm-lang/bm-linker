package bm.linker.source.members;

import bm.linker.LinkContext;
import bm.linker.source.SContainer;
import bm.linker.source.SMember;
import bm.linker.source.SMemberList;
import bm.linker.target.types.TClass;

public class SClass implements SMember, SContainer {

  private TClass linkedClass;

  public final SMemberList members = new SMemberList();
  public String name;

  public SClass(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public SMember search(String name) {
    return null;
  }

  @Override
  public TClass link(LinkContext context) {
    if (this.linkedClass != null) {
      return this.linkedClass;
    }

    linkedClass = new TClass(context.originFor(this));

    context.usingContainer(this, () -> {
      for (SMember member : members) {
        member.link(context);
      }
    });

    return linkedClass;
  }
}
