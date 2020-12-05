## upload
```cmd
mvn clean deploy -Dmaven.test.skip=true

mvn clean deploy -P release

cls & D:\Deposit\000\0-Code\apache-maven-3.5.0\bin\mvn clean deploy -Dmaven.test.skip=true -P release

# install
cls & D:\Deposit\000\0-Code\apache-maven-3.5.0\bin\mvn clean install -Dmaven.test.skip=true -P release

# 导出 pom 树
mvn dependency:tree> 1.txt
```

## explorer
https://github.com/adarryring/adarryring.github.io
https://repo.maven.apache.org/maven2
https://mvnrepository.com/
https://oss.sonatype.org/#stagingRepositories
http://search.maven.org
