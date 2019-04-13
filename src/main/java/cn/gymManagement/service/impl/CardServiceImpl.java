package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.CardMapper;
import cn.gymManagement.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;
    /**
     * 新增会员卡成员
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
     * @param userID
     * @return
     */
    @Override
    public int delCardByUserId(int userID) {
        return cardMapper.delCardByUserId(userID);
    }

    /**
     * 根据会员ID修改会员卡信息
     * @param rankName
     * @param validTime
     * @return
     */
    @Override
    public int updateCardByUserId(String rankName, String validTime,int userID) {
        return cardMapper.updateCardByUserId(rankName, validTime,userID);
    }
}
