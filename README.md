# sql2excel-server
A simple spring-boot web application provides several rest-apis to acquire data from a database by sql statement and generate excel files.

## project structure
```
|- sql2excel
    |- src
        |- main
            |- bin          starup scripts
            |- java         java source code
            |- resources    java resource files
    |- assembly.xml         assembly descicription file
    |- pom.xml              maven project description file
```

## package structure
```
|- sql2excel-server
    |- lib          libruaries
    |- startup.bat  startup script for windows
    |- startup.sh   startup script for unix/linux/macos
```

## installation
### clone source code
```
git clone 'https://github.com/johnsonmoon/sql2excel.git'
```

### build source code
```
cd sql2excel
mvn clean package -Dmaven.test.skip=true
```

### upload tar.gz package to your host machine
```
scp xxx.tar.gz ${user}@${IP}:${directory}
```

### extract tar.gz package
```
tar -xzf xxx.tar.gz
```

### start server (jre required, >= 1.7)
```
cd sql2excel-server
./startup.sh
```


## change server port
### stop server, modify startup script, like this:
```
kill -9 ${PID}

vi startup.sh
```

### add vm option like this:
```
java -Dserver.port=${port} -cp ${CLASSPATH} xxx.xxApplication
```

## usage
using web browser to visit server
```
http://${IP}:${port}/sql2excel
```

finally you can see this:  

![](visit-example.jpg)