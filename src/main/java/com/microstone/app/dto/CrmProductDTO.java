package com.microstone.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CrmProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long productId;
    /**
     *	是否父产品
     */
    @ApiModelProperty(value = "是否父产品")
    private Boolean parent;
    /**
     * 父产品id
     */
    @ApiModelProperty(value = "父产品id")
    private Long parentProductId;
    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String name;
    /**
     * 子产品名称
     */
    @ApiModelProperty(value = "子产品名称")
    private String childName;
    /**
     * 创建产品步骤
     * */
    @ApiModelProperty(value = "创建产品步骤")
    public Integer createStep;
    /**
     * 产品状态
     */
    @ApiModelProperty(value = "产品状态")
    private Integer productStatus;
    /**
     * 先前产品状态
     */
    @ApiModelProperty(value = "先前产品状态")
    private Integer productPreviousStatus;
    /**
     * 产品编码
     */
    @ApiModelProperty(value = "产品编码")
    private String code;
    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型")
    private Integer type;
    /**
     * 基金备案名称
     */
    @ApiModelProperty(value = "基金备案名称")
    private String registeredName;
    /**
     * 基金管理人
     */
    @ApiModelProperty(value = "基金管理人")
    private Long manager;
    /**
     * 基金管理人
     */
    @ApiModelProperty(value = "基金管理人")
    private String managerName;
    /**
     * 产品风险等级
     */
    @ApiModelProperty(value = "产品风险等级")
    private Integer riskLevel;
    /**
     * 币种编号
     */
    @ApiModelProperty(value = "币种编号")
    private Long currency;

    /**
     * 币种编号
     */
    @ApiModelProperty(value = "币种")
    private String currencyName;

    /**
     * 币种编号
     */
    @ApiModelProperty(value = "币种")
    private String currencyCharacter;

    /**
     * 是否有产品规模
     */
    @ApiModelProperty(value = "是否有产品规模")
    private Boolean hasScale;
    /**
     * 产品规模
     */
    @ApiModelProperty(value = "产品规模")
    private BigDecimal productScale;
    /**
     * 募集规模
     */
    @ApiModelProperty(value = "募集规模")
    private BigDecimal scale;
    /**
     * 认购起点
     */
    @ApiModelProperty(value = "认购起点")
    private BigDecimal startScale;
    /**
     * 已募集规模
     */
    @ApiModelProperty(value = "已募集规模")
    private BigDecimal raiseScale;
    /**
     * 预约额度
     * */
    @ApiModelProperty(value = "预约额度")
    private BigDecimal bookScale;
    /**
     * 打款挂起额度
     * */
    @ApiModelProperty(value = "打款挂起额度")
    private BigDecimal raisePendingScale;
    /**
     * 递增金额
     */
    @ApiModelProperty(value = "递增金额")
    private BigDecimal stepScale;
    /**
     * 投资人数上限
     */
    @ApiModelProperty(value = "投资人数上限")
    private Integer maxInvestors;
    /**
     * 是否永续
     */
    @ApiModelProperty(value = "是否永续")
    private Boolean continuity;
    /**
     * 投资期限
     */
    @ApiModelProperty(value = "投资期限")
    private String investmentTerm;
    /**
     * 是否启用警戒线
     */
    @ApiModelProperty(value = "是否启用警戒线")
    private Boolean warningLine;
    /**
     * 缴款方式
     */
    @ApiModelProperty(value = "缴款方式")
    private Integer payMethod;
    /**
     * 是否有份额
     */
    @ApiModelProperty(value = "是否有份额")
    private Boolean hasShare;
    /**
     * 是否有子产品
     */
    @ApiModelProperty(value = "是否有子产品")
    private Boolean hasChildrenProduct;
    /**
     * 是否产品组
     */
    @ApiModelProperty(value = "是否产品组")
    private Boolean hasProductGroup;
    /**
     * 模板编号
     */
    @ApiModelProperty(value = "模板编号")
    private Long templateId;
    /**
     * 模板编号
     */
    @ApiModelProperty(value = "模板编号")
    private String templateName;
    /**
     * 存量金额
     */
    @ApiModelProperty(value = "存量金额")
    private BigDecimal stockMoney;
    /**
     * 关账时间
     */
    @ApiModelProperty(value = "关账时间")
    private Date closeAccountDate;
    /**
     * 预计关账时间
     */
    @ApiModelProperty(value = "预计关账时间")
    private Date expectCloseAccountDate;
    /**
     * 成立日期
     */
    @ApiModelProperty(value = "成立日期")
    private Date establishDate;
    /**
     * 是否提前确认到期日
     */
    @ApiModelProperty(value = "是否提前确认到期日")
    private Boolean confirmMaturityDate;
    /**
     * 起息日
     */
    @ApiModelProperty(value = "起息日")
    private Date valueDate;
    /**
     * 到期时间
     */
    @ApiModelProperty(value = "到期时间")
    private Date maturityDate;
    /**
     * 结算方式
     */
    @ApiModelProperty(value = "结算方式")
    private Integer settlementMethod;
    /**
     * 产品经理
     */
    @ApiModelProperty(value = "产品经理")
    private Long productManagerId;
    /**
     * 产品经理
     */
    @ApiModelProperty(value = "产品经理")
    private String productManagerName;
    /**
     * 产品经理
     */
    @ApiModelProperty(value = "产品经理")
    private Integer productManagerStatus;
    /**
     * 运营总监
     */
    @ApiModelProperty(value = "运营总监")
    private Long investmentChiefManagerId;
    /**
     * 运营经理
     */
    @ApiModelProperty(value = "运营总监")
    private String investmentChiefManagerName;
    /**
     * 运营经理
     */
    @ApiModelProperty(value = "运营总监")
    private Integer investmentChiefManagerStatus;
    /**
     * 运营经理
     */
    @ApiModelProperty(value = "运营经理")
    private Long investmentManagerId;
    /**
     * 运营经理
     */
    @ApiModelProperty(value = "运营经理")
    private String investmentManagerName;
    /**
     * 运营经理
     */
    @ApiModelProperty(value = "运营经理")
    private Integer investmentManagerStatus;
    /**
     * 投后经理
     */
    @ApiModelProperty(value = "投后经理")
    private Long postInvestmentManagerId;
    /**
     * 投后经理
     */
    @ApiModelProperty(value = "投后经理")
    private String postInvestmentManagerName;
    /**
     * 投后经理
     */
    @ApiModelProperty(value = "投后经理")
    private Integer postInvestmentManagerStatus;
    /**
     * 会议秘书
     */
    @ApiModelProperty(value = "会议秘书")
    private Long meetingSecretaryId;
    /**
     * 会议秘书
     */
    @ApiModelProperty(value = "会议秘书")
    private String meetingSecretaryName;
    /**
     * 会议秘书
     */
    @ApiModelProperty(value = "会议秘书")
    private Integer meetingSecretaryStatus;
    /**
     * 发行经理
     */
    @ApiModelProperty(value = "发行经理")
    private Long publicationManagerId;
    /**
     * 发行经理
     */
    @ApiModelProperty(value = "发行经理")
    private String publicationManagerName;
    /**
     * 发行经理
     */
    @ApiModelProperty(value = "发行经理")
    private Integer publicationManagerStatus;
    /**
     * 发行总监
     */
    @ApiModelProperty(value = "发行总监")
    private Long publicationChiefManagerId;
    /**
     * 发行总监
     */
    @ApiModelProperty(value = "发行总监")
    private String publicationChiefManagerName;
    /**
     * 发行总监
     */
    @ApiModelProperty(value = "发行总监")
    private Integer publicationChiefManagerStatus;

    /**
     * SPV/有限合伙编号
     */
    @ApiModelProperty(value = "SPV/有限合伙编号")
    private Long companyId;
    /**
     * SPV/有限合伙
     */
    @ApiModelProperty(value = "SPV/有限合伙")
    private String companyName;


    private Long createUser;
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("创建部门")
    private Long createDept;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty("创建时间")
    private Date createTime;

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("更新人")
    private Long updateUser;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("业务状态")
    private Integer status;

    private Integer isDeleted;

    /*    private List<ProductModuleValueDto> moduleValueList;*/
    /**
     * 险种（仅保险）
     */
    @ApiModelProperty(value = "险种（仅保险）")
    private String insurance;
    /**
     * 保险公司（仅保险）
     */
    @ApiModelProperty(value = "保险公司（仅保险）")
    private Long insuranceCompany;
    /**
     * 保险公司名（仅保险）
     */
    @ApiModelProperty(value = "保险公司名（仅保险）")
    private String insuranceCompanyName;
    /**
     * 险种地区（仅保险）
     */
    @ApiModelProperty(value = "险种地区（仅保险）")
    private String insuranceRegion;
    /**
     * 保险标的金额（仅保险）
     */
    @ApiModelProperty(value = "保险标的金额（仅保险）")
    private BigDecimal insuredAmount;
    /**
     * 首年保费（仅保险）
     */
    @ApiModelProperty(value = "首年保费（仅保险）")
    private BigDecimal firstYearInsurance;
    /**
     * 缴费年限（仅保险）
     * */
    @ApiModelProperty(value = "缴费年限（仅保险）")
    private Integer paymentYears;
    /**
     * 打款模式
     * */
    @ApiModelProperty(value = "打款模式")
    private Integer tradeMode;
    /**
     * 递增方式
     * */
    @ApiModelProperty(value = "递增方式")
    private Integer stepMethod;
    /**
     * 能否转让
     * */
    @ApiModelProperty(value = "能否转让")
    private Boolean transfer;
    /**
     * 发行地
     * */
    @ApiModelProperty(value = "发行地")
    private Integer publishPlace;
    /**
     * 昵称
     * */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 募集规模警戒线
     * */
    @ApiModelProperty(value = "募集规模警戒线")
    private BigDecimal raiseScaleWarningLine;

    /**
     * 投资人数警戒线
     * */
    @ApiModelProperty(value = "投资人数警戒线")
    private Integer investorsWarningLine;

    /**
     * 已投资人数
     * */
    @ApiModelProperty(value = "已投资人数")
    private Integer investorsNumber;

    /**
     * 已投资挂起人数
     * */
    @ApiModelProperty(value = "已投资挂起人数")
    private Integer investorsPendingNumber;

    /**
     * 首次预约超时
     * */
    @ApiModelProperty(value = "首次预约超时")
    private BigDecimal firstBookOverTime;

    /**
     * 重复打款超时
     * */
    @ApiModelProperty(value = "重复打款超时")
    private BigDecimal duplicatePayOverTime;

    /**
     * 分配方式
     * */
    @ApiModelProperty(value = "分配方式")
    private String distributionMethod;

    /**
     * 费用
     * */
    @ApiModelProperty(value = "费用")
    private String fee;

    /**
     * 投资方向
     * */
    @ApiModelProperty(value = "投资方向")
    private String investmentDirection;

    /*	*//**
     * 普通投资者产品交易附件
     * *//*
	@ApiModelProperty(value = "普通投资者产品交易附件")
	private String normalTradeAttachment;

	*//**
     * 普通投资者产品交易附件
     * *//*
	@ApiModelProperty(value = "普通投资者产品交易附件")
	private String professionalTradeAttachment;*/

    /**
     * 收益类型
     * */
    @ApiModelProperty(value = "收益类型")
    private Integer incomeType;

    /**
     * 基金类型
     * */
    @ApiModelProperty(value = "基金类型")
    private Integer fundType;

    /**
     * 是否循环募集
     * */
    @ApiModelProperty(value = "是否循环募集")
    private Boolean circulatingRaising;

    /**
     * 是否允许跨子产品追加
     * */
    @ApiModelProperty(value = "是否允许跨子产品追加")
    private Boolean childAdditionalPayment;

    /**
     * 打款备注
     * */
    @ApiModelProperty(value = "打款备注")
    private String paymentNote;

    /**
     * 封闭类型(证券专用)
     * */
    @ApiModelProperty(value = "封闭类型(证券专用)")
    private Integer closureType;

    /**
     * 首个开放日(证券专用)
     * */
    @ApiModelProperty(value = "首个开放日(证券专用)")
    private Date firstOpenDay;

    /*   *//**
     * 运营材料
     * *//*
    @ApiModelProperty(value = "运营材料")
    private List<ElementFileValue> marketingMaterialList = new ArrayList<>();

    *//**
     * 备查文件
     * *//*
    @ApiModelProperty(value = "备查文件")
    private List<ElementFileValue> referenceDocumentList = new ArrayList<>();

    *//**
     * 基金合同
     * *//*
    @ApiModelProperty(value = "基金合同")
    private List<ElementFileValue> fundContractList = new ArrayList<>();

    *//**
     * 交易材料
     * *//*
    @ApiModelProperty(value = "交易材料")
    private List<ElementFileValue> tradeMaterialList = new ArrayList<>();

    *//**
     * 发行制度
     * *//*
    @ApiModelProperty(value = "发行制度")
    private List<ElementFileValue> issueRuleList = new ArrayList<>();*/


    /**
     * 产品经理是否确认
     * */
    @ApiModelProperty(value = "产品经理是否确认")
    private Boolean productManagerConfirm;

    /**
     * 投资经理是否确认
     * */
    @ApiModelProperty(value = "投资经理是否确认")
    private Boolean investmentManagerConfirm;

    /**
     * 投后经理是否确认
     * */
    @ApiModelProperty(value = "投后经理是否确认")
    private Boolean postInvestmentManagerConfirm;

    /**
     * 是否提前确认清算日
     * */
    @ApiModelProperty(value = "是否提前确认清算日")
    private Boolean confirmLiquidationDate;

    /**
     * 清算日
     * */
    @ApiModelProperty(value = "清算日")
    private Date liquidationDate;

    /*———————for sync———————*/

    /**
     * 是否同步
     * */
    @ApiModelProperty(value = "是否同步")
    private Boolean sync;


    /**
     * 产品介绍
     * */
    @ApiModelProperty(value = "产品介绍")
    private String productIntroduction;

    /**
     * 是否在小程序显示
     * */
    @ApiModelProperty(value = "是否在小程序显示")
    private Boolean onMiniProgram;

    /**
     * 是否精选
     * */
    @ApiModelProperty(value = "是否精选")
    private Boolean onHandpick;

    /**
     * 产品亮点
     * */
    @ApiModelProperty(value = "产品亮点")
    private String productHighlight;

    /**
     * 最小收益率
     * */
    @ApiModelProperty(value = "最小收益率")
    private BigDecimal minIncomeRate;

    /**
     * 最大收益率
     * */
    @ApiModelProperty(value = "最大收益率")
    private BigDecimal maxIncomeRate;
    /**
     * 产品亮点
     * */
    @ApiModelProperty(value = "产品亮点")
    private Integer investmentTermSum;
    /**
     * 是否需要电子签
     * */
    @ApiModelProperty(value = "是否需要电子签")
    private Boolean isNeedSign;
    /**
     * 个人版 业务合同模版id
     * */
    @ApiModelProperty(value = "业务合同模版id")
    private Long contractBusinessPersonal;
    /**
     * 机构版 业务合同模版id
     * */
    @ApiModelProperty(value = "业务合同模版id")
    private Long contractBusinessOrgan;

    private BigDecimal lastNetValue;

    private Integer appProductStatus;

    private BigDecimal exchangeRate;
}
