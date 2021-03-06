package app.presenter.week;

import app.di.DIProvider;
import app.util.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class WeekViewPresenter {

    @FXML
    private HBox weekPane;

    @FXML
    public void initialize() {
        DIProvider.getAppContext().observeSelectedDate().subscribe((date) -> {
            setupView(date);
            Node node = weekPane.getChildren().get(date.getDayOfWeek().getValue() - 1);
            node.setStyle("-fx-background-color: #FFCFFF;");
        });

    }

    private void setupView(LocalDate selectedDate) {
        weekPane.getChildren().clear();
        LocalDate startDayOfWeek = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() - 1);
        for (int i = 0; i < 7; i++) {
            ViewUtils.LoadedView<Node, WeekViewDayPresenter> loadedView = ViewUtils.loadView("week/WeekViewDay.fxml");
            Node n = loadedView.view;
            n.prefWidth(weekPane.getWidth() / 7.0);
            n.prefHeight(weekPane.getHeight());
            loadedView.controller.setDate(startDayOfWeek.plusDays(i));
            weekPane.getChildren().add(n);
        }
    }
}
