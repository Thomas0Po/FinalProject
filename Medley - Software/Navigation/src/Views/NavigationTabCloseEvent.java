package Views;

import Controllers.NavigationController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class NavigationTabCloseEvent implements EventHandler<Event> {

	TabPane tabPaneRef;
	Tab tab;
	NavigationController controllerRef;
	
	public NavigationTabCloseEvent(Tab tab, TabPane tabPane, NavigationController controllerRef)
	{
		this.tabPaneRef = tabPane;
		this.tab = tab;
		this.controllerRef = controllerRef;
	}
	
	@Override
	public void handle(Event arg0) {
		int index = tabPaneRef.getSelectionModel().getSelectedIndex();
		
		//if index == -1 it's the last Tab to delete
		//else the new selected Tab is at the index of the Tab deleted
		if (index == -1)
			controllerRef.delTabAt(0);
		else
			controllerRef.delTabAt(index);
		controllerRef.save();
	}
}
