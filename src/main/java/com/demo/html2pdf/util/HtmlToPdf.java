package com.demo.html2pdf.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class HtmlToPdf {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     *
     * @param wkhtmltopdfPath       wkhtmltopdf.exe路径
     * @param myoutlinePaht         目录路径
     * @param srcPath               生成PDF地址或者路径
     * @param destPath              生成本地的路径
     * @param coverUrl              coverUrl
     * @param footerUrl             footerUrl
     * @return
     */
    public boolean convert(String wkhtmltopdfPath, String myoutlinePaht, String srcPath, String destPath, String coverUrl,String footerUrl){

        File file = new File(destPath);
        File parent = file.getParentFile();
        //如果pdf保存路径不存在，则创建路径
        if(!parent.exists()){
            parent.mkdirs();
        }
        StringBuilder cmd = new StringBuilder();
        cmd.append(wkhtmltopdfPath);
        cmd.append(" ");
//        cmd.append(" --header-line");//页眉下面的线
//
////cmd.append(" --header-center 这里是页眉这里是页眉这里是页眉这里是页眉 ");//页眉中间内容
//
//        cmd.append(" --margin-top 3cm ");//设置页面上边距 (default 10mm) 
//
////        cmd.append(" --header-html ");// (添加一个HTML页眉,后面是网址)
//
//        cmd.append(" --header-spacing 5 ");// (设置页眉和内容的距离,默认0)
//
////cmd.append(" --footer-center (设置在中心位置的页脚内容)");//设置在中心位置的页脚内容
//
////        cmd.append(" --footer-html ");// (添加一个HTML页脚,后面是网址)
//
//        cmd.append(" --footer-line");//* 显示一条线在页脚内容上)
//
//        cmd.append(" --footer-spacing 5 ");// (设置页脚和内容的距离)


        cmd.append(" --disable-smart-shrinking --enable-local-file-access -B 17 -T 0 -L 0 -R 0 -d 192 ");
        cmd.append(" --page-offset -1 ");
        cmd.append(" --footer-html ").append(footerUrl).append(" ");
        cmd.append(" --footer-spacing 1");

        cmd.append(" --footer-center \"page\" ");
        cmd.append(" cover ");
        cmd.append(coverUrl);
        cmd.append(" toc --xsl-style-sheet ").append(myoutlinePaht).append(" ");
        cmd.append(" --javascript-delay 1000 ");
        cmd.append(" --encoding utf-8 ");


//        cmd.append(" --disable-smart-shrinking --enable-local-file-access -B 15 -T 15 -L 0 -R 0 ");
//
//        cmd.append(" --disable-smart-shrinking ");
//        cmd.append(" cover ");
//        cmd.append(coverUrl);
//        cmd.append(" toc --xsl-style-sheet ").append(myoutlinePaht).append(" ");;
//        cmd.append(" --javascript-delay 1000 ");
//        cmd.append(" --encoding utf-8 ");
//        cmd.append(" --debug-javascript  ");

        cmd.append(srcPath);

        cmd.append(" ");

        cmd.append(destPath);
        log.info("生成命令："+cmd.toString());
        boolean result = true;
        try{
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        }catch(Exception e){
            result = false;
            e.printStackTrace();
        }

        return result;
    }

//    wkhtmltopdf [OPTIONS]... <input file> [More input files] <output file>
//    常规选项
//         --allow <path>  允许加载从指定的文件夹中的文件或文件（可重复）
//                --book*  设置一会打印一本书的时候，通常设置的选项
//         --collate  打印多份副本时整理
//         --cookie <name> <value>  设置一个额外的cookie（可重复）
//                --cookie-jar <path>  读取和写入的Cookie，并在提供的cookie jar文件
//         --copies <number>  复印打印成pdf文件数（默认为1）
//                --cover* <url>  使用HTML文件作为封面。它会带页眉和页脚的TOC之前插入
//         --custom-header <name> <value>  设置一个附加的HTTP头（可重复）
//                --debug-javascript  显示的javascript调试输出
//        --default-header*  添加一个缺省的头部，与页面的左边的名称，页面数到右边，例如： --header-left '[webpage]' --header-right '[page]/[toPage]'  --header-line
//         --disable-external-links*  禁止生成链接到远程网页
//         --disable-internal-links*  禁止使用本地链接
//        --disable-javascript  禁止让网页执行JavaScript
//         --disable-pdf-compression*  禁止在PDF对象使用无损压缩
//         --disable-smart-shrinking*  禁止使用WebKit的智能战略收缩，使像素/ DPI比没有不变
//         --disallow-local-file-access  禁止允许转换的本地文件读取其他本地文件，除非explecitily允许用 --allow
//        --dpi <dpi>  显式更改DPI（这对基于X11的系统没有任何影响）
//                --enable-plugins  启用已安装的插件（如Flash
//         --encoding <encoding>  设置默认的文字编码
//         --extended-help  显示更广泛的帮助，详细介绍了不常见的命令开关
//         --forms*  打开HTML表单字段转换为PDF表单域
//        --grayscale  PDF格式将在灰阶产生
//        --help  Display help
//         --htmldoc  输出程序HTML帮助
//         --ignore-load-errors  忽略claimes加载过程中已经遇到了一个错误页面
//        --lowquality  产生低品质的PDF/ PS。有用缩小结果文档的空间
//         --manpage  输出程序手册页
//        --margin-bottom <unitreal>  设置页面下边距 (default 10mm)
//        --margin-left <unitreal>  将左边页边距 (default 10mm)
//        --margin-right <unitreal>  设置页面右边距 (default 10mm)
//        --margin-top <unitreal>  设置页面上边距 (default 10mm)
//         --minimum-font-size <int>  最小字体大小 (default 5)
//         --no-background  不打印背景
//        --orientation <orientation>  设置方向为横向或纵向
//         --page-height <unitreal>  页面高度 (default unit millimeter)
//         --page-offset* <offset>  设置起始页码 (default 1)
//        --page-size <size>  设置纸张大小: A4, Letter, etc.
//        --page-width <unitreal>  页面宽度 (default unit millimeter)
//         --password <password>  HTTP验证密码
//         --post <name> <value>  Add an additional post field (repeatable)
//         --post-file <name> <path>  Post an aditional file (repeatable)
//         --print-media-type*  使用的打印介质类型，而不是屏幕
//        --proxy <proxy>  使用代理
//        --quiet  Be less verbose
//        --read-args-from-stdin  读取标准输入的命令行参数
//        --readme  输出程序自述
//        --redirect-delay <msec>  等待几毫秒为JS-重定向(default 200)
//        --replace* <name> <value>  替换名称,值的页眉和页脚（可重复）
//                --stop-slow-scripts  停止运行缓慢的JavaScripts
//        --title <text>  生成的PDF文件的标题（第一个文档的标题使用，如果没有指定）
//                --toc*  插入的内容的表中的文件的开头
//        --use-xserver*  使用X服务器（一些插件和其他的东西没有X11可能无法正常工作）
//                --user-style-sheet <url>  指定用户的样式表，加载在每一页中
//        --username <username>  HTTP认证的用户名
//        --version  输出版本信息退出
//         --zoom <float>  使用这个缩放因子 (default 1)
//
//    页眉和页脚选项
//        --header-center*    <text>    (设置在中心位置的页眉内容)
//        --header-font-name* <name>    (default Arial)  (设置页眉的字体名称)
//        --header-font-size* <size>    (设置页眉的字体大小)
//        --header-html*  <url> (添加一个HTML页眉,后面是网址)
//        --header-left*  <text>   (左对齐的页眉文本)
//        --header-line*      (显示一条线在页眉下)
//        --header-right* <text>    (右对齐页眉文本)
//        --header-spacing*   <real>    (设置页眉和内容的距离,默认0)
//        --footer-center*    <text>    (设置在中心位置的页脚内容)
//        --footer-font-name* <name>    (设置页脚的字体名称)
//        --footer-font-size* <size>    (设置页脚的字体大小default 11)
//        --footer-html*  <url> (添加一个HTML页脚,后面是网址)
//        --footer-left*  <text>    (左对齐的页脚文本)
//        --footer-line*      显示一条线在页脚内容上)
//        --footer-right* <text>    (右对齐页脚文本)
//        --footer-spacing*   <real>    (设置页脚和内容的距离)
//        ./wkhtmltopdf --footer-right '[page]/[topage]' http://www.baidu.com baidu.pdf
//        ./wkhtmltopdf --header-center '报表' --header-line --margin-top 2cm --header-line http://192.168.212.139/oma/  oma.pdf
//    表内容选项中
//         --toc-depth* <level>  Set the depth of the toc (default 3)
//         --toc-disable-back-links*  Do not link from section header to toc
//         --toc-disable-links*  Do not link from toc to sections
//         --toc-font-name* <name>  Set the font used for the toc (default Arial)
//         --toc-header-font-name* <name>  The font of the toc header (if unset use --toc-font-name)
//         --toc-header-font-size* <size>  The font size of the toc header (default 15)
//         --toc-header-text* <text>  The header text of the toc (default Table Of Contents)
//         --toc-l1-font-size* <size>  Set the font size on level 1 of the toc (default 12)
//         --toc-l1-indentation* <num>  Set indentation on level 1 of the toc (default 0)
//         --toc-l2-font-size* <size>  Set the font size on level 2 of the toc (default 10)
//         --toc-l2-indentation* <num>  Set indentation on level 2 of the toc (default 20)
//         --toc-l3-font-size* <size>  Set the font size on level 3 of the toc (default 8)
//         --toc-l3-indentation* <num>  Set indentation on level 3 of the toc (default 40)
//         --toc-l4-font-size* <size>  Set the font size on level 4 of the toc (default 6)
//         --toc-l4-indentation* <num>  Set indentation on level 4 of the toc (default 60)
//         --toc-l5-font-size* <size>  Set the font size on level 5 of the toc (default 4)
//         --toc-l5-indentation* <num>  Set indentation on level 5 of the toc (default 80)
//         --toc-l6-font-size* <size>  Set the font size on level 6 of the toc (default 2)
//         --toc-l6-indentation* <num>  Set indentation on level 6 of the toc (default 100)
//         --toc-l7-font-size* <size>  Set the font size on level 7 of the toc (default 0)
//         --toc-l7-indentation* <num>  Set indentation on level 7 of the toc (default 120)
//         --toc-no-dots*  Do not use dots, in the toc
//            轮廓选项
//         --dump-outline <file>  转储目录到一个文件
//         --outline  显示目录(文章中h1,h2来定)
//         --outline-depth <level>  设置目录的深度（默认为4）
//            页脚和页眉
//         * [page]       由当前正在打印的页的数目代替
//         * [frompage]   由要打印的第一页的数量取代
//         * [topage]     由最后一页要打印的数量取代
//         * [webpage]    通过正在打印的页面的URL替换
//         * [section]    由当前节的名称替换
//         * [subsection] 由当前小节的名称替换
//         * [date]       由当前日期系统的本地格式取代
//         * [time]       由当前时间，系统的本地格式取代
}
