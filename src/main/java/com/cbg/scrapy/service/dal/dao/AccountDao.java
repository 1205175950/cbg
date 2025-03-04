package com.cbg.scrapy.service.dal.dao;

import com.cbg.scrapy.service.dal.entity.ScrapyAccount;
import com.cbg.scrapy.service.dal.entity.ScrapyAccountExample;
import com.cbg.scrapy.service.dal.mapper.ScrapyAccountMapper;
import com.cbg.scrapy.web.vo.business.AccountVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Repository
public class AccountDao {

    @Resource
    private ScrapyAccountMapper scrapyAccountMapper;

    /**
     * 获取所有可用账号
     */
    public List<ScrapyAccount> getAllActiveAccount() {
        ScrapyAccountExample example = new ScrapyAccountExample();
        ScrapyAccountExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        return scrapyAccountMapper.selectByExample(example);
    }

    /**
     * 是否存在可用账号信息
     * @return true-可用，false-不可用
     */
    public boolean hasAliveAccount() {
        ScrapyAccountExample example = new ScrapyAccountExample();
        ScrapyAccountExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        List<ScrapyAccount> scrapyAccounts = scrapyAccountMapper.selectByExample(example);
        return !CollectionUtils.isEmpty(scrapyAccounts);
    }

    /**
     * 根据账号名称查询账号详情
     */
    public ScrapyAccount getAccountByName(String name) {
        ScrapyAccountExample example = new ScrapyAccountExample();
        ScrapyAccountExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(name);
        List<ScrapyAccount> scrapyAccounts = scrapyAccountMapper.selectByExample(example);
        return CollectionUtils.isEmpty(scrapyAccounts) ? null : scrapyAccounts.get(0);
    }

    /**
     * 新增账号信息
     */
    public void insertAccount(AccountVo accountVo) {
        ScrapyAccount scrapyAccount = new ScrapyAccount();
        BeanUtils.copyProperties(accountVo, scrapyAccount);
        scrapyAccount.setAccount(accountVo.getUserName());
        scrapyAccount.setAddTime(new Date());
        scrapyAccount.setUpdateTime(new Date());
        scrapyAccountMapper.insert(scrapyAccount);
    }

    /**
     * 更新账号信息
     */
    public void updateAccount(AccountVo accountVo) {
        ScrapyAccount scrapyAccount = new ScrapyAccount();
        BeanUtils.copyProperties(accountVo, scrapyAccount);
        scrapyAccount.setAccount(accountVo.getUserName());
        scrapyAccount.setUpdateTime(new Date());
        scrapyAccountMapper.updateByPrimaryKey(scrapyAccount);
    }
}
