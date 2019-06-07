https://www.cnblogs.com/Uzai/p/10249837.html
想要通过java官网提供的java-1.0.2.jar 来解决音频文件切割
资源下载地址http://www.sauronsoftware.it/projects/jave/download.php 

这个jar是maven仓库里面没有的 ， 导入本地仓库步骤
1.将java-1.0.2.jar 复制到C:\Users\Administrator路径下， 不同pc路径可能不一样， 总而言之是要在用户路径下
2.进入这个路径
3.mvn install:install-file -Dfile=jave-1.0.2.jar -DgroupId=joinery -DartifactId=jave -Dversion=1.0.2 -Dpackaging=jar