package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Interface implémentant le DP Factory pour traiter les données extraites de
 * TEAMS selon la sortie désirée. Pour ajouter des nouvelles sorties, il suffira
 * de créer des classes implémentant cette interface.
 *
 * @author Jade
 * 
 */
public interface TEAMSProcessor{


    public Collection<People> get_allpeople();

    public String toCode();

	public void writeFile();
}
