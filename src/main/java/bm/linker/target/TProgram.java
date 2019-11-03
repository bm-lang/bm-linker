package bm.linker.target;

public class TProgram {

  private final TMemberList members;

  public TProgram() {
    members = new TMemberList();
  }

  public void addMember(TMember member) {
    members.add(member);
  }

}
