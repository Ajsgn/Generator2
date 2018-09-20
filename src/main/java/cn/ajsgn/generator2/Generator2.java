package cn.ajsgn.generator2;

import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.core.strategy.impl.DbGeneratorStrategy;
import cn.ajsgn.generator2.core.strategy.impl.DbGeneratorStrategy.Builder;
import cn.ajsgn.generator2.db.names.DefaultPackageNameCreator;
import cn.ajsgn.generator2.vm.VolecityInstance;

public class Generator2 {
	
	private static final VolecityInstance INSTANCE = VolecityInstance.init();
	
	public static final void generator(GeneratorStrategy strategy) {
		if(null != strategy) 
			strategy.generator(INSTANCE);
	}
	
	public static void main(String[] args) {
		
		Builder builder = new Builder("bcpay", "bcpay", "jdbc:oracle:thin:@192.168.0.161:1521:orcl", "oracle.jdbc.driver.OracleDriver");
		DbGeneratorStrategy dbGeneratorStrategy = builder.build();
//		dbGeneratorStrategy.setBasePackage("cn.ajsgn.generator.test", DefaultPackageNameCreator.singletonInstance());
		generator(dbGeneratorStrategy);
		
	}

}
