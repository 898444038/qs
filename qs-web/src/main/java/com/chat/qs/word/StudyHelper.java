package com.chat.qs.word;

import com.chat.qs.config.redis.RedissonService;
import com.chat.qs.constant.RedisConstant;
import com.chat.qs.entity.Answer;
import com.chat.qs.entity.City;
import com.chat.qs.entity.Question;
import com.chat.qs.service.AnswerService;
import com.chat.qs.service.QuestionService;
import com.chat.qs.utils.SocketMessage;
import com.chat.qs.utils.SpringBeanUtil;
import com.chat.qs.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/4/27 0027.
 */
public class StudyHelper {

    private volatile static StudyHelper instance = null;

    private StudyHelper(){}

    public static StudyHelper getInstance()   {
        if (instance == null)  {
            synchronized (StudyHelper.class) {
                if (instance== null)  {
                    instance= new StudyHelper();
                }
            }
        }
        return instance;
    }

    public SocketMessage study(SocketMessage receiveMessage) {
        if (receiveMessage.getId() == null){
            return SocketMessage.study(1,"","已进入学习模式，请输入要学习的问题：",2);
        }else{
            RedissonService redissonService = SpringBeanUtil.getBean(RedissonService.class);
            List<Question> questionList = redissonService.getRBucket(RedisConstant.QUESTION_STUDY_LIST_KEY);
            if(questionList==null || questionList.isEmpty()){
                questionList = new ArrayList<>();
                Question question = new Question();
                question.setId(receiveMessage.getId().longValue());
                question.setTitle(receiveMessage.getData().toString());
                questionList.add(question);
                redissonService.setRBucket(RedisConstant.QUESTION_STUDY_LIST_KEY,questionList);
                return SocketMessage.study(receiveMessage.getId(),"","请输入该问题的说明",2);
            }else{
                Question question = null;
                Long id = 0L;
                for(Question q : questionList){
                    if(q.getId().equals(receiveMessage.getId().longValue())){
                        question = q;
                    }
                    if(q.getId()>id){
                        id = q.getId();
                    }
                }
                id++;
                if(question == null){
                    question = new Question();
                    question.setId(id);
                    question.setTitle(receiveMessage.getData().toString());
                    questionList.add(question);
                    redissonService.setRBucket(RedisConstant.QUESTION_STUDY_LIST_KEY,questionList);
                    return SocketMessage.study(id.intValue(),"","请输入该问题的说明",2);
                }else{
                    if(StringUtils.isBlank(question.getTitle())){
                        return SocketMessage.study(question.getId().intValue(),"","请输入要学习的问题",2);
                    }
                    List<Answer> answerList = question.getAnswerList();
                    if(answerList == null || answerList.isEmpty()){
                        Answer answer = new Answer();
                        answer.setContent(receiveMessage.getData().toString());

                        //入库
                        QuestionService questionService = SpringBeanUtil.getBean(QuestionService.class);
                        AnswerService answerService = SpringBeanUtil.getBean(AnswerService.class);
                        question.setType(0);
                        question.setDelFlag(false);
                        questionService.insert(question);
                        answer.setQuestionId(question.getId());
                        answer.setDelFlag(false);
                        answerService.insert(answer);

                        redissonService.deleteRBucket(RedisConstant.QUESTION_STUDY_LIST_KEY);
                        WordHelper.getInstance().initWord();
                        return SocketMessage.study(question.getId().intValue(),"","已学习该问题，退出学习模式！",0);
                    }
                }
            }
        }
        return SocketMessage.fail();
    }
}
