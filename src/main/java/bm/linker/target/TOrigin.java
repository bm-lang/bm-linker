package bm.linker.target;

import bm.linker.source.SContainer;
import bm.linker.source.SMember;
import bm.linker.source.SModule;

import java.util.ArrayList;
import java.util.Arrays;

public class TOrigin {

  private final SContainer[] traceback;
  private final SMember member;

  public TOrigin(SContainer[] traceback, SMember member) {
    this.traceback = traceback;
    this.member = member;
  }

  public String[] toPath() {
    ArrayList<String> path = new ArrayList<>();

    for (SContainer container : traceback) {
      if (container instanceof SMember) {
        SMember containerMember = (SMember)container;

        path.add(containerMember.getName());
      }
    }

    path.add(member.getName());

    return path.toArray(new String[0]);
  }

  public boolean matches(String[] memberPath) {
    String[] thisPath = toPath();
    return Arrays.equals(memberPath, thisPath);
  }

  public SMember getMember() {
    return member;
  }

  public SContainer[] getTraceback() {
    return traceback;
  }
}
