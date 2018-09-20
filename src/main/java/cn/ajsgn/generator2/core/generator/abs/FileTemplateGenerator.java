package cn.ajsgn.generator2.core.generator.abs;

import java.io.FileWriter;

public interface FileTemplateGenerator extends TemplateGenerator {
	
	public FileWriter targetFileWriter();
	
}
