O Maven permite criar a estrutura de diretórios e um pom.xml mínimo pelo terminal,
conforme visto em: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

Exemplo:
   mvn archetype:generate -DgroupId=br.edu.ifpb -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

Para limpar a estrutura de diretórios: mvn clean

Para compilar: $ mvn compile

Para executar a classe:
    [my-app]$ java -cp target/classes br.edu.ifpb.App

Para gerar o jar: $ mvn package
Para executar o jar:
    [my-app]$ java -jar target/my-app-1.0-SNAPSHOT.jar

Veja a lista de plugins e suas versões disponíveis: https://maven.apache.org/plugins/