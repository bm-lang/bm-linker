package bm.linker.source;

import bm.linker.LinkContext;
import bm.linker.target.TMember;

public interface SMember {

  String getName();

  TMember link(LinkContext context);

}
