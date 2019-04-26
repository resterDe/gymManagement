package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.CardMapper;
import cn.gymManagement.pojo.Card;
import cn.gymManagement.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    /**
     * 新增会员卡成员
     *
     * @param userID
     * @param rankName
     * @param validTime
     * @return
     */
    @Override
    public int addUserCard(int userID, String rankName, String validTime) {
        return cardMapper.addUserCard(userID, rankName, validTime);
    }

    /**
     * 根据userID删除会员卡信息
     *
     * @param userID
     * @return
     */
    @Override
    public int delCardByUserId(int userID) {
        return cardMapper.delCardByUserId(userID);
    }

    /**
     * 根据会员ID修改会员卡信息
     *
     * @param rankName
     * @param validTime
     * @return
     */
    @Override
    public int updateCardByUserId(String rankName, String validTime, int userID) {
        return cardMapper.updateCardByUserId(rankName, validTime, userID);
    }

    /**
     * 根据会员类型查询总数
     *
     * @param rankName
     * @return
     */
    @Override
    public int getNumberByName(String rankName) {
        return cardMapper.getNumberByName(rankName);
    }

    /**
     * 根据会员卡类型查询会员信息
     *
     * @param rankName
     * @return
     */
    @Override
    public List<Card> getUserCardByName(int pages, int limits, String rankName) {
        return cardMapper.getUserCardByName(pages, limits, rankName);
    }

    /**
     * 查询会员卡类型所有会员
     *
     * @param pages
     * @param limits
     * @return
     */
    @Override
    public List<Card> getUserCardList(int pages, int limits) {
        return cardMapper.getUserCardList(pages, limits);
    }

    /**
     * 查询会员卡总数
     *
     * @return
     */
    @Override
    public int getCardNumber() {
        return cardMapper.getCardNumber();
    }
}
