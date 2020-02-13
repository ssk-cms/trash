package com.trash.collection.trash.common;

/**
 * 状态码
 *
 * @author seth
 * @since 2020-01-19
 */
public enum StatusCode {
    // 1xxx 操作成功但需要客户端进行操作
    BIND_MOBILE(1000),

    // 2xxx 操作成功
    Success(2000),
    VERIFYCODE_SENDED(2100),
    UPLOAD_ID_SUCCESS(2200),
    DOUBLE_CLAIMING(2210),
    CHECK_IN_END(2220),

    // 3xxx 需要用户重试
    Try_Again(3000),
    // 3001 需要用户确认
    Try_Confirm(3001),


    // # 31xx 验证相关
    // # 310x 手机相关
    // 3100 手机号码不存在
    Mobile_Not_Exist(3100),
    // 3101 手机号码格式不正确
    Invalid_Mobile(3101),
    // 3102 手机号码不正确
    Mobile_Error(3102),


    // # 311x 验证码相关
    // 3110 发送验证码失败，请重试
    VerifyCode_Send_Fail(3110),
    // 3111 验证码无效,请重试
    Invalid_VerifyCode(3111),
    // 3112 发送验证码太频繁,请稍后重试
    VerifyCode_Busy(3112),

    // # 317x 消息推送相关
    // 3170 消息推送失败
    Message_Push_Error(3170),

    // # 318x 邮件相关
    // 3180 链接已过期,请重试
    Link_Expire(3180),

    // # 319x 其他验证相关
    // 3190 数据不存在，请重试
    Data_Not_Exist(3190),
    // 3191 提交数据有误,请重试
    Data_Error(3191),
    // 3192 提交数据重复,请重试
    Data_Exist_Try_Again(3192),
    // 3193 外部接口调用异常
    API_Call_Fail(3193),
    // 3194 重复操作
    Repetitive_Operation(3194),

    // # 32xx 相关
    INVALID_BANK_CARD(3200),

    // # 33xx 数据有误相关
    // 3300 输入的代码有误
    Invalid_Code(3300),
    // 3301 提交数据不完整
    Data_Not_Complete(3301),

    // # 34xx api相关
    // # 340x 账号相关
    // 3400 用户名或密码错误
    Invalid_Password(3400),
    // 3402 账号已激活
    Account_Activated(3402),
    // 3403 账号未激活
    Account_Inactive(3403),
    // 3404 账号已禁用
    Account_Disabled(3404),

    // # 341x 用户相关
    // 3410 账号不存在
    Account_Not_Exist(3410),
    // 3411 账号已存在
    Account_Exist(3411),

    // # 342x 机构相关
    // # 343x 成员相关

    // #35xx 文件相关
    // 3500 上传文件不能为空
    File_IsNull(3500),
    // 3501 上传文件太大
    File_Too_Large(3501),
    // 3502 上传文件失败
    File_Upload_Fail(3502),
    // 3503 图片格式不对
    Picture_Format_Error(3503),
    // 3504 系统找不到指定的文件
    File_Not_Found(3504),

    // # 36xx 微信相关
    // 3600 签名验证错误
    Validate_Signature_Error(3600),
    // 3601 xml解析失败
    Parse_Xml_Error(3601),
    // 3602 sha加密生成签名失败
    Compute_Signature_Error(3602),
    // 3603 SymmetricKey非法
    Illegal_AesKey(3603),
    // 3604 corpid校验失败
    Validate_Corpid_Error(3604),
    // 3605 aes加密失败
    Encrypt_AES_Error(3605),
    // 3606 aes解密失败
    Decrypt_AES_Error(3606),
    // 3607 解密后得到的buffer非法
    Illegal_Buffer(3607),

    // # 37xx 权限相关
    // 3700 无权限
    No_Permission(3700),

    // # 39xx 通用异常相关
    // 3900 操作失败
    Fail_Code(3900),

    // 3910 请求api非法
    API_Fail(3910),
    // 3920 请求action错误
    Action_Fail(3920),
    // 3930 请求参数错误
    Param_Error(3930),
    // 3940 提取码错误
    FetchCode_Error(3940),

    // 4xxx 需要用户重试
    ReLogin(4000),
    // 4100 token已过期，请重新登录
    Invalid_Token_ReLogin(4100),
    INVALID_CLIENT(4110),

    // 6xxx 版本管理
    VERSION_VALID(6000),
    VERSION_UPDATE(6100),
    VERSION_MUSET_UPDATE(6200),

    // 9xxx 系统错误*
    System_Error(9000);

    private Integer value;

    // 构造方法
    StatusCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public StatusCode setValue(Integer value) {
        this.value = value;
        return this;
    }
}
