
class HuaweiExtension {

    Object run(ArrayList<String> params,Map<String,String> maps) {
        // do something
        def pluginID = params[0]
        def flavorName = params[1]
        def applicationID = params[2]
        def proj = params[3]
        def root = proj.getProjectDir().absolutePath
        println "********************************************************* "
        println "检查包名结尾 - " + (applicationID.endsWith(".huawei") || applicationID.endsWith(".HUAWEI"))
        if(!applicationID.endsWith(".huawei") && !applicationID.endsWith(".HUAWEI"))
        {
            throw new Exception("${flavorName} - ${applicationID} 包名未用 .huawei 结尾")
        }

        def targetFile = new File(root+"/launcher/src/$flavorName/agconnect-services.json")
        def targetFile2 = new File(root+"/src/$flavorName/agconnect-services.json")

        println "检查json文件Path1: " + targetFile.getPath()
        println "检查json文件Path2: " + targetFile2.getPath()
        println "检查json文件 1_ "+ targetFile.exists() + " 2_ " + targetFile2.exists()
        if(!targetFile.exists() && !targetFile2.exists()){
            throw new Exception("${flavorName} 缺少华为配置文件agconnect-services")
        }

        println "********************************************************* "

    }

}
