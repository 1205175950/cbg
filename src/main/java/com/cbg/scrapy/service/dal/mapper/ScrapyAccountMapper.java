package com.cbg.scrapy.service.dal.mapper;

import com.cbg.scrapy.service.dal.entity.ScrapyAccount;
import com.cbg.scrapy.service.dal.entity.ScrapyAccountExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ScrapyAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    long countByExample(ScrapyAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int deleteByExample(ScrapyAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int insert(ScrapyAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int insertSelective(ScrapyAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    List<ScrapyAccount> selectByExample(ScrapyAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    ScrapyAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int updateByExampleSelective(@Param("record") ScrapyAccount record, @Param("example") ScrapyAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int updateByExample(@Param("record") ScrapyAccount record, @Param("example") ScrapyAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int updateByPrimaryKeySelective(ScrapyAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scrapy_account_info
     *
     * @mbg.generated Tue Mar 04 22:42:22 CST 2025
     */
    int updateByPrimaryKey(ScrapyAccount record);
}