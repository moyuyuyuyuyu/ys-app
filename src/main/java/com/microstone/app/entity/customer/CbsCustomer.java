package com.microstone.app.entity.customer;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 *
 * @author Ms
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CbsCustomer extends TenantEntity {


	private static final long serialVersionUID = 1L;

	/**
	 * 客户编码
	 */
	@ApiModelProperty(value = "客户编码")
	private String code;

	/**
	 * 客户状态
	 */
	@ApiModelProperty(value = "客户编码")
	private Integer status;
	/**
	 * 客户类别
	 */
	@ApiModelProperty(value = "客户类别")
	private Integer type;
	/**
	 * 客户姓名
	 */
	@ApiModelProperty(value = "客户姓名")
	private String name;
	/**
	 * 姓
	 */
	@ApiModelProperty(value = "姓")
	private String lastName;
	/**
	 * 名
	 */
	@ApiModelProperty(value = "名")
	private String firstName;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private Integer gender;
	/**
	 * 国籍
	 */
	@ApiModelProperty(value = "国籍")
	private Integer nationality;
	/**
	 * 其他国籍
	 */
	@ApiModelProperty(value = "其他国籍")
	private String otherNationality;
	/**
	 * 证件类型
	 */
	@ApiModelProperty(value = "证件类型")
	private Integer credentialType;
	/**
	 * 证件号码
	 */
	@ApiModelProperty(value = "证件号码")
	private String credentialNumber;
	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日")
	private Date birthday;
	/**
	 * 证件有效期开始日期
	 */
	@ApiModelProperty(value = "证件有效期开始日期")
	private Date credentialExpiryStart;
	/**
	 * 证件有效期结束日期
	 */
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	@ApiModelProperty(value = "证件有效期结束日期")
	private Date credentialExpiryEnd;
	/**
	 * 证件照正面
	 */
	@ApiModelProperty(value = "证件照正面")
	private String credentialPictureFront;
	/**
	 * 证件照反面
	 */
	@ApiModelProperty(value = "证件照反面")
	private String credentialPictureBack;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String mobile;
	/**
	 * 备用手机号
	 */
	@ApiModelProperty(value = "备用手机号")
	private String otherMobile;
	/**
	 * 地址省
	 */
	@ApiModelProperty(value = "地址省")
	private String province;
	/**
	 * 地址市
	 */
	@ApiModelProperty(value = "地址市")
	private String city;
	/**
	 * 地址明细
	 */
	@ApiModelProperty(value = "地址明细")
	private String addressDetail;
	/**
	 * 地址
	 */
	@ApiModelProperty(value = "地址")
	private String address;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
	/**
	 * 营业执照
	 */
	@ApiModelProperty(value = "营业执照")
	private String licenseNumber;
	/**
	 * 营业执照扫描件
	 */
	@ApiModelProperty(value = "营业执照扫描件")
	private String licensePicture;
	/**
	 * 组织机构代码
	 */
	@ApiModelProperty(value = "组织机构代码")
	private String orgnizationCode;
	/**
	 * 法人姓名
	 */
	@ApiModelProperty(value = "法人姓名")
	private String legalName;
	/**
	 * 法人姓
	 */
	@ApiModelProperty(value = "法人姓")
	private String legalLastName;
	/**
	 * 法人名
	 */
	@ApiModelProperty(value = "法人名")
	private String legalFirstName;
	/**
	 * 法人性别
	 */
	@ApiModelProperty(value = "法人性别")
	private Integer legalGender;
	/**
	 * 法人国籍
	 */
	@ApiModelProperty(value = "法人国籍")
	private Integer legalNationality;
	/**
	 * 法人其他国籍
	 */
	@ApiModelProperty(value = "法人其他国籍")
	private String legalOtherNationality;

	/**
	 * 法人证件类型
	 */
	@ApiModelProperty(value = "法人证件类型")
	private Integer legalCredentialType;
	/**
	 * 法人证件号码
	 */
	@ApiModelProperty(value = "法人证件号码")
	private String legalCredentialNumber;
	/**
	 * 法人生日
	 */
	@ApiModelProperty(value = "法人生日")
	private Date legalBirthday;
	/**
	 * 法人证件有效期开始日期
	 */
	@ApiModelProperty(value = "法人证件有效期开始日期")
	private Date legalCredentialExpiryStart;
	/**
	 * 法人证件有效期结束日期
	 */
	@ApiModelProperty(value = "法人证件有效期结束日期")
	private Date legalCredentialExpiryEnd;
	/**
	 * 法人证件照正面
	 */
	@ApiModelProperty(value = "法人证件照正面")
	private String legalCredentialPictureFront;
	/**
	 * 法人证件照反面
	 */
	@ApiModelProperty(value = "法人证件照反面")
	private String legalCredentialPictureBack;
	/**
	 * 法人是否经办人
	 */
	@ApiModelProperty(value = "法人是否经办人")
	private Boolean copyOperator;
	/**
	 * 经办人姓名
	 */
	@ApiModelProperty(value = "经办人姓名")
	private String operatorName;
	/**
	 * 经办人姓
	 */
	@ApiModelProperty(value = "经办人姓")
	private String operatorLastName;
	/**
	 * 经办人名
	 */
	@ApiModelProperty(value = "经办人名")
	private String operatorFirstName;
	/**
	 * 经办人性别
	 */
	@ApiModelProperty(value = "经办人性别")
	private Integer operatorGender;
	/**
	 * 经办人国籍
	 */
	@ApiModelProperty(value = "经办人国籍")
	private Integer operatorNationality;
	/**
	 * 经办人其他国籍
	 */
	@ApiModelProperty(value = "经办人其他国籍")
	private String operatorOtherNationality;
	/**
	 * 经办人证件类型
	 */
	@ApiModelProperty(value = "经办人证件类型")
	private Integer operatorCredentialType;
	/**
	 * 经办人证件号码
	 */
	@ApiModelProperty(value = "经办人证件号码")
	private String operatorCredentialNumber;
	/**
	 * 经办人生日
	 */
	@ApiModelProperty(value = "经办人生日")
	private Date operatorBirthday;
	/**
	 * 经办人证件有效期开始日期
	 */
	@ApiModelProperty(value = "经办人证件有效期开始日期")
	private Date operatorCredentialExpiryStart;
	/**
	 * 经办人证件有效期结束日期
	 */
	@ApiModelProperty(value = "经办人证件有效期结束日期")
	private Date operatorCredentialExpiryEnd;
	/**
	 * 经办人证件照正面
	 */
	@ApiModelProperty(value = "经办人证件照正面")
	private String operatorCredentialPictureFront;
	/**
	 * 经办人证件照反面
	 */
	@ApiModelProperty(value = "经办人证件照反面")
	private String operatorCredentialPictureBack;
	/**
	 * 经办人地址省
	 */
	@ApiModelProperty(value = "经办人地址省")
	@TableField("operator_Province")
	private String operatorProvince;
	/**
	 * 经办人地址市
	 */
	@ApiModelProperty(value = "经办人地址市")
	private String operatorCity;
	/**
	 * 经办人地址明细
	 */
	@ApiModelProperty(value = "经办人地址明细")
	private String operatorAddressDetail;
	/**
	 * 经办人地址
	 */
	@ApiModelProperty(value = "经办人地址")
	private String operatorAddress;
	/**
	 * 经办人手机号
	 */
	@ApiModelProperty(value = "经办人手机号")
	private String operatorMobile;
	/**
	 * 经办人备用手机号
	 */
	@ApiModelProperty(value = "经办人备用手机号")
	private String operatorOtherMobile;
	/**
	 * 是否有归属
	 */
	@ApiModelProperty(value = "是否有归属")
	private Boolean hasOwned;
//	/**
//	* 是否启用
//	*/
//		@ApiModelProperty(value = "是否启用")
//		private Boolean enable;
	/**
	 * 是否新分配
	 */
	@ApiModelProperty(value = "是否新分配")
	private Boolean hasNewDistribution;
	/**
	 * 是否历史客户
	 */
	@ApiModelProperty(value = "是否历史客户")
	private Boolean hasHistory;
	/**
	 * 理财师编号
	 */
	@ApiModelProperty(value = "理财师编号")
	private Long employeeId;
	/**
	 * 理财师部门
	 */
	@ApiModelProperty(value = "理财师部门id")
	private Long employeeDepartmentId;
	/**
	 * 累计打款金额
	 */
	@ApiModelProperty(value = "累计打款金额")
	private BigDecimal totalPayMoney;
	/**
	 * 存量金额
	 */
	@ApiModelProperty(value = "存量金额")
	private BigDecimal totalStockMoney;
	/**
	 * 存量份额
	 */
	@ApiModelProperty(value = "存量份额")
	private BigDecimal totalStockShare;
	/**
	 * 风险等级
	 */
	@ApiModelProperty(value = "风险等级")
	private Integer riskLevel;
	/**
	 * 风险等级名称
	 */
	@ApiModelProperty(value = "风险等级名称")
	private String levelName;
	/**
	 * 风险测评分数
	 */
	@ApiModelProperty(value = "风险测评分数")
	private BigDecimal rpqScore;
	/**
	 * 投资者认证类型
	 */
	@ApiModelProperty(value = "投资者认证类型")
	private Integer investCertificateType;
	/**
	 * 会员等级名称
	 */
	@ApiModelProperty(value = "会员等级名称")
	private String memberLevelName;
	/**
	 * 会员等级id
	 */
	@ApiModelProperty(value = "会员等级id")
	private Long memberLevelId;


	@ApiModelProperty(value = "会员加速")
	private BigDecimal memberSpeed;

	/**
	 * 客户信息有效
	 */
	@ApiModelProperty(value = "客户信息有效")
	private Boolean hasInfoValidate;
	/**
	 * 客户风测有效
	 */
	@ApiModelProperty(value = "客户风测有效")
	private Boolean hasRpqValidate;
	/**
	 * 首次交易日期
	 */
	@ApiModelProperty(value = "首次交易日期")
	private Date firstTradeDate;
	/**
	 * 最近交易日期
	 */
	@ApiModelProperty(value = "最近交易日期")
	private Date recentTradeDate;
	/**
	 * 最近通话日期
	 */
	@ApiModelProperty(value = "最近通话日期")
	private Date recentCallDate;
	/**
	 * 是否转化
	 */
	@ApiModelProperty(value = "是否转化")
	private Boolean hasTransfer;
	/**
	 * 是否来自isp
	 */
	@ApiModelProperty(value = "是否来自isp")
	private Boolean hasFromIsp;
	/**
	 * 是否分公司
	 */
	@ApiModelProperty(value = "是否分公司")
	private Integer hasBranch;
	/**
	 * 手机号是否正确
	 */
	@ApiModelProperty(value = "手机号是否正确")
	private Boolean hasMobileCorrect;
	/**
	 * 证件号是否正确
	 */
	@ApiModelProperty(value = "证件号是否正确")
	private Boolean hasIdCorrect;
	/**
	 * 是否实名认证
	 */
	@ApiModelProperty(value = "是否实名认证")
	private Boolean hasIdentityVerification;
	/**
	 * 客户推荐码
	 */
	@ApiModelProperty(value = "客户推荐码")
	private String recommendCode;
	/**
	 * 来源
	 */
	@ApiModelProperty(value = "来源")
	private Integer source;


	/**
	 * 删除人
	 */
	@ApiModelProperty(value = "删除人")
	private String deleteUser;
	/**
	 * 删除时间
	 */
	@ApiModelProperty(value = "删除时间")
	private Date deleteTime;
	private Integer version;

	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;

	@ApiModelProperty(value = "会员等级序号")
	private Integer memberLevelSort;

	@ApiModelProperty(value = "会员等级降级开始时间")
	private Date remainTime;

	@ApiModelProperty(value = "是否长期")
	private Boolean isContinue;

	@ApiModelProperty("租户id")
	private String tenantId;

	@ApiModelProperty(value = "其他客户来源")
	private String	otherSource;

	@ApiModelProperty("最近呼叫结果 1-异常 2-失败 3-成功")
	private Integer recentCallResult;

	@ApiModelProperty("回访发送短信时间")
	private Date sendMessageTime;

	@ApiModelProperty("微信昵称")
	private String wechatNickName;

	private Long wechatId;
}
