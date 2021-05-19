package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface TEAMSProcessor{


    public Collection<People> get_allpeople();

    public String toHTMLCode();

	public void writeFile();
}
