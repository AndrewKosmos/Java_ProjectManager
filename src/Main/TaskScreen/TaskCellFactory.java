package Main.TaskScreen;

import Main.TaskCard.TaskCardController;
import Models.TaskCardModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Created by Andrew on 13.05.2018.
 */
public class TaskCellFactory implements Callback<ListView<TaskCardModel>, ListCell<TaskCardModel>> {
    @Override
    public ListCell<TaskCardModel> call(ListView<TaskCardModel> param) {
        return new TaskCardController();
    }
}
