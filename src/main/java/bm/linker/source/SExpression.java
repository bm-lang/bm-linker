package bm.linker.source;

import bm.linker.LinkContext;
import bm.linker.target.TExpression;

public interface SExpression {

  TExpression link(LinkContext context);

}
