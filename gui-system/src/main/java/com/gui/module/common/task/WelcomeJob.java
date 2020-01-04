package com.gui.module.common.task;

import com.gui.module.oa.domain.Response;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WelcomeJob implements Job{
	@Autowired
	SimpMessagingTemplate template;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	template.convertAndSend("/topic/getResponse", new Response("欢迎体验gui-system,这是一个任务计划，使用了websocket和quzrtz技术!" ));

    }

}