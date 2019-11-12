package bm.linker;

import bm.linker.source.SContainer;
import bm.linker.source.SMember;
import bm.linker.source.members.SClass;
import bm.linker.target.TMember;
import bm.linker.target.TMemberList;
import bm.linker.target.TOrigin;
import bm.linker.target.TProgram;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class LinkContext {

  private final Stack<SContainer> containers;
  private final TMemberList members;

  public LinkContext() {
    containers = new Stack<>();
    members = new TMemberList();
  }

  public TProgram toProgram() {
    return new TProgram(members);
  }

  public List<TMember> getMembers() {
    return members;
  }

  public void add(TMember member) {
    members.add(member);
  }

  public SMember resolveMember(String name) {
    SMember result = null;

    for (int i = containers.size() - 1; i >= 0; i--) {
      SMember member = containers.get(i).search(name);

      if (member != null) {
        if (result != null) {
          throw new LinkerException();
        }

        result = member;
      }
    }

    if (result == null) {
      throw new LinkerException();
    }

    return result;
  }

  public SMember resolveMember(String[] path) {
    if (path.length == 0) {
      throw new LinkerException();
    }

    SMember member = resolveMember(path[0]);

    for (int i = 1; i < path.length; i++) {
      if (member instanceof SContainer) {
        SContainer container = (SContainer)member;

        member = container.search(path[i]);

        if (member == null) {
          throw new LinkerException();
        }
      }
    }

    return member;
  }

  public void usingContainer(SContainer container, Runnable action) {
    containers.push(container);

    action.run();

    containers.pop();
  }

  public TOrigin originFor(SMember member) {
    return new TOrigin(containers.toArray(new SContainer[0]), member);
  }
}
