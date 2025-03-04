package com.cbg.scrapy.service.facade;

import com.cbg.scrapy.service.dal.dao.AccountDao;
import com.cbg.scrapy.service.dal.entity.ScrapyAccount;
import com.cbg.scrapy.service.exception.CbgBizException;
import com.cbg.scrapy.service.scrapy.CbgScrapyService;
import com.cbg.scrapy.web.vo.business.AccountVo;
import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

@Service
public class AccountFacade {

    @Resource
    private AccountDao accountDao;
    @Resource
    private CbgScrapyService cbgScrapyService;

    /**
     * 判断当前是否存在可用账号
     */
    public boolean hasAliveAccount() {
        return accountDao.hasAliveAccount();
    }

    /**
     *  新增账号
     */
    public boolean createAccount(AccountVo accountVo) {
        ScrapyAccount existAccount = accountDao.getAccountByName(accountVo.getUserName());
        if (null != existAccount) {
            CbgBizException.fly("待新增的账号名称已经存在，请直接进行更新");
        }
        accountDao.insertAccount(accountVo);
        return true;
    }

    /**
     * 更新账号
     */
    public boolean updateAccount(AccountVo accountVo) {
        if (null == accountVo.getId()) {
            CbgBizException.fly("待更新账号信息内容不完整，请检查");
        }
        ScrapyAccount existAccount = accountDao.getAccountByName(accountVo.getUserName());
        if (null == existAccount) {
            CbgBizException.fly("待更新账号不存在，请检查");
        }
        accountDao.updateAccount(accountVo);
        return true;
    }

    /**
     * 通过爬虫登录验证账号有效性
     */
    public boolean isUsefulAccount(AccountVo accountVo) {
        return cbgScrapyService.trySignIn(accountVo.getUserName(), accountVo.getPassword());
    }
}
