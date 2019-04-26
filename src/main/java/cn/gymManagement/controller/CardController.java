package cn.gymManagement.controller;

import cn.gymManagement.pojo.Card;
import cn.gymManagement.service.CardService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 查询会员卡信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getUserCardList", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8;", "application/js"})
    @ResponseBody
    public String getUserCardList(int page, int limit) {
        //添加到数据集合中
        int count = cardService.getCardNumber();
        int limits = limit;
        int pages = (page - 1) * limit;
        List<Card> cardList = cardService.getUserCardList(pages, limits);
        //设置响应格式
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", count);
        obj.put("data", cardList);
        return obj.toJSONString();
    }

    /**
     * 根据会员类型查询会员信息
     *
     * @param page
     * @param limit
     * @param rankName
     * @return
     */
    @RequestMapping(value = "getUserCardByName", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8;", "application/js"})
    @ResponseBody
    public String getUserCardByName(int page, int limit, @RequestParam("rankName") String rankName) {
        System.out.println("获取的值：" + rankName);
        //添加到数据集合中
        int count = cardService.getNumberByName(rankName);
        int limits = limit;
        int pages = (page - 1) * limit;
        List<Card> rankNameList = cardService.getUserCardByName(pages, limits, rankName);
        //设置响应格式
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", count);
        obj.put("data", rankNameList);
        return obj.toJSONString();
    }
}
