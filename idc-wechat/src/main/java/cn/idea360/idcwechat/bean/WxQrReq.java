package cn.idea360.idcwechat.bean;

import java.io.Serializable;

public class WxQrReq implements Serializable {

    private final String actionName = "QR_LIMIT_STR_SCENE";
    private ActionInfo actionInfo; //  {"scene": {"scene_str": "idcmind"}

    public WxQrReq(String data) {
        SceneStr sceneStr = new SceneStr();
        sceneStr.setSceneStr(data);
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.setScene(sceneStr);
        this.actionInfo = actionInfo;
    }

    public String getActionName() {
        return actionName;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public class ActionInfo {
        private SceneStr scene;

        public SceneStr getScene() {
            return scene;
        }

        public void setScene(SceneStr scene) {
            this.scene = scene;
        }
    }

    public class SceneStr {
        private String sceneStr;

        public String getSceneStr() {
            return sceneStr;
        }

        public void setSceneStr(String sceneStr) {
            this.sceneStr = sceneStr;
        }
    }


}
