package com.towerdefense.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    private Node labelNode;
    private BorderPane map;

    public DragTowers(Pane draggablePane) throws IOException, FileNotFoundException {

        this.draggablePane = draggablePane;
        this.labelNode = draggablePane;
        map = MapCell.getMap(1); // It will be modified in constructor, just trial.

    }

    public Group clone(Group group) {

        Group clonedGroup = new Group();
        clonedGroup.getChildren().addAll(group.getChildren());

        return clonedGroup;
    }

    public void draggableTower(Label towerLabel, Group towerGroup) {

        Group clonedDraggableGroup = clone(towerGroup);
        Circle towerCircle = new Circle(200);
        towerCircle.setFill(Color.rgb(255, 0, 0, 0.4));

        towerCircle.setVisible(false);
        clonedDraggableGroup.setVisible(false);

        draggablePane.getChildren().addAll(clonedDraggableGroup, towerCircle);

        towerLabel.setOnMousePressed(e -> {

            Point2D relativePoint = draggablePane.sceneToLocal(e.getSceneX(), e.getSceneY());
            towerCircle.setVisible(true);
            clonedDraggableGroup.setVisible(true);

            clonedDraggableGroup
                    .setLayoutX(relativePoint.getX() - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup
                    .setLayoutY(relativePoint.getY() - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(relativePoint.getX());
            towerCircle.setCenterY(relativePoint.getY());

            e.consume();
        });

        towerLabel.setOnMouseDragged(e -> {

            Point2D relativePoint = draggablePane.sceneToLocal(e.getSceneX(), e.getSceneY());

            clonedDraggableGroup
                    .setLayoutX(relativePoint.getX() - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup
                    .setLayoutY(relativePoint.getY() - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(relativePoint.getX());
            towerCircle.setCenterY(relativePoint.getY());

            e.consume();
        });

        towerLabel.setOnMouseReleased(e -> {

            towerCircle.setVisible(false);

            Point2D relativePoint = draggablePane.sceneToLocal(e.getSceneX(), e.getSceneY());

            for (Node rectangle : ((GridPane) map.getCenter()).getChildren()) {

                Bounds bounds = rectangle.localToScene(rectangle.getBoundsInLocal());

                if (bounds.contains(relativePoint)) {

                    double centerX = draggablePane.sceneToLocal((bounds.getMinX() + bounds.getMaxX()) / 2, 0).getX();
                    double centerY = draggablePane.sceneToLocal(0, ((bounds.getMinY() + bounds.getMaxY()) / 2)).getY();

                    clonedDraggableGroup
                            .setLayoutX(centerX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
                    clonedDraggableGroup
                            .setLayoutY(centerY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

                    towerCircle.setCenterX(centerX);
                    towerCircle.setCenterY(centerY);
                }
            }

            repositioner(clonedDraggableGroup, towerCircle);
            e.consume();

        });
    }

    public void repositioner(Group group, Circle circle) {
        final double[] locationDifference = new double[2];

        group.setOnMousePressed(e -> {

            Point2D relativePoint = draggablePane.sceneToLocal(e.getSceneX(), e.getSceneY());

            locationDifference[0] = relativePoint.getX() - group.getLayoutX();
            locationDifference[1] = relativePoint.getY() - group.getLayoutY();

            circle.setVisible(true);
            e.consume();
        });

        group.setOnMouseDragged(e -> {

            Point2D relativePoint = draggablePane.sceneToLocal(e.getSceneX(), e.getSceneY());

            group.setLayoutX(relativePoint.getX() - locationDifference[0]);
            group.setLayoutY(relativePoint.getY() - locationDifference[1]);

            circle.setCenterX(group.getLayoutX() + group.getBoundsInLocal().getWidth() / 2);
            circle.setCenterY(group.getLayoutY() + group.getBoundsInLocal().getHeight() / 2);

            e.consume();
        });

        group.setOnMouseReleased(e -> {
            circle.setVisible(false);
            e.consume();
        });
    }
}
