Autor: Matheus Tabares Lopes

Aplicação Maven RESTFull com JDK8, JUnit, Hibernate/JPA, Spark Framework, Jetty, MySQL, JSON, AngularJS e Bootstrap.

Pré Requisitos:
	JDK 8
	MYSQL (em execução)
	
PASSOS: 
1°: Executar script de criação da Base de Dados, localizado na pasta Scripts/script-criacao-base-de-dados;
2°: Alterar value das tags user e passoword no arquivo DBCTeste/src/main/resources/META-INF/persistence.xml. 
	Conforme autenticação do MySQL na máquina do avaliador;
2°: Executar mvn clean install no projeto DBCTeste;
3°: Subir servidor através do comando Java Application;
4°: Acessar URL-http://'ipmaquina':4567/