package br.com.spring.batch.treinamento.springbatch.transformers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

import br.com.spring.batch.treinamento.springbatch.domain.Autobot;


public class AutobotItemProcessor implements ItemProcessor<Autobot, Autobot> {
	
	private static final Logger logger = LoggerFactory.getLogger(AutobotItemProcessor.class);

	@Override
	public Autobot process(Autobot autobot) throws Exception {
		String nome = autobot.getNome().toUpperCase();
		String carro = autobot.getCarro().toUpperCase();
		
		final Autobot transformed = new Autobot(nome, carro);
		logger.info("Converting (" + autobot + ") into (" + transformed + ")");
		
		return transformed;
	}

}
