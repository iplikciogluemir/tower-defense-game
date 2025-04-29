package com.towerdefense.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.towerdefense.map.MapCell;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DragTowers {

    private BorderPane pane;
    ArrayList<Integer> list;
    int index;

    public DragTowers(BorderPane uiPane) throws IOException, FileNotFoundException {

        this.pane = uiPane;
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
            pane.getChildren().addAll(clonedDraggableGroup, towerCircle);

            clonedDraggableGroup.setPickOnBounds(true);
            towerCircle.setMouseTransparent(true);

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

            repositioner(towerLabel, clonedDraggableGroup, towerCircle);

            e.consume();
        });

    }

    void repositioner(Label towerLabel, Group clonedDraggableGroup, Circle towerCircle) {

        towerLabel.setOnMouseDragged(e -> {
            setOnMouseDragged(clonedDraggableGroup, towerCircle, e);
        });

        towerLabel.setOnMouseReleased(e -> {
            setOnMouseReleased(clonedDraggableGroup, towerCircle, e);

            clonedDraggableGroup.setOnMousePressed(e2 -> {
                towerCircle.setVisible(true);
            });

            clonedDraggableGroup.setOnMouseDragged(e2 -> {
                setOnMouseDragged(clonedDraggableGroup, towerCircle, e2);
            });

            clonedDraggableGroup.setOnMouseReleased(e2 -> {
                setOnMouseReleased(clonedDraggableGroup, towerCircle, e2);
            });
        });
    }

    void setOnMouseReleased(Group group, Circle circle, MouseEvent e) {
        circle.setVisible(false);
        double sceneX = e.getSceneX();
        double sceneY = e.getSceneY();
        boolean isEnteredPane = false;
        boolean isOnEnemyPath = false;
        // list.remove(new Integer(index));
        index = -1;

        for (Node rectangle : ((GridPane) pane.getCenter()).getChildren()) {

            Bounds bounds = rectangle.localToScene(rectangle.getBoundsInLocal());
            index++;

            if (bounds.contains(sceneX, sceneY)) {

                isEnteredPane = true;
                double centerX = pane.sceneToLocal((bounds.getMinX() + bounds.getMaxX()) / 2, 0).getX();
                double centerY = pane.sceneToLocal(0, ((bounds.getMinY() + bounds.getMaxY()) / 2)).getY();

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
                break;
            }
        }
        if (!isEnteredPane || isOnEnemyPath || list.contains(index))
            pane.getChildren().removeAll(group, circle);

        else {
            list.add(index);
        }
        e.consume();
    }

    void setOnMouseDragged(Group group, Circle circle, MouseEvent e) {

        double sceneX = e.getSceneX();
        double sceneY = e.getSceneY();

        group.setLayoutX(sceneX - group.getBoundsInLocal().getWidth() / 2);
        group.setLayoutY(sceneY - group.getBoundsInLocal().getHeight() / 2);

        circle.setCenterX(sceneX);
        circle.setCenterY(sceneY);

        e.consume();
    }
}
