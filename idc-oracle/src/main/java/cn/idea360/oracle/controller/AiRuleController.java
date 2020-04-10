package cn.idea360.oracle.controller;

import cn.idea360.oracle.dto.AiRuleRankDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.PageRespDTO;
import cn.idea360.oracle.model.AiRule;
import cn.idea360.oracle.service.AiRuleService;
import cn.idea360.oracle.vo.AiRuleReqVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AiRule")
public class AiRuleController {

    @Autowired
    private AiRuleService aiRuleService;

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public Object addOrUpdateAiRule(@RequestBody AiRuleReqVO aiRuleReqVO) {

        boolean result = false;
        AiRule aiRule = new AiRule();
        BeanUtils.copyProperties(aiRuleReqVO, aiRule);

        // todo
        aiRule.setCompanyId(1);
        aiRule.setCreater("blm");
        aiRule.setUpdater("blm");

        try {
            if (aiRule.getId() == null || aiRule.getId() <= 0) {
                result = aiRuleService.insert(aiRule);
            } else {
                result = aiRuleService.updateIgnoreNullById(aiRule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                return R.failed("数据冲突");
            }
            if (e instanceof NullPointerException) {
                return R.failed("数据不存在");
            }
        }

        if (!result) {
            return R.failed(R.Code.FAILED);
        }
        return R.ok();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Object detailAiRuleById(@PathVariable Long id) {
        AiRule aiRule = aiRuleService.selectById(id);
        return R.ok(aiRule);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public Object delAiRule(@PathVariable Long id) {
        boolean result = aiRuleService.removeById(id);
        if (!result) {
            return R.failed(R.Code.FAILED);
        }
        return R.ok();
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object pageAiRule(@RequestBody PageDTO pageDTO) {
        PageRespDTO page = aiRuleService.page(pageDTO);
        return R.ok(page);
    }


    @RequestMapping(value = "/updateRank", method = RequestMethod.POST)
    public Object updateAiRuleRank(@RequestBody List<AiRuleRankDTO> aiRuleRankDTOList) {
        boolean result = aiRuleService.updateRank(aiRuleRankDTOList);
        if (!result) {
            return R.failed(R.Code.FAILED);
        }
        return R.ok();
    }


}
