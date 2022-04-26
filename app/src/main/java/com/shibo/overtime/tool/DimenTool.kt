package com.shibo.overtime.tool

import java.io.*

object DimenTool {

    fun gen() {
        //以此文件夹下的dimens.xml文件内容为初始值参照
        val file = File("./app/src/main/res/values/dimens.xml")
        var reader: BufferedReader? = null
        val sw240 = StringBuilder()
        val sw320 = StringBuilder()
        val sw360 = StringBuilder()
        val sw480 = StringBuilder()
        val sw600 = StringBuilder()
        val sw720 = StringBuilder()
        val sw800 = StringBuilder()
        try {
            println("生成不同分辨率：")
            reader = BufferedReader(FileReader(file))
            var tempString: String
            var line = 1

            // 一次读入一行，直到读入null为文件结束
            while (reader.readLine().also { tempString = it } != null) {
                if (tempString.contains("</dimen>")) {

                    //tempString = tempString.replaceAll(" ", "");
                    val start = tempString.substring(0, tempString.indexOf(">") + 1)
                    val end = tempString.substring(tempString.lastIndexOf("<") - 2)
                    //截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字
                    val num = tempString.substring(
                        tempString.indexOf(">") + 1,
                        tempString.indexOf("</dimen>") - 2
                    ).toDouble()

                    //根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行。
                    sw240.append(start).append(num * 0.75).append(end).append("\r\n")
                    sw320.append(start).append(num * 1).append(end).append("\r\n")
                    sw360.append(start).append(num * 1.125).append(end).append("\r\n")
                    sw480.append(start).append(num * 1.5).append(end).append("\r\n")
                    sw600.append(start).append(num * 1.87).append(end).append("\r\n")
                    sw720.append(start).append(num * 2.25).append(end).append("\r\n")
                    sw800.append(start).append(num * 2.5).append(end).append("\r\n")
                } else {
                    sw240.append(tempString).append("")
                    sw320.append(tempString).append("")
                    sw360.append(tempString).append("")
                    sw480.append(tempString).append("")
                    sw600.append(tempString).append("")
                    sw720.append(tempString).append("")
                    sw800.append(tempString).append("")
                }
                line++
            }
            reader.close()
            println("<!--  sw240 -->")
            println(sw240)
            println("<!--  sw320 -->")
            println(sw320)
            println("<!--  sw360 -->")
            println(sw360)
            println("<!--  sw480 -->")
            println(sw480)
            println("<!--  sw600 -->")
            println(sw600)
            println("<!--  sw720 -->")
            println(sw720)
            println("<!--  sw800 -->")
            println(sw800)
            val sw240file = "./app/src/main/res/values-sw240dp/dimens.xml"
            val sw320file = "./app/src/main/res/values-sw320dp/dimens.xml"
            val sw360file = "./app/src/main/res/values-sw360dp/dimens.xml"
            val sw480file = "./app/src/main/res/values-sw480dp/dimens.xml"
            val sw600file = "./app/src/main/res/values-sw600dp/dimens.xml"
            val sw720file = "./app/src/main/res/values-sw720dp/dimens.xml"
            val sw800file = "./app/src/main/res/values-sw800dp/dimens.xml"
            val w820file = "./app/src/main/res/values-w820dp/dimens.xml"
            //将新的内容，写入到指定的文件中去
            writeFile(sw240file, sw240.toString())
            writeFile(sw320file, sw320.toString())
            writeFile(sw360file, sw360.toString())
            writeFile(sw480file, sw480.toString())
            writeFile(sw600file, sw600.toString())
            writeFile(sw720file, sw720.toString())
            writeFile(sw800file, sw800.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
            }
        }
    }

    /**
     * 写入方法
     *
     */
    fun writeFile(file: String?, text: String?) {
        var out: PrintWriter? = null
        try {
            out = PrintWriter(BufferedWriter(FileWriter(file)))
            out.println(text)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        out?.close()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        gen()
    }
}