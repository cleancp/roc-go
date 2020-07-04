package com.today.roc.go.common.utils;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 17:30*
 * log.info()
 */

/**
 *  *程序功能：支持将仟亿数字转换成中文数字表示，如(110.12 转换成壹佰壹拾元壹角贰分)
 *  *算法思路:将数字，分成整数部分和小数部分(小数部分四舍五入到两位)，分别进行转换，然后将转换后的结果合并后，生成最终结果 转换过程关键怎么处理中间的零，以及如何取单位。
 */

public class ConverToChinesePart {

    //需转换的数字
    private double number;

    //中文数字
    private String[] source1 = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    //数字单位
    private String[] source2 = {"仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", "元", "角", "分"};

    //构造函数
    public ConverToChinesePart(double number) {
        this.number = number;
    }

    //转换整数部分
    private String integerToChinese() {

        //整数部分
        long integerPart = (long) number;

        //将整数部分转换成字符串
        String integerStr = Long.toString(integerPart);

        //取得整数部分的长度
        int intPartLen = integerStr.length();

        //将整数部分转换成字符数组
        char[] intChars = integerStr.toCharArray();

        //当前字符是否为零的标志变量
        boolean bitState = false;

        //转换结果
        String convertResult = null;

        //当整数部分为0时
        if (intChars[0] == '0') {

            return "";

        }

        //当整数部分的长度为1时
        if (intPartLen == 1) {

            convertResult = source1[intChars[0] - 48] + source2[11];

            return convertResult;

        } else {
            //char类型不能直接转换成int，由于数字char类型，与整型正好相差48，则能通过char-48转换成整型。
            //循环处理每一个数字，index用于intChars定位，position用于source2定位
            for (int index = 0, position = source2.length - intPartLen - 2; index < intChars.length; index++, position++) {
                //判断当前数字是否为零，将设置bitState,为零时为true,否则false
                if (intChars[index] == '0') {
                    bitState = true;
                } else {
                    bitState = false;
                }
                //处理第一个数
                if (index == 0) {
                    convertResult = source1[intChars[0] - 48] + source2[position];
                    //处理最后一个数字，当最后一个数字为零时
                } else if (index == intChars.length - 1 && intChars[index] == '0') {
                    //将已有的转换结果，转换成字符数组并判断最后一个字符是否零，
                    //如果是零，则去掉最后一个零，加上单位元
                    char[] temporaryChars = convertResult.toCharArray();
                    if (temporaryChars[temporaryChars.length - 1] == '零') {
                        convertResult = convertResult.substring(0, convertResult.length() - 1);
                    }
                    convertResult += "元";
                    break;
                    //处理最后一个数字，当最后一个数字不为零时
                } else if (index == intChars.length - 1 && intChars[index] != '0') {
                    //直接取中文数字，加上单位元
                    convertResult += source1[intChars[index] - 48] + "元";
                    break;
                    //处理中间数字,这部分是转换最难的部分
                    //先分别处理在亿、万位时如何转换
                } else {
                    //当前处于亿位时，并且当前字符是0时
                    if (source2[position] == "亿" && bitState) {
                        //将已有的转换结果，转换成字符数组并判断最后一个字符是否零，
                        //如果是零，则去掉最后一个零，加上单位亿
                        char[] temporaryChars = convertResult.toCharArray();
                        if (temporaryChars[temporaryChars.length - 1] == '零') {
                            convertResult = convertResult.substring(0, convertResult.length() - 1);
                            continue;
                        }
                        convertResult += "亿";
                        //当前处于亿位时，并且当前字符不是0时
                    } else if (source2[position] == "亿" && !bitState) {
                        //直接取中文数字，加上单位元
                        convertResult += source1[intChars[index] - 48] + source2[position];
                        //当前处于万位时，并且当前字符是0时
                    } else if (source2[position] == "万" && bitState) {
                        //将已有的转换结果，转换成字符数组并判断最后一个字符是否零，
                        //如果是零，则去掉最后一个零，并且去掉零，如果最后一个字符是亿，则不加万单位否则要加
                        char[] temporaryChars = convertResult.toCharArray();
                        if (temporaryChars[temporaryChars.length - 1] == '零') {
                            convertResult = convertResult.substring(0, convertResult.length() - 1);
                            if (convertResult.toCharArray()[convertResult.length() - 1] == '亿') {
                                continue;
                            }
                        }
                        convertResult += "万";

                        //当前处于万位时，并且当前字符不是0时
                    } else if (source2[position] == "万" && !bitState) {
                        //直接取中文数字，加上单位元
                        convertResult += source1[intChars[index] - 48] + source2[position];

                    } else //处理不在亿、万位的数字转换
                    {
                        //当前字符不是0时
                        if (!bitState) {
                            //直接取中文数字，根据当前位置取数字单位
                            convertResult += source1[intChars[index] - 48] + source2[position];

                            continue;

                        } else {
                            //当前字符是0时
                            //将已有的转换结果，转换成字符数组并判断最后一个字符是否零，
                            // 如果是零，不转换当前字符，如果不是零时，进行转换不带数字单位
                            char[] temporaryChars = convertResult.toCharArray();
                            if (temporaryChars[temporaryChars.length - 1] == '零') {
                                continue;

                            }
                            convertResult += source1[0];

                        }

                    }

                }

            }
            //返回最终整数部分转换结果
            return convertResult;

        }

    }

    /**
     * 算法：1.通过long强制转换，取得整数部分integerPart
     * <p>
     *   2.通过number-integerPart取得小数部分decimalPart，decimalPart * 100用于保留两位
     * <p>
     *  小数，由于计算处理double精度问题，用math.round函数圆整。小数部分可以出0，1,12,50
     */

    //转换小数部分
    private String decimalToChinese() {
        //小数部分
        int decimalPart = (int) Math.round((number - (long) number) * 100);
        //将小数部分转换成字符串.
        String decimalStr = Integer.toString(decimalPart);
        //转换结果
        String result;
        //将小数部分转换成字符数组
        char[] decimalChars = decimalStr.toCharArray();
        //取得char[]长度
        int charLen = decimalChars.length;
        //当charLen只有1位时，且当前char为0直接返回空字符串
        if (charLen == 1 && decimalChars[0] == '0') {
            return "";
            //当charLen只有1位时，且当前char不为0，转换成零加当前字符的中文数字
        } else if (charLen == 1 && decimalChars[0] != '0') {
            result = "零" + source1[decimalChars[0] - 48] + source2[13];
            //当charLen为2位时，且最后一位字符为0
        } else if (decimalChars[1] != '0') {
            result = source1[decimalChars[0] - 48] + source2[12] + source1[decimalChars[1] - 48] + source2[13];
            //除上述条件，其它情况的转换
        } else {
            result = source1[decimalChars[0] - 48] + source2[12];
        }
        return result;
    }


    /**
     * 转换方法
     *
     * @return String
     */

    //外部接口
    public String convertToChinese() {
        return integerToChinese() + decimalToChinese();
    }
    //程序入口
    public static void main(String[] args) {
        ConverToChinesePart conver = new ConverToChinesePart(1010101010.12);
        System.out.println(conver.convertToChinese());
    }

}
