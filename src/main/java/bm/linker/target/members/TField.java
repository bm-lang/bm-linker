package bm.linker.target.members;

import bm.linker.target.TExpression;
import bm.linker.target.TMember;
import bm.linker.target.TOrigin;
import bm.linker.target.TType;

public class TField extends TMember {

  private TType dataType;
  private TExpression initialValue;

  public TField(TOrigin origin) {
    super(origin);
  }

  public void setDataType(TType dataType) {
    this.dataType = dataType;
  }

  public TType getDataType() {
    return dataType;
  }

  public void setInitialValue(TExpression initialValue) {
    this.initialValue = initialValue;
  }

  public TExpression getInitialValue() {
    return initialValue;
  }

}
