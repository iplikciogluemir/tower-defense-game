package com.towerdefense.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.towerdefense.map.GameMapScanner;
import com.towerdefense.map.MapCell;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DragTowers {

    private Pane draggablePane;
    private BorderPane map;
    ArrayList<Integer> list;

    public DragTowers(Pane draggablePane, BorderPane uiPane) throws IOException, FileNotFoundException {

        this.draggablePane = draggablePane;
        this.map = uiPane;
        list = new ArrayList<>();

    }

    public Group clone(Group group) {

        Group clonedGroup = new Group();

        for (Node node : group.getChildren()) {

            Rectangle source = (Rectangle) node;
            Rectangle cloned = new Rectangle(source.getX(), source.getY(), source.getWidth(), source.getHeight());
            cloned.setFill(source.getFill());
            cloned.setStyle(source.getStyle());

            clonedGroup.getChildren().addAll(cloned);

        }

        return clonedGroup;
    }

    public void draggableTower(Label towerLabel, Group towerGroup) {

        towerLabel.setOnMousePressed(e -> {

            Group clonedDraggableGroup = clone(towerGroup);

            Circle towerCircle = new Circle(200);
            towerCircle.setFill(Color.rgb(255, 0, 0, 0.4));
            draggablePane.getChildren().addAll(clonedDraggableGroup, towerCircle);

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            towerCircle.setVisible(true);
            clonedDraggableGroup.setVisible(true);

            clonedDraggableGroup
                    .setLayoutX(sceneX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup
                    .setLayoutY(sceneY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(sceneX);
            towerCircle.setCenterY(sceneY);

            dragManager(towerLabel, clonedDraggableGroup, towerCircle);

            e.consume();
        });
    }

    void dragManager(Label towerLabel, Group clonedDraggableGroup, Circle towerCircle) {

        towerLabel.setOnMouseDragged(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            clonedDraggableGroup
                    .setLayoutX(sceneX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup
                    .setLayoutY(sceneY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(sceneX);
            towerCircle.setCenterY(sceneY);

            e.consume();
        });

        towerLabel.setOnMouseReleased(e -> {

            towerCircle.setVisible(false);
            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();
            boolean isEnteredPane = false;
            boolean isOnEnemyPath = false;
            int count = -1;

            for (Node rectangle : ((GridPane) map.getCenter()).getChildren()) {

                Bounds bounds = rectangle.localToScene(rectangle.getBoundsInLocal());
                count++;

                if (bounds.contains(sceneX, sceneY)) {

                    isEnteredPane = true;
                    double centerX = draggablePane.sceneToLocal((bounds.getMinX() + bounds.getMaxX()) / 2, 0).getX();
                    double centerY = draggablePane.sceneToLocal(0, ((bounds.getMinY() + bounds.getMaxY()) / 2)).getY();

                    clonedDraggableGroup
                            .setLayoutX(centerX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
                    clonedDraggableGroup
                            .setLayoutY(centerY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

                    towerCircle.setCenterX(centerX);
                    towerCircle.setCenterY(centerY);

                    try {
                        isOnEnemyPath = MapCell.isEnemyPath(MapCell.currMap,
                                GridPane.getRowIndex((Rectangle) rectangle),
                                GridPane.getColumnIndex((Rectangle) rectangle));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    break;

                }
            }

            if (!isEnteredPane || isOnEnemyPath || list.contains(count))
                draggablePane.getChildren().removeAll(clonedDraggableGroup, towerCircle);

            else {
                list.add(count);
                dragManager(towerLabel, clonedDraggableGroup, towerCircle);
            }

            e.consume();
        });
    }

    public void repositioner(BorderPane map, Group group, Circle circle) {
        final double[] locationDifference = new double[2];

        group.setOnMousePressed(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            locationDifference[0] = sceneX - group.getLayoutX();
            locationDifference[1] = sceneY - group.getLayoutY();

            circle.setVisible(true);
            e.consume();
        });

        group.setOnMouseDragged(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            group.setLayoutX(sceneX - locationDifference[0]);
            group.setLayoutY(sceneY - locationDifference[1]);

            circle.setCenterX(sceneX);
            circle.setCenterY(sceneY);

            e.consume();
        });

        group.setOnMouseReleased(e -> {
            circle.setVisible(false);

            boolean isEnteredPane = false;
            boolean isOnEnemyPath = false;

            for (Node rectangle : ((GridPane) map.getCenter()).getChildren()) {

                Bounds bounds = rectangle.localToScene(rectangle.getBoundsInLocal());

                if (bounds.contains(e.getSceneX(), e.getSceneY())) {

                    isEnteredPane = true;
                    double centerX = draggablePane.sceneToLocal((bounds.getMinX() + bounds.getMaxX()) / 2, 0).getX();
                    double centerY = draggablePane.sceneToLocal(0, ((bounds.getMinY() + bounds.getMaxY()) / 2)).getY();

                    group.setLayoutX(centerX - group.getBoundsInLocal().getWidth() / 2);
                    group.setLayoutY(centerY - group.getBoundsInLocal().getHeight() / 2);

                    circle.setCenterX(centerX);
                    circle.setCenterY(centerY);

                    try {
                        isOnEnemyPath = MapCell.isEnemyPath(MapCell.currMap,
                                GridPane.getRowIndex((Rectangle) rectangle),
                                GridPane.getColumnIndex((Rectangle) rectangle));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (isOnEnemyPath) {
                        break;
                    }

                }
            }

            if (!isEnteredPane || isOnEnemyPath) {

                draggablePane.getChildren().removeAll(group, circle);

            }

            e.consume();
        });
    }
}
