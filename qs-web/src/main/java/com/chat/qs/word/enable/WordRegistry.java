package com.chat.qs.word.enable;

import com.chat.qs.word.WordHelper;
import com.chat.qs.word.qa.QuestionAnswerHelper;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by Administrator on 2020/4/15 0015.
 */
public class WordRegistry implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, GenerateConfig.class.getName(), GenerateConfig.class);
        WordHelper.getInstance().initWord();
        System.out.println("word中文拆词初始化成功!");
        //QuestionAnswerHelper.getInstance().initQuestionSystem();
        //System.out.println("问答系统初始化成功!");
    }

}
