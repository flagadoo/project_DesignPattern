package appli;

/**
 * Interface implémentant le DP Strategy pour la structure et la sortie des fichiers,
 * selon l'extension désirée (HTML, txt, xls, etc). De nouvelles sorties pourront
 * être implémentées par la suite.
 * 
 * @author geo02
 *
 */
public interface FileWriterStrategy {
	
	public void writeFile();

}
