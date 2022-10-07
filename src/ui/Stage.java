package ui;

import libs.RenderableObject;

import java.util.List;

public abstract class Stage {
    private List<RenderableObject> renderableObjectList;

    public List<RenderableObject> getRenderableObjectList() {
        return renderableObjectList;
    }
}
