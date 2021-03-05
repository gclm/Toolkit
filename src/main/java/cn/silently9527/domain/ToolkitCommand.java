package cn.silently9527.domain;

import cn.silently9527.domain.executor.Base64DecodeToolkitCommandExecutor;
import cn.silently9527.domain.executor.Base64EncodeToolkitCommandExecutor;
import cn.silently9527.domain.executor.DateToolkitCommandExecutor;
import cn.silently9527.domain.executor.IpToolkitCommandExecutor;
import cn.silently9527.domain.executor.JsonToolkitCommandExecutor;
import cn.silently9527.domain.executor.Md5ToolkitCommandExecutor;
import cn.silently9527.domain.executor.PhoneToolkitCommandExecutor;
import cn.silently9527.domain.executor.QrcodeEncodeToolkitCommandExecutor;
import cn.silently9527.domain.executor.RegularToolkitCommandExecutor;
import cn.silently9527.domain.executor.Sql2DslToolkitCommandExecutor;
import cn.silently9527.domain.executor.TimestampToolkitCommandExecutor;
import cn.silently9527.domain.executor.ToolkitCommandExecutor;
import cn.silently9527.domain.executor.UrlDecodeToolkitCommandExecutor;
import cn.silently9527.domain.executor.UrlEncodeToolkitCommandExecutor;
import cn.silently9527.domain.executor.UuidToolkitCommandExecutor;

public enum ToolkitCommand {
    Date("date", "日期转时间戳", DateToolkitCommandExecutor.class),
    Timestamp("timestamp", "时间戳转日期", TimestampToolkitCommandExecutor.class),
    Json("json", "JSON格式化", JsonToolkitCommandExecutor.class),
    Sql2dsl("sql2dsl", "SQL转elasticSearch语句", Sql2DslToolkitCommandExecutor.class),
    URLEncode("url encode", "URL编码", UrlEncodeToolkitCommandExecutor.class),
    URLDecode("url decode", "URL解码", UrlDecodeToolkitCommandExecutor.class),
    UUID("uuid", "UUID随机唯一值", UuidToolkitCommandExecutor.class),
    MD5("md5", "md5加密", Md5ToolkitCommandExecutor.class),
    Base64Encode("base64 encode", "base64编码", Base64EncodeToolkitCommandExecutor.class),
    Base64Decode("base64 decode", "base64解码", Base64DecodeToolkitCommandExecutor.class),
    Phone("phone", "手机号归属地", PhoneToolkitCommandExecutor.class),
    Regular("regular", "正则表达式", RegularToolkitCommandExecutor.class),
    QRCodeEncode("qrcode encode", "生成二维码", QrcodeEncodeToolkitCommandExecutor.class),
    IP("ip", "IP归属地", IpToolkitCommandExecutor.class);

    private String command;
    private String description;
    private Class<? extends ToolkitCommandExecutor> executor;

    ToolkitCommand(String command, String description, Class<? extends ToolkitCommandExecutor> executor) {
        this.command = command;
        this.description = description;
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends ToolkitCommandExecutor> getExecutor() {
        return executor;
    }
}