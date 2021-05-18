module project_DesignPattern {
	requires transitive javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	
	opens appli to javafx.graphics,javafx.fxml;
	
	exports appli to javafx.graphics,javafx.fxml;
	
}