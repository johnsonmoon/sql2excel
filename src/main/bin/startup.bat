java -Xmx512m -Xms256m -Xss256K -XX:MaxMetaspaceSize=256m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+ExplicitGCInvokesConcurrent -cp .\lib\*;%CLASSPATH% com.johnsonmoon.sql2excel.Sql2excelApplication