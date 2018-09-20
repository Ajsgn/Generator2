package cn.ajsgn.generator2.core.generator.abs;

import java.util.Map;

public interface TemplateGenerator {
	
	public abstract Map<String, Object> targetParam();
	
	public abstract String templateResource();
	
}
