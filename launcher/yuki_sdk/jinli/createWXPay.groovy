
class JinliExtension {
    
    //新建微信支付的java类
    Object run(ArrayList<String> params,Map<String,String> maps) {
        // do something
        def pluginID = params[0]
        def flavorName = params[1]
        def applicationID = params[2]

        println pluginID
        println flavorName
        println applicationID

        def applicationPath = applicationID.replaceAll("\\.","/")
        def targetPathStr = "src/$flavorName/java/$applicationPath/wxapi"
        def targetPath = new File(targetPathStr)
        if(!targetPath.exists()){
            println "创建目录[$targetPathStr]"
            targetPath.mkdirs()
        }
        def file = new File(targetPathStr+"/WXPayEntryActivity.java")
        if(!file.exists()){
            file.createNewFile()
        }
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw);
        pw.println("package ${applicationID}.wxapi;");
        pw.println("import com.gionee.gsp.cppayer.wechat.WechatPayerActivity;");
        pw.println("public class WXPayEntryActivity extends WechatPayerActivity{}");
        pw.flush()
        pw.close()
    }
 
}
