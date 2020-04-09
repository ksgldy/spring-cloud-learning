
        SELECT
            T2.*
        FROM
            ( SELECT T1.*, ROWNUM RN FROM ( SELECT ai.* FROM AI_PROJECT_GROUP ai
              <where>
                  1=1
                  <if test="pageDTO.keyword != null">
                      AND ai.group_name LIKE '%' ||  #{pageDTO.keyword} || '%'
                  </if>
              </where>
              ) T1 ) T2
        WHERE
            RN BETWEEN #{pageDTO.beginResult}
                AND #{pageDTO.endResult}
        ORDER BY
            RANK
            
            
            
            
            
            
        SELECT
            T2.*
        FROM
            ( SELECT T1.*, ROWNUM RN FROM ( SELECT ai.* FROM AI_PROJECT_GROUP ai
              <where>
                  1=1
                  <if test="pageDTO.keyword != null">
                      AND ai.group_name LIKE '%' ||  #{pageDTO.keyword} || '%'
                  </if>
              </where>
              ) T1 ) T2
        WHERE
            RN > #{pageDTO.beginResult}
            AND RN <= #{pageDTO.endResult}
        ORDER BY
            RANK
            
            
            
            
            startInde=(page-1)*limit+1; //oracle是从1开始计数的
            endIndex=page*limit;