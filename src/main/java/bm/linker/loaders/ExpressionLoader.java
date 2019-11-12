package bm.linker.loaders;

import bm.linker.LinkerException;
import bm.linker.source.SExpression;
import bm.linker.source.expressions.SNull;
import bm.parser.target.expressions.PExpression;
import bm.parser.target.expressions.PExpressionRaw;
import bm.parser.target.expressions.PNullLiteral;

public class ExpressionLoader {

  public static SExpression load(PExpression pExpression) {
    if (pExpression == null) {
      return null;
    }
    else if (pExpression instanceof PNullLiteral) {
      return new SNull();
    }
    else if (pExpression instanceof PExpressionRaw) {
      return loadRaw((PExpressionRaw)pExpression);
    }

    throw new LinkerException("Not implemented expression dataType: " + pExpression.getClass());
  }

  private static SExpression loadRaw(PExpressionRaw expr) {
    if (expr.getItems().size() == 1) {
      return load(expr.getItems().get(0));
    }

    throw new LinkerException("Not implemented multiple raw");
  }

}
