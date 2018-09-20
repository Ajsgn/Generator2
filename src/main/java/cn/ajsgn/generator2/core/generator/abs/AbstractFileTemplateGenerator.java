package cn.ajsgn.generator2.core.generator.abs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.ajsgn.generator2.util.FileUtil;

public abstract class AbstractFileTemplateGenerator implements FileTemplateGenerator, TemplateGenerator {
	
	protected abstract File targetFile();

	@Override
	public FileWriter targetFileWriter() {
		FileWriter fw = null;
		File file = targetFile();
		if(null!= file) {
			FileUtil.fileReady(file);
			try {
				fw = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fw;
	}
	
	
}
