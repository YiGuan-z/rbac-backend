
build:
	mvnd package
tomcat@run:
	export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-19.jdk/Contents/Home"
	mvnd tomcat7:run -f pom.xml