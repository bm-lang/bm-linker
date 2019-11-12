package bm.linker;

import bm.linker.source.SScope;
import bm.linker.source.SMemberRef;
import bm.linker.source.SModule;
import bm.linker.source.expressions.SNull;
import bm.linker.source.members.SClass;
import bm.linker.source.members.SField;
import bm.linker.target.expressions.TNull;
import bm.linker.target.members.TField;
import bm.linker.target.types.TClass;
import org.junit.Test;

public class Case1 {

  @Test
  public void case1() {
    SModule sRootModule = new SModule("example");

    SScope sScope1 = new SScope("scope1.bm");
    sRootModule.add(sScope1);

    SScope sScope2 = new SScope("scope2.bm");
    sRootModule.add(sScope2);

    SField sFieldX = new SField("X");
    sScope1.add(sFieldX);

    SClass sClassT = new SClass("T");
    sScope2.add(sClassT);

    sFieldX.initialValue = new SNull();
    sFieldX.dataType = new SMemberRef("T");

    LinkContext context = new LinkContext();

    sRootModule.link(context);

    TField tFieldX = (TField)context.getMembers().get(0);
    TClass tClassT = (TClass)context.getMembers().get(1);

    assert tFieldX.getOrigin().getMember() == sFieldX;
    assert tFieldX.getOrigin().getTraceback()[0] == sRootModule;
    assert tFieldX.getInitialValue() instanceof TNull;
    assert tFieldX.getDataType() == tClassT;

    assert tClassT.getOrigin().getMember() == sClassT;
    assert tClassT.getOrigin().getTraceback()[0] == sRootModule;
  }

}
