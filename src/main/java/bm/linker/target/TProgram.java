package bm.linker.target;

public class TProgram {

  private final TMemberList members;

  public TProgram(TMemberList members) {
    this.members = members;
  }

  public TMember findMember(String... path) {
    TMember member = getMember(path);

    if (member == null) {
      throw new RuntimeException("not found: " + String.join(".", path));
    }

    return member;
  }

  public TMember getMember(String... path) {
    for (TMember member : members) {
      if (member.getOrigin().matches(path)) {
        return member; // TODO consider ambiguities?
      }
    }

    return null;
  }

}
