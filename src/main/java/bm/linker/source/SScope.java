package bm.linker.source;

import bm.linker.LinkContext;
import bm.linker.LinkerException;
import bm.linker.target.TMember;

import java.util.Objects;

public class SScope implements SContainer {

  public final String label;

  public final SMemberList members = new SMemberList();

  public SScope(String label) {
    this.label = label;
  }

  @Override
  public SMember search(String name) {
    SMember result = null;

    for (SMember member : members) {
      if (Objects.equals(member.getName(), name)) {
        if (result != null) {
          throw new LinkerException();
        }
        result = member;
      }
    }

    return result;
  }

  public void link(LinkContext context) {
    context.usingContainer(this, () -> {
      for (SMember smember : members) {
        TMember tmember = smember.link(context);

        if (tmember != null) {
          context.add(tmember);
        }
      }
    });
  }

  public void add(SMember member) {
    this.members.add(member);
  }
}
