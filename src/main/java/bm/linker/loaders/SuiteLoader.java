package bm.linker.loaders;

import bm.linker.source.*;
import bm.parser.target.*;

public class SuiteLoader {

  public static SModule loadSuite(PSuite pSuite) {
    SModule sModule = new SModule(pSuite.getName());

    for (PSourceFile sSourceFile : pSuite.getSourceFiles()) {
        SScope scope = loadSourceFile(sSourceFile);

        sModule.add(scope);
    }

    return null;
  }

  public static SScope loadSourceFile(PSourceFile pSourceFile) {
    SScope sScope = new SScope(pSourceFile.fileName);

    for (PMember pMember : pSourceFile.members) {
      SMember sMember = MemberLoader.load(pMember);

      sScope.add(sMember);
    }

    return sScope;
  }

}
