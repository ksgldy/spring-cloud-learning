# 人机结合API

## 可选客服人员

- method: POST
- url: /ProjectUser/users
- header: Content-Type:application/json
- requestBody: {
"id":1,
"departmentId":1,
"searchField":"userId",
"keyword":""
}

## 已选客服人员

- method: GET
  - url: /ProjectGroup/detail/{groupId}


  ## 新增修改项目组

  - method: POST
  - url: ProjectGroup/addOrUpdate
  - header: Content-Type:application/json
  - requestBody: {
  "id":80,
  "groupName":"",
  "userIds":[""]
  }

  ## 删除项目组

  - method: POST
  - url: /ProjectGroup/del/{groupId}

  ## 项目组分页条件查询

  - method: POST
  - url: /ProjectGroup/page
  - header: Content-Type:application/json
  - requestBody: {
  "page":1,
  "size":10,
  "keyword":""
  }

  ## 新增修改规则

  - method: POST
  - url: /AiRule/addOrUpdate
  - header: Content-Type:application/json
  - requestBody: {
  "id":26,
  "groupId": 78,
  "ruleName": "rule22",
  "state":1,
  "condition":"",
  "action":""
  }

  **condition说明：**

  ```json
  {
      "conditionOne":{                    //条件一
          "relation":0,                   //关系(url区配与关键词匹配条件之间关系 0或、1并且，默认为0)
          "urlState":0,                   //访问url是否开启（0否、1是,默认为0）
          "urlOption":"chatUrl",          //url匹配参数（chatUrl对话建立页、firstUrl初始访问页,默认为chatUrl）
          "urlValue":"url1,url2,...",     //url值（英文逗号隔开）
          "keyWordState":0,               //关键词是否开启（0否、1是,默认为0）
          "keyWordOption":0,              //关键词匹配类型（0任意、1全部,默认为0）
          "keyWordValue":"key1,key2,..."  //关键词值（英文逗号隔开）
      },
      "conditionTwo":{                    //条件二
          "visitorState":0,               //访客未说话时间是否开启（0否、1是,默认为0）
          "timeOut":6,                    //超时时间
          "timeType":0                    //时间类型（0秒、1分，默认为0）
      },
      "conditionThree":{                  //条件三
          "visitorMsgState":0,            //访客发消息数是否开启（0否、1是,默认为0）
          "count":0                       //消息数
      }
  }
  ```

  **action说明：**

  ```json
  {
      "voiceState":0,                     //声音提醒是否开启0否 1是
      "flickerState":0,                   //闪动提醒是否开启  0否 1是
      "chatListState":0,                  //对话列表提醒是否开启  0否 1是
      "color":"#000000"                    //对话列表提醒色值
  }
  ```

  > 说明：condition和action需要 `序列化字符串` 处理。

  ## 规则详情

  - method: GET
  - url: /AiRule/detail/{ruleId}

  ## 删除规则

  - method: POST
  - url: /AiRule/del/{ruleId}

  ## 规则分页条件查询

  - method: POST
  - /AiRule/page
  - headers: Content-Type:application/json
  - requestBody: {
  "page":1,
  "size":10,
  "keyword":""
  }