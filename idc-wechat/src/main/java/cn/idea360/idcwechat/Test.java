package cn.idea360.idcwechat;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception{
        String jsonString = "{\"code\":0,\"data\":{\"id\":30,\"companyId\":1,\"userId\":\"ceshi3\",\"subscribe\":1,\"openid\":\"oHOLJw-r6lBxSXU4pRDKpoDyqWI0\",\"nickname\":\"当我遇上你\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"朝阳\",\"province\":\"北京\",\"country\":\"中国\",\"headimgurl\":\"http://thirdwx.qlogo.cn/mmopen/RFKUCMNiaHBDu2OOyCcvq5uTIteIlicusVTVUVNtIicjSyNY2su0eSYAIUzDtlAkE3Ff6uaKN8UvryLwicX1c2OeLNHJR3ibBeo9G/132\",\"subscribeTime\":1587984535,\"remark\":\"\",\"groupid\":0,\"subscribeScene\":\"ADD_SCENE_QR_CODE\",\"qrScene\":0,\"qrSceneStr\":\"{\\\"userId\\\":\\\"ceshi3\\\",\\\"companyId\\\":1}\"},\"msg\":\"操作成功\"}";
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readTree(jsonString);
        JsonNode data = node.get("data");
        String userId = data.get("openid").asText();
        System.out.println(data);
    }
}
