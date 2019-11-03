package bm.linker.source;

import bm.linker.LinkContext;
import bm.linker.LinkerException;
import bm.linker.target.TMember;

import java.util.Objects;

public class SModule implements SMember, SContainer {

  private final SScopeList scopes = new SScopeList();

  private final String name;

  public SModule(String name) {
    this.name = Objects.requireNonNull(name);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public SMember search(String name) {
    SMember result = null;

    for (SScope scope : scopes) {
      SMember member = scope.search(name);

      if (member != null) {
        if (result != null) {
          throw new LinkerException();
        }

        result = member;
      }
    }

    return result;
  }

  public TMember link(LinkContext context) {
    context.usingContainer(this, () -> {
      for (SScope scope : scopes) {
        scope.link(context);
      }
    });

    return null;
  }

  public void add(SScope scope) {
    this.scopes.add(scope);
  }
}
