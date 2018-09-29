package cn.ajsgn.generator2;

import cn.ajsgn.generator2.core.strategy.impl.MavenProjectGeneratorStrategy;

public class MavenProjectGenerator2Test {
	
	public static void main(String[] args) {
		
		MavenProjectGeneratorStrategy mavenProject = new MavenProjectGeneratorStrategy.Builder("bc-project-test", "cn/ajsgn/generator2/template/maven/blankPom.xml.vm")//
				.baseOutFolderPath("d:/generator2/project")	//
				.artifactId("bc.project.test")	//
				.groupId("bc.project.test")	//
				.encode("UTF-8")	//
				.name("generator project for testing")	//
				.packaging(MavenProjectGeneratorStrategy.MavenPackagingType.WAR)	//
				.url("http://www.apache.org")	//
				.version("0.0.1-SNATSHOT")	//
				.withSrcFolder(true)	//
				.build();
		
		
		Generator2.generator(mavenProject);
		
		
		
	}
	
	
	
}
