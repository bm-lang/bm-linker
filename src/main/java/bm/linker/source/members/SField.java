package bm.linker.source.members;

import bm.linker.LinkContext;
import bm.linker.source.SExpression;
import bm.linker.source.SMember;
import bm.linker.source.SMemberRef;
import bm.linker.target.TType;
import bm.linker.target.members.TField;

public class SField implements SMember {

  private TField linkedField;

  public final String name;
  public boolean isVariable;
  public SExpression initialValue;
  public SMemberRef dataType;

  public SField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public TField link(LinkContext context) {
    if (linkedField != null) {
      return linkedField;
    }

    linkedField = new TField(context.originFor(this));

    if (this.initialValue != null) {
      linkedField.setInitialValue(this.initialValue.link(context));
    }

    if (this.dataType != null) {
      linkedField.setDataType(this.dataType.link(context).cast(TType.class));
    }

    return linkedField;
  }

}

