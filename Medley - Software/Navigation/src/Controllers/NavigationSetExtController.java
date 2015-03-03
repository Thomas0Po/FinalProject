package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Abstractions.Models.LanguageModel;
import Views.TextFieldSetExt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class NavigationSetExtController {
		 @FXML private TextFieldSetExt scoreExt;
		 @FXML private TextFieldSetExt musicExt;
		 @FXML private TextFieldSetExt tabExt;
		 @FXML private Button applyButton;
		 @FXML private Button cancelButton;
		 
		 
		 private String oldScoreExt;
		 private String oldTabExt;
		 private String oldMusicExt;
		 
		 private String description;
		 private String scoreLabel;
		 private String tabLabel;
		 private String musicLabel;
		 private String applyButtonText;
		 private String cancelButtonText;
		 
		 
		 boolean changed;
		 
		public NavigationSetExtController(List<String> scoreExt, List<String> tabExt, List<String> musicExt)
		 {
			this.fillScoreExt(scoreExt);
			this.fillTabExt(tabExt);
			this.fillMusicExt(musicExt);
			this.changed = false;
		 }
		
		public void initView(LanguageModel consts, String CurrentLanguage)
		{
			 Parent root;
			 final Stage stage = new Stage(StageStyle.TRANSPARENT);
			 stage.initModality(Modality.APPLICATION_MODAL);
			 stage.initStyle(StageStyle.DECORATED);
			 this.initLabels(consts, CurrentLanguage);
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
							"NavigationSetExtView.fxml"));
					fxmlLoader.setController(this);
					root = fxmlLoader.load();
					Scene scene = new Scene(root, 300, 275);
			        stage.setTitle(consts.getText("SETEXTMENU", CurrentLanguage));
			        stage.setScene(scene);
			        this.scoreExt.setValue(this.oldScoreExt);
			        this.tabExt.setValue(this.oldTabExt);
			        this.musicExt.setValue(this.oldMusicExt);
			        stage.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		private void initLabels(LanguageModel consts, String currentLanguage)
		 {
			 this.description = new String("Ajoutez les extentions de fichiers que vous\n souhaitez voir s'afficher dans la partie navigation\nseparee par un \";\"");
			 this.description = consts.getText("SETEXTDESCRIPTION", currentLanguage);
			 this.scoreLabel = new String("Partitions");
			 this.scoreLabel = consts.getText("SCORELABEL", currentLanguage);
			 this.tabLabel = new String("Tablatures");
			 this.tabLabel = consts.getText("TABLABEL", currentLanguage);
			 this.musicLabel = new String("Musiques");
			 this.musicLabel = consts.getText("MUSICLABEL", currentLanguage);
			 this.applyButtonText = new String("Appliquer");
			 this.applyButtonText = consts.getText("SETEXTAPPLYBUTTON", currentLanguage);
			 this.cancelButtonText = new String("Annuler");
			 this.cancelButtonText = consts.getText("SETEXTCANCELBUTTON", currentLanguage);
		 }
		 
		 private void fillScoreExt(List<String> scoreExt)
		 {
			 oldScoreExt = new String();
			 for (String ext : scoreExt)
				 oldScoreExt = oldScoreExt + ext + ";";
		 }
		 
		 private void fillTabExt(List<String> tabExt)
		 {
			 oldTabExt = new String();
			 for (String ext : tabExt)
				 oldTabExt = oldTabExt + ext + ";";
		 }
		 
		 private void fillMusicExt(List<String> musicExt)
		 {
			 oldMusicExt = new String();
			 for (String ext : musicExt)
				 oldMusicExt = oldMusicExt + ext + ";";
		 }
		 
		 public String getDescription() {
				return description;
		}

		public String getScoreLabel() {
				return scoreLabel;
		}

		public String getTabLabel() {
				return tabLabel;
		}

		public String getMusicLabel() {
				return musicLabel;
		}

		public String getApplyButtonText() {
				return applyButtonText;
		}
		
		public String getCancelButtonText() {
				return cancelButtonText;
		}
		
		public List<String> getNewScoreExt(){
			return (new ArrayList<String>(Arrays.asList(this.scoreExt.getText().split(";"))));
		}
		
		public List<String> getNewTabExt(){
			return (new ArrayList<String>(Arrays.asList(this.tabExt.getText().split(";"))));
		}
		
		public List<String> getNewMusicExt(){
			return (new ArrayList<String>(Arrays.asList(this.musicExt.getText().split(";"))));
		}
		
		public boolean isChanged() {
				return changed;
		}
		public String getOldScoreExt() {
			return oldScoreExt;
		}

		public void setOldScoreExt(String oldScoreExt) {
			this.oldScoreExt = oldScoreExt;
		}

		public String getOldTabExt() {
			return oldTabExt;
		}

		public void setOldTabExt(String oldTabExt) {
			this.oldTabExt = oldTabExt;
		}

		public String getOldMusicExt() {
			return oldMusicExt;
		}

		public void setOldMusicExt(String oldMusicExt) {
			this.oldMusicExt = oldMusicExt;
		}
		@FXML protected void applyButtonClicked(ActionEvent event) {
		     this.changed = true;
			 Stage stage = (Stage) this.applyButton.getScene().getWindow();
		     stage.close();
		    }
		@FXML protected void cancelButtonClicked(ActionEvent event) {
			 this.changed = false;
			 Stage stage = (Stage) this.cancelButton.getScene().getWindow();
		     stage.close();
		    }
}
