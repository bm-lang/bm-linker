package bm.linker.source.expressions;

import bm.linker.LinkContext;
import bm.linker.source.SExpression;
import bm.linker.target.TExpression;
import bm.linker.target.expressions.TNull;

public class SNull implements SExpression {

  @Override
  public TExpression link(LinkContext context) {
    return new TNull();
  }
}
