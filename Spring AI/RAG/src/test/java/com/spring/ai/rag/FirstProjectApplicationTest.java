package com.spring.ai.rag;

import com.spring.ai.rag.helper.Helper;
import com.spring.ai.rag.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FirstProjectApplicationTest {

	@Autowired
	ChatService chatService;

	@Test
	void saveDataToVectorDataBase(){
		log.info("saving data dump...");
		chatService.saveDataDump(Helper.getData());
		log.info("saved data dump");
	}

}
