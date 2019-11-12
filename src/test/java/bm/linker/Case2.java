package bm.linker;

import bm.linker.loaders.SuiteLoader;
import bm.linker.source.SScope;
import bm.linker.target.TMember;
import bm.linker.target.TProgram;
import bm.linker.target.expressions.TNull;
import bm.linker.target.members.TField;
import bm.linker.target.types.TClass;
import bm.parser.Parser;
import bm.parser.target.PSourceFile;
import bm.parser.target.PSuite;
import bm.utils.ResourceUtils;
import org.junit.Test;

public class Case2 {

  @Test
  public void test() {
    String bm = ResourceUtils.getResourceAsString("case2.bm");
    Parser parser = new Parser();
    PSourceFile sourceFile = parser.parseSourceFile(bm, "case2.bm", new String[]{""});
    SScope scope = SuiteLoader.loadSourceFile(sourceFile);

    LinkContext context = new LinkContext();

    scope.link(context);

    TProgram program = context.toProgram();
    TField mP = program.findMember("p").cast(TField.class);
    TClass mPoint = program.findMember("Point").cast(TClass.class);

    assert mP.getInitialValue() instanceof TNull;
    assert mP.getDataType() == mPoint;
  }

}
