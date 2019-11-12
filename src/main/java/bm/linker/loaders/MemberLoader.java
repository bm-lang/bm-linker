package bm.linker.loaders;

import bm.linker.LinkerException;
import bm.linker.source.SMember;
import bm.linker.source.SMemberRef;
import bm.linker.source.SModule;
import bm.linker.source.SScope;
import bm.linker.source.members.SClass;
import bm.linker.source.members.SField;
import bm.parser.target.*;

public class MemberLoader {

  public static SMember load(PMember pMember) {
    if (pMember == null) {
      return null;
    }
    else if (pMember instanceof PField) {
      return loadField((PField)pMember);
    }
    else if (pMember instanceof PClass) {
      return loadClass((PClass)pMember);
    }
    else if (pMember instanceof PModule) {
      return loadModule((PModule)pMember);
    }

    throw new LinkerException("Not implemented member type: " + pMember.getClass());
  }

  public static SField loadField(PField pField) {
    SField sField = new SField(pField.name.text);

    // TODO member access

    sField.dataType = loadTypeRef(pField.dataType);
    sField.initialValue = ExpressionLoader.load(pField.initialValue);

    if (pField.fieldMode == null || pField.fieldMode == PFieldMode.CONST) {
      sField.isVariable = false;
    }
    else if (pField.fieldMode == PFieldMode.VAR) {
      sField.isVariable = true;
    }
    else {
      throw new LinkerException("Unknown field mode: " + pField.fieldMode);
    }

    return sField;
  }

  public static SClass loadClass(PClass pClass) {
    SClass sClass = new SClass(pClass.name.text);

    return sClass;
  }

  public static SModule loadModule(PModule pModule) {
    SModule sModule = new SModule(pModule.name.text);
    SScope scope = new SScope(null);

    sModule.add(scope);

    for (PMember pMember : pModule.members) {
      SMember sMember = load(pMember);

      scope.add(sMember);
    }

    return sModule;
  }

  public static SMemberRef loadTypeRef(PTypeRef pTypeRef) {
    String[] path = pTypeRef.names.stream()
        .map(name -> name.text)
        .toArray(String[]::new);
    return new SMemberRef(path);
  }

}
