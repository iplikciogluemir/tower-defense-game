package com.towerdefense.ui;

import java.util.HashMap;
import com.towerdefense.map.MapCell;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DragTowers {

    private BorderPane pane;
    private static HashMap<Group, Integer> mp = new HashMap<>();

    public DragTowers(BorderPane uiPane) {
        this.pane = uiPane;
    }

    public static HashMap<Group, Integer> getTowerMap() {
        return mp;
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
        clonedGroup.setScaleX(0.8);
        clonedGroup.setScaleY(0.8);
        return clonedGroup;
    }

    public void draggableTower(Label towerLabel, Group towerGroup) {

        towerLabel.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.PRIMARY) {
                int towerPrice = price(towerGroup);

                if (HUDVariables.getMoney() >= towerPrice) {
                    HUDVariables.setMoney(HUDVariables.getMoney() - price(towerGroup));

                    Group clonedDraggableGroup = clone(towerGroup);
                    Circle towerCircle = new Circle(200);
                    towerCircle.setFill(Color.web(GameColors.getDragCircleColor()));
                    towerCircle.setOpacity(0.4);
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
                }
            }
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
                mp.remove(clonedDraggableGroup);
                towerCircle.setVisible(true);
                e2.consume();
            });

            clonedDraggableGroup.setOnMouseDragged(e2 -> {
                setOnMouseDragged(clonedDraggableGroup, towerCircle, e2);
            });

            clonedDraggableGroup.setOnMouseReleased(e2 -> {
                setOnMouseReleased(clonedDraggableGroup, towerCircle, e2);
            });

            towerLabel.setOnMouseDragged(null);
            towerLabel.setOnMouseReleased(null);
        });
    }

    void setOnMouseReleased(Group group, Circle circle, MouseEvent e) {
        circle.setVisible(false);
        double sceneX = e.getSceneX();
        double sceneY = e.getSceneY();
        boolean isEnteredPane = false;
        boolean isOnEnemyPath = false;
        int index = -1;

        for (Node rectangle : ((GridPane) pane.getCenter()).getChildren()) {
            ++index;
            Bounds bounds = rectangle.getBoundsInParent();

            if (bounds.contains(sceneX, sceneY)) {

                isEnteredPane = true;
                double centerX = (bounds.getMinX() + bounds.getMaxX()) / 2;
                double centerY = (bounds.getMinY() + bounds.getMaxY()) / 2;

                group.setLayoutX(centerX - group.getBoundsInLocal().getWidth() / 2);
                group.setLayoutY(centerY - group.getBoundsInLocal().getHeight() / 2);

                circle.setCenterX(centerX);
                circle.setCenterY(centerY);

                isOnEnemyPath = MapCell.isEnemyPath(MapCell.currMap,
                        GridPane.getRowIndex((Rectangle) rectangle),
                        GridPane.getColumnIndex((Rectangle) rectangle));

                break;
            }
        }
        if (!isEnteredPane || isOnEnemyPath || mp.containsValue(index)) {
            pane.getChildren().removeAll(group, circle);
            HUDVariables.setMoney(HUDVariables.getMoney() + price(group));
        } else {
            mp.put(group, index);
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

    public static int price(Group group) {

        int price = 0;
        int getChildrenSize = group.getChildren().size();
        switch (getChildrenSize) {
            case 11:
                price = 50;
                break;
            case 18:
                price = 120;
                break;
            case 12:
                price = 150;
                break;
            case 26:
                price = 200;
                break;
        }

        return price;
    }
}
