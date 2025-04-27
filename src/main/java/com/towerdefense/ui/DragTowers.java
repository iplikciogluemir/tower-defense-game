package com.towerdefense.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DragTowers {

    private Pane draggablePane;

    public DragTowers(Pane draggablePane) {
        this.draggablePane = draggablePane;
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

            towerCircle.setVisible(true);
            clonedDraggableGroup.setVisible(true);

            clonedDraggableGroup.setLayoutX(e.getSceneX() - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup.setLayoutY(e.getSceneY() - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(e.getSceneX());
            towerCircle.setCenterY(e.getSceneY());

            e.consume();
        });

        towerLabel.setOnMouseDragged(e -> {

            clonedDraggableGroup.setLayoutX(e.getSceneX() - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup.setLayoutY(e.getSceneY() - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(e.getSceneX());
            towerCircle.setCenterY(e.getSceneY());

            e.consume();
        });

        towerLabel.setOnMouseReleased(e -> {

            towerCircle.setVisible(false);
            repositioner(clonedDraggableGroup, towerCircle);
            e.consume();
        });
    }

    public void repositioner(Group group, Circle circle) {
        final double[] locationDifference = new double[2];

        group.setOnMousePressed(e -> {
            locationDifference[0] = e.getSceneX() - group.getLayoutX();
            locationDifference[1] = e.getSceneY() - group.getLayoutY();

            circle.setVisible(true);
            e.consume();
        });

        group.setOnMouseDragged(e -> {

            group.setLayoutX(e.getSceneX() - locationDifference[0]);
            group.setLayoutY(e.getSceneY() - locationDifference[1]);

            circle.setCenterX(group.getLayoutX() + group.getBoundsInLocal().getWidth() / 2);
            circle.setCenterY(group.getLayoutY() + group.getBoundsInLocal().getHeight() / 2);

            e.consume();
        });

        group.setOnMouseReleased(e -> {
            circle.setVisible(false);
        });
    }
}
