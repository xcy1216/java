package com.utils;

import java.util.Random;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import java.util.Objects;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

@Component
public class CommonUtil {
	/**
     * 获取随机字符串
     *
     * @param num
     * @return
     */
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

	/**
	 * 获取随机验证码
	 *
	 * @param num
	 * @return
	 */
	public static String getRandomNumber(Integer num) {
	    String base = "0123456789";
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < num; i++) {
	        int number = random.nextInt(base.length());
	        sb.append(base.charAt(number));
	    }
	    return sb.toString();
	}

    public static String getCellValue(Cell cell) {
        String resultValue = "";
        // 判空
        if (Objects.isNull(cell)) {
            return resultValue;
        }

        // 拿到单元格类型
        int cellType = cell.getCellType();
        switch (cellType) {
            // 字符串类型
            case Cell.CELL_TYPE_STRING:
                resultValue = StringUtils.isEmpty(cell.getStringCellValue()) ? "" : cell.getStringCellValue().trim();
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                resultValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 数值类型
            case Cell.CELL_TYPE_NUMERIC:
                /**
                 * format 的值可能为以下这些 yyyyMMddHHmmss
                 * yyyy-MM-dd----- 14
                 * yyyy年m月d日----- 31
                 * yyyy年m月--------57
                 * m月d日  -----------58
                 * HH:mm-----------20
                 * h时mm分  --------- 32
                 */
                Object val = cell.getNumericCellValue();
                // POI Excel 日期格式转换
                String formatDate = "";
                switch (cell.getCellStyle().getDataFormat()){
                    case 14:
                        formatDate = "yyyy-MM-dd";
                        break;
                    case 20:
                        formatDate = "HH:mm";
                        break;
                    case 21:
                        formatDate = "HH:mm:ss";
                        break;
                    case 31:
                        formatDate = "yyyy年MM月dd日";
                        break;
                    case 32:
                        formatDate = "HH时mm分";
                        break;
                    case 33:
                        formatDate = "HH时mm分mm秒";
                        break;
                    case 57:
                        formatDate = "yyyy年MM月";
                        break;
                    case 58:
                        formatDate = "MM月dd日";
                        break;
                    case 176:
                        formatDate = "yyyy-MM-dd HH:mm:ss";
                        break;
                }
                if(!"".equals(formatDate)){
                    resultValue = new SimpleDateFormat(formatDate).format(DateUtil.getJavaDate((Double) val));
                }else{
                    resultValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            // 取空串
            default:
                break;
        }
        return resultValue;
    }


    public static void sendSMS(String phone,String key){
        //第二个参数为自己独有的accessKeyid，第三个参数为自己独有的accessKeySecret,请使用自己的accessKeyid和accessKeySecret
        DefaultProfile profile = DefaultProfile.getProfile("cn-shenzhen", "LTAI5tPMAnSChoyMo96WR86S","dT9wsVq5SmsMqaVw5RQ2QCOqmHVZsG");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();//组装请求对象
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);//设置post提交
        request.setDomain("dysmsapi.aliyuncs.com");//短信API产品域名（接口地址固定，无需修改）
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shenzhen");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "阿里云短信测试");//短信签名,如果是使用阿里云的测试模板，不用改动
        request.putQueryParameter("TemplateCode", "SMS_154950909");//模板Code,如果是使用阿里云的测试模板，不用改动

        /**
         * 防止如果随机生成的第一位是0，那么发到手机上就会变成5位，0会被干掉的情况
         */
        HashMap<String,String> map=new HashMap<>();
        map.put("code",key);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(map));
        request.putQueryParameter("TemplateParam", jsonObject.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            /*{"Message":"OK","RequestId":"848FA43B-1241-49B1-9A00-DFF6F837B769","BizId":"154917588732416355^0","Code":"OK"}*/
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }



}
